package pi.growmate.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.growmate.datamodel.measurements.AirTemperatureMeasurement;
import pi.growmate.datamodel.measurements.SoilQualityMeasurement;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.plant.PlantCondition;
import pi.growmate.datamodel.sensors.DivisionSensor;
import pi.growmate.datamodel.sensors.PlantSensor;
import pi.growmate.datamodel.species.OptimalHumidity;
import pi.growmate.datamodel.species.OptimalTemperature;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.species.Season;
import pi.growmate.datamodel.task.TaskType;
import pi.growmate.datamodel.task.Task_Settings;
import pi.growmate.datamodel.task.Tasks_Current;
import pi.growmate.repositories.measurements.AirQualityRepository;
import pi.growmate.repositories.measurements.AirTemperatureRepository;
import pi.growmate.repositories.measurements.SoilQualityRepository;
import pi.growmate.repositories.tasks.CurrentTaskRepository;
import pi.growmate.repositories.tasks.TaskSettingsRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
public class AlgorithmsService {

    @Autowired
    TaskSettingsRepository taskSettingsRepository;
    @Autowired
    CurrentTaskRepository currentTaskRepository;
    @Autowired
    SoilQualityRepository soilQualityRepository;
    @Autowired
    AirTemperatureRepository airTemperatureRepository;
    @Autowired
    AirQualityRepository airQualityRepository;

    // TASKS

    // This method adds new Tasks and new Task Settings for a newly created Plant
    public void addTasksForNewPlant(Plant newPlant) {

        // For each available TaskType, we will create a new Task Settings instance that will store the frequency in which that task will be made, using an algorithm depending on the Task.
        // Besides this, a new instance of Tasks_Current will be created, with the Date calculated based on the task frequency
        for (TaskType type : TaskType.values()) {
            // Create a new Task Settings entity for this Plant and this type of Task
            Task_Settings settings = new Task_Settings();

            settings.setAutomatic(true);            // by default, new Plants will have tasks calculated automatically
            settings.setPlant(newPlant);
            settings.setTaskType(type);

            // Call the algorithm to calculate the frequency of the new Task Type
            settings.setTaskFrequency(calculateNewFrequency(newPlant, type));

            // Save the settings on the Task Settings Repository
            taskSettingsRepository.save(settings);

            // Create a new Tasks_Current entity for this TaskType and this Plant
            Tasks_Current task = new Tasks_Current();

            task.setPlant(newPlant);
            task.setTaskType(type);
            task.setName(createTaskName(newPlant, type));

            // Calculate the new Date for the Task
            calculateNewTaskDateForSingleTask(task);

            // Save the new Task
            currentTaskRepository.save(task);
        }
    }

    // This method implements the algorithm to calculate a new frequency for a Task, depending on the TaskType
    public int calculateNewFrequency(Plant plant, TaskType taskType) {
        PlantSpecies species = plant.getSpecies();

        return switch (taskType) {
            case FERTILIZER -> calculateFertilizingFrequency(species);
            case CHECK_PLANT -> calculateCheckingFrequency(species);
            case SOIL_CHANGE -> 300;
            case WATERING -> calculateNewWateringFrequency(species);
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };
    }

    // This method calculates the next date for a task of a given type, associated with a Plant
    public void calculateNewTaskDateForSingleTask(Tasks_Current task) {
        Plant plant = task.getPlant();
        TaskType taskType = task.getTaskType();

        // Getting the frequency of the Task from the Task Settings, and calculating the new Date for the Task
        Task_Settings settings = taskSettingsRepository.findFirstByPlantAndTaskType(plant, taskType);

        Date newDate = Date.valueOf(LocalDate.now().plusDays(settings.getTaskFrequency()));

        task.setTaskDate(newDate);
        currentTaskRepository.save(task);
    }

    // This method calculates the next date for all the Tasks associated with a Plant
    public void calculateNewTaskDateForAllTasks(Plant plant) {
        for (TaskType type : TaskType.values()) {
            // Find the Tasks_Current entity corresponding to this TaskType and this Plant
            Tasks_Current task = currentTaskRepository.findFirstByPlantAndTaskType(plant, type);

            // Getting the frequency of the Task from the Task Settings, and calculating the new Date for the Task
            Task_Settings settings = taskSettingsRepository.findFirstByPlantAndTaskType(plant, type);

            Date newDate = Date.valueOf(LocalDate.now().plusDays(settings.getTaskFrequency()));

            task.setTaskDate(newDate);

            // Save the Task
            currentTaskRepository.save(task);
        }
    }

    // PLANT CONDITION

    // This method sets the new condition for a Plant
    public void setNewPlantCondition(Plant plant){
        int condition = this.calculatePlantCondition(plant);

        switch(condition){
            case 0 -> plant.setPlantCondition(PlantCondition.BAD);
            case 1 -> plant.setPlantCondition(PlantCondition.NORMAL);
            case 2 -> plant.setPlantCondition(PlantCondition.GREAT);
        }
    }

    // This method calculates the current condition of a plant, taking into account as factors the tasks associated with
    // it, and the latest measurements of the sensors associated with it, if applicable
    // 0 - BAD; 1 - NORMAL; 2 - GREAT
    public int calculatePlantCondition(Plant plant) {
        boolean hasDivSensors, hasPlantSensors;

        // Checking whether the Plant, or its Division, has sensors associated with it or not
        if (plant.getDivision() != null) {
            hasDivSensors = plant.getDivision().getSensors().size() > 0;
        }else{
            hasDivSensors = false;
        }

        hasPlantSensors = plant.getSensors().size() > 0;


        // If a Plant has Plant Sensors, and their last measurement is out of the normal range, return 0
        if (hasPlantSensors) {
            for (PlantSensor sensor : plant.getSensors()) {
                SoilQualityMeasurement measurement = soilQualityRepository.findFirstBySensorOrderByPostDateDesc(sensor);

                if (measurement != null && !checkPlantHumidity(plant, measurement)) {
                    return 0;
                }
            }
        }


        // Checking the current Tasks attributed to the Plant
        List<Tasks_Current> currentTasks = plant.getCurrentTasks();

        // If a plant has tasks overdue, return 0; If it has tasks overdue in the next 3 days, return 1;
        for (Tasks_Current task : currentTasks) {
            int state = this.checkTaskDates(task);

            if (state == 0) {
                return 0;
            } else if (state == 1) {
                return 1;
            }
        }

        // If the Plant has Division Sensors, and the temperature is out of range, return 1; otherwise return 2;
        if (hasDivSensors) {
            for (DivisionSensor sensor : plant.getDivision().getSensors()) {
                AirTemperatureMeasurement measurement = airTemperatureRepository.findFirstBySensorOrderByPostDateDesc(sensor);

                if (measurement != null && !checkPlantTemperature(plant, measurement)) {
                    return 1;
                }
            }
        }

        return 2;
    }

    // AUXILLIARY FUNCTIONS

    // Setting the name for a new Task
    private String createTaskName(Plant plant, TaskType taskType) {
        String plantName = plant.getName();
        String taskName;

        switch (taskType) {
            case FERTILIZER -> taskName = "Fertilize " + plantName;
            case CHECK_PLANT -> taskName = "Check " + plantName + "'s condition";
            case SOIL_CHANGE -> taskName = "Change " + plantName + "'s soil mix";
            case WATERING -> taskName = "Water " + plantName;
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
        ;

        return taskName;
    }

    // Get the current Season
    public Season getCurrentSeason() {
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();

        if (month == Month.DECEMBER.getValue() || month == Month.JANUARY.getValue() || month == Month.FEBRUARY.getValue()) {
            return Season.WINTER;
        } else if (month == Month.MARCH.getValue() || month == Month.APRIL.getValue() || month == Month.MAY.getValue()) {
            return Season.SPRING;
        } else if (month == Month.JUNE.getValue() || month == Month.JULY.getValue() || month == Month.AUGUST.getValue()) {
            return Season.SUMMER;
        } else {
            return Season.FALL;
        }
    }

    // Calculating the watering frequency of a plant based on the species determined watering frequency, the species optimal humidity, and the current season
    private int calculateNewWateringFrequency(PlantSpecies species) {
        Season currentSeason = getCurrentSeason();
        int wateringFrequency, optimalHumidity;

        switch (species.getWateringFrequency()) {
            case INFREQUENT -> wateringFrequency = 1;
            case AVERAGE -> wateringFrequency = 2;
            case FREQUENT -> wateringFrequency = 3;
            default -> throw new IllegalArgumentException("Invalid watering frequency");
        }
        ;

        switch (species.getOptimalHumidity()) {
            case LOW -> optimalHumidity = 1;
            case MEDIUM -> optimalHumidity = 2;
            case HIGH -> optimalHumidity = 3;
            default -> throw new IllegalArgumentException("Invalid humidity converter");
        }
        ;

        if (wateringFrequency == 1) {
            if (currentSeason.equals(Season.FALL) || currentSeason.equals(Season.WINTER)) {
                return 20;
            } else if (optimalHumidity == 3 || currentSeason.equals(Season.SUMMER)) {
                return 10;
            } else {
                return 14;
            }
        } else if (wateringFrequency == 2) {
            if (currentSeason.equals(Season.FALL) || currentSeason.equals(Season.WINTER)) {
                return 10;
            } else if (optimalHumidity == 3 || currentSeason.equals(Season.SUMMER)) {
                return 6;
            } else {
                return 8;
            }
        } else {
            if (currentSeason.equals(Season.FALL) || currentSeason.equals(Season.WINTER)) {
                return 7;
            } else if (optimalHumidity == 3 || currentSeason.equals(Season.SUMMER)) {
                return 3;
            } else {
                return 5;
            }
        }
    }

    // Calculating the frequency with which a plant should be checked, based on the species difficulty
    private int calculateCheckingFrequency(PlantSpecies species) {
        return switch (species.getDifficulty()) {
            case 5 -> 7;
            case 4 -> 10;
            case 3 -> 14;
            case 2 -> 18;
            case 1 -> 21;
            default -> throw new IllegalArgumentException("Invalid watering frequency");
        };
    }

    // Calculating the fertilizing frequency of a plant based on the type of species, whether it's perennial or not, and the current season
    private int calculateFertilizingFrequency(PlantSpecies species) {
        Season currentSeason = getCurrentSeason();

        String cycle = species.getCycle();
        String family = species.getFamily().getName();


        if (family.equals("Succulents and Cacti")) {
            return 180;
        } else if (cycle.equals("Perennial")) {
            if (currentSeason.equals(Season.FALL) || currentSeason.equals(Season.WINTER)) {
                return 84;
            } else {
                return 42;
            }
        } else {
            if (currentSeason.equals(Season.FALL) || currentSeason.equals(Season.WINTER)) {
                return 56;
            } else {
                return 28;
            }
        }
    }

    // Checks the dates of a task corresponding to a Plant, in order to determine it's condition.
    // If a task has already passed, it returns 0 (RED); if there's a task in the next 2 days, it returns 1 (YELLOW)
    // otherwise, returns 2 (GREEN)
    private int checkTaskDates(Tasks_Current task) {
        Calendar calendar = Calendar.getInstance();
        Date today = new Date(calendar.getTime().getTime());

        // Checking if the date is in the past
        if (task.getTaskDate().before(today)) {
            return 0;
        }

        // Checking if the date is in the next 2 days
        long daysDifference = ChronoUnit.DAYS.between(LocalDate.now(), task.getTaskDate().toLocalDate());

        if (daysDifference < 3) {
            return 1;
        }

        return 2;
    }

    // Returns true if the latest humidity measurement for a plant is within it's optimal range, otherwise returns false
    public boolean checkPlantHumidity(Plant plant, SoilQualityMeasurement measurement) {
        OptimalHumidity optimalHumidity = plant.getSpecies().getOptimalHumidity();

        if (optimalHumidity.equals(OptimalHumidity.LOW) && (measurement.getMeasurement() < 24)) {
            return true;
        } else if (optimalHumidity.equals(OptimalHumidity.MEDIUM) && (measurement.getMeasurement() > 24 && measurement.getMeasurement() < 50)) {
            return true;
        } else return optimalHumidity.equals(OptimalHumidity.HIGH) && measurement.getMeasurement() > 50;
    }

    // Returns true if the latest temperature measurement for a plant is within it's optimal range, otherwise returns false
    public boolean checkPlantTemperature(Plant plant, AirTemperatureMeasurement measurement) {
        OptimalTemperature optimalTemperature = plant.getSpecies().getOptimalTemperature();

        if (optimalTemperature.equals(OptimalTemperature.COOL) && (measurement.getMeasurement() < 18 && measurement.getMeasurement() > 10)) {
            return true;
        } else if (optimalTemperature.equals(OptimalTemperature.AVERAGE) && (measurement.getMeasurement() > 18 && measurement.getMeasurement() < 28)) {
            return true;
        } else return optimalTemperature.equals(OptimalTemperature.WARM) && measurement.getMeasurement() > 28;
    }
}

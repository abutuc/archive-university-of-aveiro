package pi.growmate.services;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.task.TaskType;
import pi.growmate.datamodel.task.Task_Settings;
import pi.growmate.datamodel.task.Tasks_Current;
import pi.growmate.datamodel.task.Tasks_History;
import pi.growmate.datamodel.user.User;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.repositories.plant.PlantRepository;
import pi.growmate.repositories.tasks.CurrentTaskRepository;
import pi.growmate.repositories.tasks.HistoricTaskRepository;
import pi.growmate.repositories.tasks.TaskSettingsRepository;
import pi.growmate.repositories.user.UserRepository;
import pi.growmate.utils.SuccessfulRequest;

@Service
@Slf4j
public class TasksService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentTaskRepository currentTaskRepository;

    @Autowired
    private HistoricTaskRepository historicTaskRepository;

    @Autowired
    private TaskSettingsRepository taskSettingsRepository;

    @Autowired
    private AlgorithmsService algorithmsService;

    @Autowired
    private PlantRepository plantRepository;

    public List<Tasks_Current> getTasksForToday(Long userID) throws ResourceNotFoundException{
        User user = getUser(userID);
        Date today = new Date(System.currentTimeMillis()); //data de hoje
        log.info(today.toString());

        List<Tasks_Current> tasks = user.getPlants().stream()
                            .flatMap(plant -> plant.getCurrentTasks().stream())
                            .filter(task -> task.getTaskDate().toString().equals(today.toString()))
                            .collect(Collectors.toList());

        return tasks;
        
    }

    public Map<String, List<Tasks_Current>> getTasksToDo(Long userID) throws ResourceNotFoundException{
        User user = getUser(userID);

        return user.getPlants().stream()
                    .collect(Collectors.toMap(
                            plant -> plant.getId().toString(),
                            Plant::getCurrentTasks
                    ));
    }

    public List<Tasks_History> getTasksDone(Long userID) throws ResourceNotFoundException{
        User user = getUser(userID);
        List<Tasks_History> tasks = user.getPlants().stream()
                            .flatMap(plant -> plant.getHistoryTasks().stream())
                            .collect(Collectors.toList());
        return tasks;
    }

    //

    public List<Tasks_Current> getTasksForTodayPlant(Long userID, Long plantaID) throws ResourceNotFoundException{
        User user = getUser(userID);
        Plant planta = getPlant(user, plantaID);
        Date today = new Date(System.currentTimeMillis()); //data de hoje

        List<Tasks_Current> tasks = planta.getCurrentTasks().stream()
                            .filter(task -> task.getTaskDate().toString().equals(today.toString())).collect(Collectors.toList());

        return tasks;
    }

    public List<Tasks_Current> getTasksToDoPlant(Long userID, Long plantaID) throws ResourceNotFoundException{
        User user = getUser(userID);
        Plant planta = getPlant(user, plantaID);
        return new ArrayList<>(planta.getCurrentTasks());
    }

    public List<Tasks_History> getTasksDonePlant(Long userID, Long plantaID) throws ResourceNotFoundException{
        User user = getUser(userID);
        Plant planta = getPlant(user, plantaID);
        return new ArrayList<>(planta.getHistoryTasks());
    }

    public SuccessfulRequest updateTaskStatus(Long idUser, Long idPlant, Long idTask) throws ResourceNotFoundException{
        User user = getUser(idUser);
        Plant planta = getPlant(user, idPlant);

        // Finding the task in the list of current active tasks
        Tasks_Current task = planta.getCurrentTasks().stream()
                        .filter(t -> t.getId().equals(idTask))
                        .findFirst()
                        .orElseThrow(() -> new ResourceNotFoundException("Task with ID: " + idTask + " not found."));

        // Creating a record of the task to be added into the Task History table
        Tasks_History taskRecord = new Tasks_History();
        taskRecord.setName(task.getName());
        taskRecord.setTaskType(task.getTaskType());
        taskRecord.setPlant(planta);
        taskRecord.setDoneDate(Date.valueOf(LocalDate.now()));

        historicTaskRepository.save(taskRecord);

        // Updating the existing Task with a new Date
        algorithmsService.calculateNewTaskDateForSingleTask(task);

        // Updating the Plant's condition
        algorithmsService.setNewPlantCondition(planta);
        plantRepository.save(planta);

        return new SuccessfulRequest("Updated task with success");
    }

    public SuccessfulRequest toggleTaskMode(Long idUser, Long idPlant, String taskType, Integer frequency) throws ResourceNotFoundException {
        User user = getUser(idUser);
        Plant planta = getPlant(user, idPlant);

        // Finding the correct entry in Task_Settings, and toggling the mode
        TaskType type;

         switch (taskType) {
            case "FERTILIZER" -> type = TaskType.FERTILIZER;
            case "CHECK_PLANT" -> type = TaskType.CHECK_PLANT;
            case "SOIL_CHANGE" -> type = TaskType.SOIL_CHANGE;
            case "WATERING" -> type = TaskType.WATERING;
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };

        Task_Settings settings = taskSettingsRepository.findFirstByPlantAndTaskType(planta, type);
        settings.toggleMode();

        // In case the task is now automatic, we should calculate the new frequencies and task dates
        if(settings.isAutomatic()){
            int taskFrequency = algorithmsService.calculateNewFrequency(planta, type);
            settings.setTaskFrequency(taskFrequency);

            taskSettingsRepository.save(settings);

            // Find the corresponding Current_Task, and calculate the new Date
            Tasks_Current task = currentTaskRepository.findFirstByPlantAndTaskType(planta, type);
            algorithmsService.calculateNewTaskDateForSingleTask(task);

        }else{
            // Updating the task Frequency, if present in the call
            if(frequency != null){
                settings.setTaskFrequency(frequency);

                // Find the corresponding Current_Task, and calculate the new Date
                Tasks_Current task = currentTaskRepository.findFirstByPlantAndTaskType(planta, type);
                algorithmsService.calculateNewTaskDateForSingleTask(task);

                currentTaskRepository.save(task);
            }
        }

        taskSettingsRepository.save(settings);

        return new SuccessfulRequest("Task mode toggled successfully!");
    }

    public Map<String, List<Task_Settings>> getTaskSettings(Long idUser, Long idPlant, String taskType) throws ResourceNotFoundException{
        User user = getUser(idUser);

        // Depending on whether we're getting all the settings, or just for a Plant
        if(idPlant == null){
            // Getting all of the Plants belonging to the User
            List<Plant> userPlants = user.getPlants().stream().toList();

            return  userPlants.stream()
                    .collect(Collectors.toMap(
                            plant -> plant.getId().toString(),
                            Plant::getTaskSettings
                    ));
        }else if(taskType == null){
            // Getting all the task related to a Plant
            Plant planta = getPlant(user, idPlant);

            HashMap<String, List<Task_Settings>> map = new HashMap<>();
            map.put(planta.getId().toString(), planta.getTaskSettings());

            return map;
        }else{
            // Getting only a specific task
            // Finding the correct entry in Task_Settings, and toggling the mode
            TaskType type;

            switch (taskType) {
                case "FERTILIZER" -> type = TaskType.FERTILIZER;
                case "CHECK_PLANT" -> type = TaskType.CHECK_PLANT;
                case "SOIL_CHANGE" -> type = TaskType.SOIL_CHANGE;
                case "WATERING" -> type = TaskType.WATERING;
                default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
            };

            Plant planta = getPlant(user, idPlant);

            Task_Settings settings = taskSettingsRepository.findFirstByPlantAndTaskType(planta, type);

            HashMap<String, List<Task_Settings>> map = new HashMap<>();
            map.put(planta.getId().toString(), Collections.singletonList(settings));

            return map;
        }
    }

    public SuccessfulRequest updateFrequency(Long idUser, Long idPlant, String taskType, Integer frequency) throws ResourceNotFoundException {
        User user = getUser(idUser);
        Plant planta = getPlant(user, idPlant);

        // Finding the correct entry in Task_Settings, and toggling the mode
        TaskType type;

        switch (taskType) {
            case "FERTILIZER" -> type = TaskType.FERTILIZER;
            case "CHECK_PLANT" -> type = TaskType.CHECK_PLANT;
            case "SOIL_CHANGE" -> type = TaskType.SOIL_CHANGE;
            case "WATERING" -> type = TaskType.WATERING;
            default -> throw new IllegalArgumentException("Invalid task type: " + taskType);
        };

        Task_Settings settings = taskSettingsRepository.findFirstByPlantAndTaskType(planta, type);
        settings.setTaskFrequency(frequency);
        taskSettingsRepository.save(settings);

        // Find the corresponding Current_Task, and calculate the new Date
        Tasks_Current task = currentTaskRepository.findFirstByPlantAndTaskType(planta, type);
        log.info(task.toString());

        algorithmsService.calculateNewTaskDateForSingleTask(task);

        currentTaskRepository.save(task);

        return new SuccessfulRequest("Task frequency changed successfully!");
    }

    public SuccessfulRequest updateTaskDate(Long idUser, Long idTask, Date newDate) throws ResourceNotFoundException {
        User user = getUser(idUser);
        Tasks_Current task = currentTaskRepository.findById(idTask).orElseThrow(() -> new ResourceNotFoundException("Task with ID: " + idTask + " not found."));

        task.setTaskDate(newDate);

        currentTaskRepository.save(task);

        // Recalculate plant's condition
        Plant plant = task.getPlant();
        algorithmsService.setNewPlantCondition(plant);
        plantRepository.save(plant);

        return new SuccessfulRequest("Task date updated successfully!");
    }
    
    // Auxilliary functions
    private User getUser(long userID) throws ResourceNotFoundException {
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userID + " not found."));
    }

    private Plant getPlant(User user, long plantID) throws ResourceNotFoundException {
        return user.getPlants().stream()
                .filter(d -> d.getId().equals(plantID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Plant with ID: " + plantID + " not found."));
    }


}

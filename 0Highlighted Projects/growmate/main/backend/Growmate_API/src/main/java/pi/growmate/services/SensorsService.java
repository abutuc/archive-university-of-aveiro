package pi.growmate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.sensors.DivisionSensor;
import pi.growmate.datamodel.measurements.AirQualityMeasurement;
import pi.growmate.datamodel.measurements.AirTemperatureMeasurement;
import pi.growmate.datamodel.measurements.Measurement;
import pi.growmate.datamodel.measurements.SoilQualityMeasurement;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.sensors.GenericSensor;
import pi.growmate.datamodel.sensors.PlantSensor;
import pi.growmate.datamodel.user.User;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.repositories.division.DivisionRepository;
import pi.growmate.repositories.division.DivisionSensorRepository;
import pi.growmate.repositories.measurements.AirQualityRepository;
import pi.growmate.repositories.measurements.AirTemperatureRepository;
import pi.growmate.repositories.measurements.SoilQualityRepository;
import pi.growmate.repositories.plant.PlantRepository;
import pi.growmate.repositories.plant.PlantSensorRepository;
import pi.growmate.repositories.user.UserRepository;
import pi.growmate.utils.SuccessfulRequest;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SensorsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlantSensorRepository plantSensorRepository;
    @Autowired
    private DivisionSensorRepository divisionSensorRepository;
    @Autowired
    private SoilQualityRepository soilQualityRepository;
    @Autowired
    private AirQualityRepository airQualityRepository;
    @Autowired
    private AirTemperatureRepository airTemperatureRepository;
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private PlantRepository plantRepository;

    public List<DivisionSensor> getDivisionSensors(long userID, long divisionID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);
        Division div = this.getDivision(user, divisionID);

        return div.getSensors();
    }

    public List<PlantSensor> getPlantSensors(long userID, long plantID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);
        Plant plant = this.getPlant(user, plantID);

        return plant.getSensors();
    }

    public Map<Long, List<GenericSensor>> getSensorsFromUser(long userID, int type) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);

        return switch (type) {
            case 0 -> user.getDivisionSensors().stream()
                    .collect(Collectors.groupingBy(sensor -> sensor.getDivision().getId(),
                            Collectors.mapping(sensor -> (GenericSensor) sensor,
                                    Collectors.toList())));
            case 1 -> user.getPlantSensors().stream()
                    .collect(Collectors.groupingBy(sensor -> sensor.getPlant().getId(),
                    Collectors.mapping(sensor -> (GenericSensor) sensor,
                            Collectors.toList())));
            default -> throw new IllegalArgumentException("Invalid type integer! Must be 0 for Division Sensors or 1 for Plant Sensors!");
        };
    }

    public SuccessfulRequest addNewSensor(long userID, int type, String name, String  code, long ownerID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);

        if(type == 0){ // Division Sensor
            Division div = this.getDivision(user, ownerID);
            DivisionSensor newSensor = new DivisionSensor();

            newSensor.setName(name);
            newSensor.setSensorCode(code);
            newSensor.setOwner(user);
            newSensor.setDivision(div);

            divisionSensorRepository.save(newSensor);
        }else{  // Plant Sensor
            Plant plant = this.getPlant(user, ownerID);
            PlantSensor newSensor = new PlantSensor();

            newSensor.setName(name);
            newSensor.setSensorCode(code);
            newSensor.setOwner(user);
            newSensor.setPlant(plant);

            plantSensorRepository.save(newSensor);
        }

        return new SuccessfulRequest("Sensor added succesfully!");
    }

    public SuccessfulRequest deleteSensor(long userID, long sensorID, int type) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);

        if(type == 0){ // Division Sensor
            DivisionSensor sensor = divisionSensorRepository.findById(sensorID).orElseThrow(() -> new ResourceNotFoundException("Sensor with ID: " + sensorID + " not found."));

            divisionSensorRepository.delete(sensor);
        }else{  // Plant Sensor
            PlantSensor sensor = plantSensorRepository.findById(sensorID).orElseThrow(() -> new ResourceNotFoundException("Sensor with ID: " + sensorID + " not found."));

            plantSensorRepository.delete(sensor);
        }

        return new SuccessfulRequest("Sensor deleted succesfully!");
    }

    public SuccessfulRequest updateSensorInformation(long userID, long sensorID, int type, String newName, Long newAssociatedID) throws ResourceNotFoundException{
        User user = this.checkIfUserExists(userID);

        if(type == 0){ // Division Sensor
            DivisionSensor sensor = divisionSensorRepository.findById(sensorID).orElseThrow(() -> new ResourceNotFoundException("Sensor with ID: " + sensorID + " not found."));

            sensor.setName(newName != null ? newName : sensor.getName());

            if(newAssociatedID !=  null){
                Division newDiv = divisionRepository.findById(newAssociatedID).orElseThrow(() -> new ResourceNotFoundException("Division with ID: " + newAssociatedID + " not found."));

                sensor.setDivision(newDiv);
            }

            divisionSensorRepository.save(sensor);
        }else{  // Plant Sensor
            PlantSensor sensor = plantSensorRepository.findById(sensorID).orElseThrow(() -> new ResourceNotFoundException("Sensor with ID: " + sensorID + " not found."));

            sensor.setName(newName != null ? newName : sensor.getName());

            if(newAssociatedID !=  null){
                Plant newPlant = plantRepository.findById(newAssociatedID).orElseThrow(() -> new ResourceNotFoundException("Plant with ID: " + newAssociatedID + " not found."));

                sensor.setPlant(newPlant);
            }

            plantSensorRepository.save(sensor);
        }

        return new SuccessfulRequest("Sensor updated successfully!");
    }

    public Map<String, Measurement> getLatestMeasurements(long userID) throws ResourceNotFoundException{
        User user = this.checkIfUserExists(userID);

        List<Plant> userPlants = user.getPlants();
        List<Division> userDivisions = user.getDivisions();

        // Getting all of the sensors associated with this User's account
        List<PlantSensor> allPlantSensors = userPlants.stream()
                                                        .flatMap(plant -> plant.getSensors().stream())
                                                        .toList();

        List<DivisionSensor> allDivisionSensors = userDivisions.stream()
                                                        .flatMap(division -> division.getSensors().stream())
                                                        .toList();

        // From each sensor, get the latest measurement
        Map<String, SoilQualityMeasurement> soilQualityMeasurements = allPlantSensors.stream()
                                                                    .map(sensor -> soilQualityRepository.findFirstBySensorOrderByPostDateDesc(sensor))
                                                                    .collect(Collectors.toMap(
                                                                            sensor -> sensor.getSensor().getPlant().getName() + "_soilq",
                                                                            Function.identity()
                                                                    ));

        Map<String, AirQualityMeasurement> airQualityMeasurements = allDivisionSensors.stream()
                .map(sensor -> airQualityRepository.findFirstBySensorOrderByPostDateDesc(sensor))
                .collect(Collectors.toMap(
                        sensor -> sensor.getSensor().getDivision().getName() + "_airq",
                        Function.identity()
                ));

        Map<String, AirTemperatureMeasurement> airTemperatureMeasurements = allDivisionSensors.stream()
                .map(sensor -> airTemperatureRepository.findFirstBySensorOrderByPostDateDesc(sensor))
                .collect(Collectors.toMap(
                        sensor -> sensor.getSensor().getDivision().getName() + "_airtemp",
                        Function.identity()
                ));

        // Add the measurements to the return map

        return Stream.of(soilQualityMeasurements.entrySet(), airQualityMeasurements.entrySet(), airTemperatureMeasurements.entrySet())
                .flatMap(Collection::stream)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));

    }

    public Map<String, Measurement> getLatestSingleMeasurement(long userID, long sensorID, int type) throws ResourceNotFoundException{
        User user = this.checkIfUserExists(userID);
        Map<String, Measurement> measurementMap = new HashMap<>();

        if(type == 0){ // Division Sensor
            DivisionSensor sensor = divisionSensorRepository.findById(sensorID).orElseThrow(() -> new ResourceNotFoundException("Sensor with ID: " + sensorID + " not found."));

            measurementMap.put(sensor.getDivision().getName() + "_airq", airQualityRepository.findFirstBySensorOrderByPostDateDesc(sensor));
            measurementMap.put(sensor.getDivision().getName() + "_airtemp", airTemperatureRepository.findFirstBySensorOrderByPostDateDesc(sensor));

        }else{  // Plant Sensor
            PlantSensor sensor = plantSensorRepository.findById(sensorID).orElseThrow(() -> new ResourceNotFoundException("Sensor with ID: " + sensorID + " not found."));

            measurementMap.put(sensor.getPlant().getName() + "_soilq", soilQualityRepository.findFirstBySensorOrderByPostDateDesc(sensor));
        }

        return measurementMap;
    }

    public Map<String, List<Measurement>> getThreeDaysMeasurements(long userID, long plantID) throws ResourceNotFoundException{
        User user = this.checkIfUserExists(userID);
        Map<String, List<Measurement>> returnMap = new HashMap<>();

        // Check if the Plant is in the User inventory
        Plant plant = user.getPlants().stream()
                .filter(p -> p.getId().equals(plantID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Plant with ID: " + plantID + " not found."));

        // Get the sensors related with the plant. For each one, get the last measurements
        for(PlantSensor sensor: plant.getSensors()){
            List<Measurement> measurements = soilQualityRepository.findAllByPostDateAfterAndSensorOrderByPostDateDesc(LocalDateTime.now().minusDays(3), sensor).stream()
                    .map(measurement -> (Measurement) measurement)
                    .toList();

            returnMap.put(sensor.getName() + "_soilq", measurements);
        }

        // Check if the plant is associated with a Division, and if so, if the Division has sensors. Add their mesaurements to the return map
        Division div = plant.getDivision();

        if(div != null){
            for(DivisionSensor sensor: div.getSensors()){
                List<Measurement> measurements = airQualityRepository.findAllByPostDateAfterAndSensorOrderByPostDateDesc(LocalDateTime.now().minusDays(3), sensor).stream()
                        .map(measurement -> (Measurement) measurement)
                        .toList();

                returnMap.put(sensor.getName() + "_airq", measurements);

                List<Measurement> measurementsTemp = airTemperatureRepository.findAllByPostDateAfterAndSensorOrderByPostDateDesc(LocalDateTime.now().minusDays(3), sensor).stream()
                        .map(measurement -> (Measurement) measurement)
                        .toList();

                returnMap.put(sensor.getName() + "_airtemp", measurementsTemp);
            }
        }

        return returnMap;
    }

    // Auxilliary functions
    private User checkIfUserExists(long userID) throws ResourceNotFoundException {
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userID + " not found."));
    }

    private Division getDivision(User user, long divisionID) throws ResourceNotFoundException {
        return user.getDivisions().stream()
                .filter(d -> d.getId().equals(divisionID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Division with ID: " + divisionID + " not found."));
    }

    private Plant getPlant(User user, long plantID) throws ResourceNotFoundException {
        return user.getPlants().stream()
                .filter(d -> d.getId().equals(plantID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Plant with ID: " + plantID + " not found."));
    }
}

package pi.growmate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.growmate.datamodel.sensors.DivisionSensor;
import pi.growmate.datamodel.measurements.Measurement;
import pi.growmate.datamodel.sensors.GenericSensor;
import pi.growmate.datamodel.sensors.PlantSensor;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.services.SensorsService;
import pi.growmate.utils.SuccessfulRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("growmate/user")
@CrossOrigin
public class SensorsController {
    @Autowired
    private SensorsService sensorsService;

    public SensorsController(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    // Gets all the Sensors associated with an User. For the type parameter: 0 - Division Sensor; 1 - Plant Sensor
    @GetMapping("/{userID}/sensors")
    public ResponseEntity<Map<Long, List<GenericSensor>>> getSensorsFromUser(@PathVariable(value = "userID") Long userID,
                                                                  @RequestParam(value = "type") int type) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.getSensorsFromUser(userID, type));
    }

    // Gets the Sensors associated with a given Division
    @GetMapping("/{userID}/sensors/division/{divisionID}")
    public ResponseEntity<List<DivisionSensor>> getSensorsOnDivision(@PathVariable(value = "userID") Long userID,
                                                                     @PathVariable(value = "divisionID") Long divisionID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.getDivisionSensors(userID, divisionID));
    }

    // Gets the Sensors associated with a given Plant
    @GetMapping("/{userID}/sensors/plant/{plantID}")
    public ResponseEntity<List<PlantSensor>> getPlantSensors(@PathVariable(value = "userID") Long userID,
                                                             @PathVariable(value = "plantID") Long plantID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.getPlantSensors(userID, plantID));
    }

    // Creates a new Sensor (0 - Division Sensor; 1 - Plant Sensor)
    @PostMapping("/{userID}/sensors/")
    public ResponseEntity<SuccessfulRequest> addNewSensor(@PathVariable(value = "userID") Long userID,
                                                          @RequestParam(name = "sensorType") int type,
                                                          @RequestParam(name = "sensorName") String name,
                                                          @RequestParam(name = "sensorCode") String code,
                                                          @RequestParam(name = "ownerID") Long ownerID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.addNewSensor(userID, type, name, code, ownerID));
    }

    // Deletes a Sensor (sensorType: 0 - Division Sensor; 1 - Plant Sensor)
    @DeleteMapping("/{userID}/sensors/{sensorID}")
    public ResponseEntity<SuccessfulRequest> deleteSensor(@PathVariable(value = "userID") Long userID,
                                                          @PathVariable(value = "sensorID") Long sensorID,
                                                          @RequestParam(name = "sensorType") int type) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.deleteSensor(userID, sensorID, type));
    }

    // Changes a sensor's information, such as the division/plant it's associated with, or it's name. The ID of the new division/plant it's associated with
    // is given by newAssociatedID. (sensorType: 0 - Division Sensor; 1 - Plant Sensor)
    @PutMapping("/{userID}/sensors/{sensorID}")
    public ResponseEntity<SuccessfulRequest> updateSensorInformation(@PathVariable(value = "userID") Long userID,
                                                                     @PathVariable(value = "sensorID") Long sensorID,
                                                                     @RequestParam(name = "sensorType") int type,
                                                                     @RequestParam(name = "newName", required = false) String newName,
                                                                     @RequestParam(name = "newAssociatedID", required = false) Long newAssociatedID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.updateSensorInformation(userID, sensorID, type, newName, newAssociatedID));
    }

    // Getting the last measurements from all sensors related to a user
    //TODO: Not Tested
    @GetMapping("/{userID}/sensors/last")
    public ResponseEntity<Map<String, Measurement>> returnLatestMeasurements(@PathVariable(value = "userID") Long userID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.getLatestMeasurements(userID));
    }

    // Getting the last measurements from a specific sensor related to a user (sensorType: 0 - Division Sensor; 1 - Plant Sensor)
    @GetMapping("/{userID}/sensors/{sensorID}/last")
    public ResponseEntity<Map<String, Measurement>> returnLatestSingleMeasurement(@PathVariable(value = "userID") Long userID,
                                                                                  @PathVariable(value = "sensorID") Long sensorID,
                                                                                  @RequestParam(name = "sensorType") int type) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.getLatestSingleMeasurement(userID, sensorID, type));
    }

    // Get the measurements of a plant in the past 3 days
    @GetMapping("/{userID}/sensors/last/plant/{plantID}")
    public ResponseEntity<Map<String, List<Measurement>>> returnMeasurementsPlant(@PathVariable(value = "userID") Long userID,
                                                                            @PathVariable(value = "plantID") Long plantID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(sensorsService.getThreeDaysMeasurements(userID, plantID));
    }
}

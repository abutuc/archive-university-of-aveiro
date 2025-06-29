package pi.growmate.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.sensors.PlantSensor;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.user.User;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.repositories.division.DivisionRepository;
import pi.growmate.repositories.plant.PlantRepository;
import pi.growmate.repositories.plant.PlantSensorRepository;
import pi.growmate.repositories.species.PlantSpeciesRepository;
import pi.growmate.repositories.user.UserRepository;
import pi.growmate.utils.SuccessfulRequest;

@Service
@Slf4j
public class PlantService {

    @Autowired 
    private PlantRepository plantRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private PlantSensorRepository plantSensorRepository;

    @Autowired 
    PlantSpeciesRepository plantSpeciesRepository;


    public Plant getPlantInfo(long userID, long plantID) throws ResourceNotFoundException{
        return getPlant(checkIfUserExists(userID), plantID);
    }

    public PlantSpecies getPlantSpeciesInfo(long userID,long plantID) throws ResourceNotFoundException{
        Plant planta = getPlant(checkIfUserExists(userID), plantID);

        return planta.getSpecies();
    }

    public SuccessfulRequest removePlant(long userID, long plantID, boolean dead) throws ResourceNotFoundException{
        User user = checkIfUserExists(userID);
        Plant planta = getPlant(user, plantID);

        List<Plant> userPlants = user.getPlants();
        userPlants.remove(planta);
        user.setPlants(userPlants);
        

        if (dead){
            user.setDead_plants(user.getDead_plants()+1);
        }

        userRepository.save(user);

        plantRepository.delete(planta);

        return new SuccessfulRequest("Plant deleted with success.");
    }

    public Plant updatePlantInfo(long userID, long plantID, String nome, String photo, Long division_id, Long sensor_id, Date plantation_date ) throws ResourceNotFoundException{
        Plant planta = getPlant(checkIfUserExists(userID), plantID);
        if (nome!=null){
            planta.setName(nome);
        }
        if (photo!=null){
            planta.setPlantPhoto(photo);
        }
        if (division_id!=null){
            Division division = divisionRepository.findById(division_id).orElseThrow(() -> new ResourceNotFoundException("Division with id " + division_id + " not found"));
            planta.setDivision(division);
        }
        if (sensor_id != null){
            PlantSensor sensor = plantSensorRepository.findById(sensor_id).orElseThrow(() -> new ResourceNotFoundException("Plant sensor with id " + sensor_id + " not found"));
            planta.removeAllSensors();
            planta.addSensor(sensor);
        }
        if (plantation_date!=null){
            planta.setPlantationDate(plantation_date);
        }
        plantRepository.save(planta);
        return plantRepository.findById(planta.getId()).orElseThrow(() -> new ResourceNotFoundException("can't find by id "+ plantID));
    }

    //auxiliary functions

    private Plant getPlant(User user, long plantID) throws ResourceNotFoundException {
        return user.getPlants().stream()
                .filter(p -> p.getId().equals(plantID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Plant with ID: " + plantID + " not found."));

    }

    private User checkIfUserExists(long userID) throws ResourceNotFoundException {
        return userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userID + " not found."));
   }
    
}

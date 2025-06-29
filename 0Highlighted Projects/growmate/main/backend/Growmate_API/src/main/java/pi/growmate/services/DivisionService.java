package pi.growmate.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.species.OptimalLuminosity;
import pi.growmate.datamodel.user.User;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.repositories.division.DivisionRepository;
import pi.growmate.repositories.plant.PlantRepository;
import pi.growmate.repositories.user.UserRepository;
import pi.growmate.utils.SuccessfulRequest;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class DivisionService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DivisionRepository divisionRepository;
    @Autowired
    private PlantRepository plantRepository;

    public List<Division> getUserDivisions(Long userID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);

        return user.getDivisions();
    }

    public List<Plant> getPlantsOnDivision(Long userID, Long divisionID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);
        Division div = this.getDivision(user, divisionID);

        return div.getPlantsOnDivision();
    }

    public Plant updatePlantDivisionStatus(Long userID, Long divisionID, Long plantID, Long newDivisionID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);
        Division oldDiv = this.getDivision(user, divisionID);

        Plant plant = oldDiv.getPlantsOnDivision().stream()
                .filter(p -> p.getId().equals(plantID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Plant with ID: " + plantID + " not found."));

        if(newDivisionID != null){
            Division newDiv = this.getDivision(user, newDivisionID);

            plant.setDivision(newDiv);
            oldDiv.getPlantsOnDivision().remove(plant);
            newDiv.getPlantsOnDivision().add(plant);
        }else{
            plant.setDivision(null);
        }

        plantRepository.save(plant);
        return plant;
    }

    public SuccessfulRequest addPlantToDivision(Long userID, Long divisionID, Long plantID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);
        Division newDiv = this.getDivision(user, divisionID);

        // Check if the Plant is in the User inventory
        Plant plant = user.getPlants().stream()
                .filter(p -> p.getId().equals(plantID))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Plant with ID: " + plantID + " not found."));

        // Set the new Division
        plant.setDivision(newDiv);
        plantRepository.save(plant);

        return new SuccessfulRequest("Plant added to Division successfully!");
    }

    public Division deleteDivision(Long userID, Long divisionID) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);
        Division div = this.getDivision(user, divisionID);

        // Making the division related to plants null
        div.getPlantsOnDivision().forEach(plant -> {
            plant.setDivision(null);
            plantRepository.save(plant);
        });

        div.setPlantsOnDivision(new ArrayList<>());

        List<Division> userDivisions = user.getDivisions();
        userDivisions.remove(div);
        user.setDivisions(userDivisions);
        userRepository.save(user);

        divisionRepository.delete(div);
        return div;
    }

    public SuccessfulRequest addNewDivision(Long userID, String name, int luminosityType) throws ResourceNotFoundException {
        User user = this.checkIfUserExists(userID);
        Division div = new Division();

        switch (luminosityType) {
            case 0 -> div.setLuminosity(OptimalLuminosity.LOW);
            case 1 -> div.setLuminosity(OptimalLuminosity.MEDIUM);
            case 2 -> div.setLuminosity(OptimalLuminosity.HIGH);
            case 3 -> div.setLuminosity(OptimalLuminosity.SUNNY);
            default -> throw new IllegalArgumentException("Invalid luminosity converter integer: " + luminosityType);
        };

        div.setName(name);
        div.setOwner(user);

        divisionRepository.save(div);

        return new SuccessfulRequest("Division added successfully!");
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
}

package pi.growmate.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.services.PlantService;
import pi.growmate.services.UserService;
import pi.growmate.utils.SuccessfulRequest;

@RestController
@RequestMapping("growmate/user")
@CrossOrigin
public class PlantInventoryController {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    // get information about a plant
    @GetMapping("{idUser}/inventory/{idPlanta}")
    public ResponseEntity<Plant> getPlantInfo(@PathVariable(value = "idUser") Long idUser,  
                                              @PathVariable(value = "idPlanta") Long idPlanta) throws ResourceNotFoundException{
                                                  
        return ResponseEntity.ok().body(plantService.getPlantInfo(idUser, idPlanta));
    }

    // Get the species info of a plant in a User's inventory
    @GetMapping("{idUser}/inventory/{idPlanta}/speciesinfo")
    public ResponseEntity<PlantSpecies> getPlantSpeciesInfo(@PathVariable(value = "idUser") Long idUser,   
                                                            @PathVariable(value = "idPlanta") Long idPlanta) throws ResourceNotFoundException{

        return ResponseEntity.ok().body(plantService.getPlantSpeciesInfo(idUser, idPlanta));
                 
    }

    // Deletes a plant from a user's inventory
    @DeleteMapping("{idUser}/inventory/{idPlanta}")
    public ResponseEntity<SuccessfulRequest> removePlant(@PathVariable(value = "idUser") Long idUser,
                                                        @PathVariable(value = "idPlanta") Long idPlanta,
                                                        @RequestParam(value = "dead") boolean dead) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(plantService.removePlant(idUser, idPlanta, dead));
    }

    //String nome, String photo, Long division_id, Long sensor_id, Date plantation_date
    @PutMapping("{idUser}/inventory/{idPlanta}")
    public ResponseEntity<Plant> updatePlantInfo(@PathVariable(value = "idUser") Long idUser,
                                                @PathVariable(value = "idPlanta") Long idPlanta,
                                                @RequestParam(value = "nomePlanta", required = false) String nomePlanta,
                                                @RequestParam(value = "photoPlanta", required = false) String photoPlanta, 
                                                @RequestParam(value = "division-id", required = false) Long division_id,
                                                @RequestParam(value = "sensor-id", required = false) Long sensor_id,
                                                @RequestParam(value = "plantation-date", required = false) Date date
                                                ) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(plantService.updatePlantInfo(idUser, idPlanta, nomePlanta, photoPlanta, division_id, sensor_id, date));

    }

    // get informacao sobre lista de plantas do utilizador
    @GetMapping("/{idUser}/plants")
    public ResponseEntity<List<Plant>> getPlantsFromUser(@PathVariable(value = "idUser") Long idUser) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(userService.getUserPlants(idUser));
    }

    // add new plant to inventory
    @PostMapping("/{idUser}/addplant")
    public ResponseEntity<SuccessfulRequest> addNewPlantToUserInventory(@PathVariable(value = "idUser") Long idUser,
                                                                        @RequestParam(value = "plantName") String plantName,
                                                                        @RequestParam(value = "photoURL", required = false) String URL,
                                                                        @RequestParam(value = "species-id") Long speciesID,
                                                                        @RequestParam(value = "division-id", required = false) Long divisionID,
                                                                        @RequestParam(value = "sensor-id", required = false) Long sensorID,
                                                                        @RequestParam(value = "plantation-date", required = false) Date date) throws ResourceNotFoundException{

        return ResponseEntity.ok().body(userService.addNewPlantToUserInventory(idUser, plantName, URL, speciesID, divisionID, sensorID, date));
    }

    // get a plant recomended to a user
    @GetMapping("/{idUser}/recommendation")
    public ResponseEntity<List<PlantSpecies>> recommendPlantSpecies(@PathVariable(value = "idUser") Long idUser) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(userService.recommendPlantSpecies(idUser));
    }
}

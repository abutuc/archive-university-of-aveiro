package pi.growmate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.user.User;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.services.PlantSpeciesService;
import pi.growmate.services.UserService;

@RestController
@RequestMapping("growmate/admin")
@CrossOrigin
public class AdminController {
    
    @Autowired
    private PlantSpeciesService plantSpeciesService;
    @Autowired
    private UserService userService;

    @GetMapping("/plants")
    public ResponseEntity<List<PlantSpecies>> getAllPlants(){
        return ResponseEntity.ok().body(plantSpeciesService.getListOfPlantSpecies());
    }

    @PostMapping("/addPlantSpecies")
    public ResponseEntity<PlantSpecies> addPlantSpecies(@RequestParam(value="common_name") String common_name,
                                                        @RequestParam(value = "cycle") String cycle,
                                                        @RequestParam(value = "usual_size") Double usual_size,
                                                        @RequestParam(value = "flowering") Boolean flowering,
                                                        @RequestParam(value = "season") int season,
                                                        @RequestParam(value = "sci_name") String scientific_name,
                                                        @RequestParam(value = "photo") String photo,
                                                        @RequestParam(value = "leaf_color") String leaf_color,
                                                        @RequestParam(value = "opt_hum") int optimal_humidity,
                                                        @RequestParam(value = "opt_temp") int optimal_temperature,
                                                        @RequestParam(value = "opt_lum") int optimal_luminosity,
                                                        @RequestParam(value = "watering_freq") int watering_frequency,
                                                        @RequestParam(value = "fam_id") int family_id
                                                        ) throws ResourceNotFoundException{
        return ResponseEntity.ok().body(plantSpeciesService.addPlantSpecies(common_name, cycle, usual_size, flowering, season, scientific_name, photo, leaf_color, optimal_humidity, optimal_temperature, optimal_luminosity, watering_frequency, family_id));
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(value="adminPass") String adminPass,
                                                  @RequestParam(value = "email") String adminEmail) throws Exception {
        return ResponseEntity.ok().body(userService.getAllUsers(adminEmail, adminPass));
    }

}

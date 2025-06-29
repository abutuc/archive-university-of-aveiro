package pi.growmate.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pi.growmate.datamodel.species.OptimalHumidity;
import pi.growmate.datamodel.species.OptimalLuminosity;
import pi.growmate.datamodel.species.OptimalTemperature;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.species.Season;
import pi.growmate.datamodel.species.SpeciesFamily;
import pi.growmate.datamodel.species.WateringFrequency;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.repositories.species.PlantSpeciesRepository;
import pi.growmate.repositories.species.SpeciesFamilyRepository;

@Service
public class PlantSpeciesService {
    
    @Autowired 
    PlantSpeciesRepository plantSpeciesRepository;

    @Autowired
    SpeciesFamilyRepository speciesFamilyRepository;

    public List<PlantSpecies> getListOfPlantSpecies(){
        return plantSpeciesRepository.findAll();
    }

    public PlantSpecies addPlantSpecies(String common_name, 
                                        String cycle, 
                                        Double usual_size, 
                                        Boolean flowering, 
                                        int season, 
                                        String scientific_name, 
                                        String photo, 
                                        String leaf_color, 
                                        int optimal_humidity, 
                                        int optimal_temperature, 
                                        int optimal_luminosity, 
                                        int watering_frequency, 
                                        int family_id ) throws ResourceNotFoundException{

        PlantSpecies species = new PlantSpecies();
        species.setCommonName(common_name);
        species.setCycle(cycle);
        species.setUsualSize(usual_size);
        species.setFlowering(flowering);

        //WINTER, SPRING, SUMMER, FALL -> 0, 1, 2, 3
        switch (season) {
            case 0:
                species.setSeason(Season.WINTER);
                break;
            case 1:
                species.setSeason(Season.SPRING);
                break;
            case 2:
                species.setSeason(Season.SUMMER);
                break;
            case 3:
                species.setSeason(Season.FALL);
                break;
            default:
                throw new IllegalArgumentException("Invalid season converter integer: " + season + ". List goes from 0 (Winter) to 3 (Fall)");
        }

        species.setScientificName(scientific_name);
        species.setSpeciesPhoto(photo);
        species.setLeafColor(leaf_color);

        //LOW, MEDIUM, HIGH -> 0, 1, 2 
        switch (optimal_humidity) {
            case 0:
                species.setOptimalHumidity(OptimalHumidity.LOW);
                break;
            case 1:
                species.setOptimalHumidity(OptimalHumidity.MEDIUM);
                break;
            case 2:
                species.setOptimalHumidity(OptimalHumidity.HIGH);
                break;
            default:
                throw new IllegalArgumentException("Invalid optimal humidity converter integer: " + optimal_humidity + ". List goes from 0 (LOW) to 2 (HIGH)");
        }

        //COOL, AVERAGE, WARM -> 0, 1, 2
        switch (optimal_temperature) {
            case 0:
                species.setOptimalTemperature(OptimalTemperature.COOL);
                break;
            case 1:
                species.setOptimalTemperature(OptimalTemperature.AVERAGE);
                break;
            case 2:
                species.setOptimalTemperature(OptimalTemperature.WARM);
                break;
            default:
                throw new IllegalArgumentException("Invalid optimal temperature converter integer: " + optimal_temperature + ". List goes from 0 (COOL) to 2 (WARM)");
        }

        //LOW, MEDIUM, HIGH, SUNNY -> 0, 1, 2, 3
        switch (optimal_luminosity) {
            case 0:
                species.setOptimalLuminosity(OptimalLuminosity.LOW);
                break;
            case 1:
                species.setOptimalLuminosity(OptimalLuminosity.MEDIUM);
                break;
            case 2:
                species.setOptimalLuminosity(OptimalLuminosity.HIGH);
                break;
            case 3:
                species.setOptimalLuminosity(OptimalLuminosity.SUNNY);
                break;
            default:
                throw new IllegalArgumentException("Invalid optimal luminosity converter integer: " + optimal_luminosity + ". List goes from 0 (LOW) to 3 (SUNNY)");
        }

        //INFREQUENT, AVERAGE, FREQUENT -> 0, 1, 2
        switch (watering_frequency) {
            case 0:
                species.setWateringFrequency(WateringFrequency.INFREQUENT);
                break;
            case 1:
                species.setWateringFrequency(WateringFrequency.AVERAGE);
                break;
            case 2:
                species.setWateringFrequency(WateringFrequency.FREQUENT);
                break;
            default:
                throw new IllegalArgumentException("Invalid watering frequency converter integer: " + optimal_luminosity + ". List goes from 0 (LOW) to 3 (SUNNY)");
        }

        species.setFamily(checkIfFamilySpeciesExists(family_id));

        return plantSpeciesRepository.save(species);
    }

    private SpeciesFamily checkIfFamilySpeciesExists(long family_id) throws ResourceNotFoundException {
        return speciesFamilyRepository.findById(family_id).orElseThrow(() -> new ResourceNotFoundException("Species family with ID: " + family_id + " not found."));
   }
}

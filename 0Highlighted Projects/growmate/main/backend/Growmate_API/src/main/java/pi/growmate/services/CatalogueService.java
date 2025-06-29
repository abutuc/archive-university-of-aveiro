package pi.growmate.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.species.SpeciesFamily;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.repositories.species.PlantSpeciesRepository;
import pi.growmate.repositories.species.SpeciesFamilyRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CatalogueService {

    @Autowired
    private SpeciesFamilyRepository speciesFamilyRepository;
    @Autowired
    private PlantSpeciesRepository plantSpeciesRepository;

    public List<SpeciesFamily> getCategories() {
        return speciesFamilyRepository.findAll();
    }

    public List<PlantSpecies> getPlantsInCategory(long categoryID) throws ResourceNotFoundException {
        SpeciesFamily family = speciesFamilyRepository.findById(categoryID).orElseThrow(() -> new ResourceNotFoundException("Category with ID: " + categoryID + " not found."));

        return family.getSpecies();
    }

    public PlantSpecies getSpeciesInfo(long speciesID) throws ResourceNotFoundException{
        return plantSpeciesRepository.findById(speciesID).orElseThrow(() -> new ResourceNotFoundException("Species with ID: " + speciesID + " not found."));
    }

    public Set<PlantSpecies> findPlantSpeciesSearch(String query) {
        String lowercaseQuery = query.toLowerCase();

        List<PlantSpecies> byCommonName = plantSpeciesRepository.findAllByCommonNameContainingIgnoreCase(lowercaseQuery);
        List<PlantSpecies> byScientificName = plantSpeciesRepository.findAllByScientificNameContainingIgnoreCase(lowercaseQuery);
    
        return Stream.concat(byCommonName.stream(), byScientificName.stream()).collect(Collectors.toSet());
    }
}

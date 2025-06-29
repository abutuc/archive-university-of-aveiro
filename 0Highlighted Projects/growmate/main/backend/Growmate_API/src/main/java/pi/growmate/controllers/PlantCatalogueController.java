package pi.growmate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.species.SpeciesFamily;
import pi.growmate.exceptions.ResourceNotFoundException;
import pi.growmate.services.CatalogueService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("growmate/public/catalogue")
@CrossOrigin
public class PlantCatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    // Gets the categories of Plants
    @GetMapping("/categories")
    public ResponseEntity<List<SpeciesFamily>> getCategories() {
        return ResponseEntity.ok().body(catalogueService.getCategories());
    }

    // Gets all the Plant Species in the Catalogue belonging to a category
    @GetMapping("/categories/{categoryID}/species")
    public ResponseEntity<List<PlantSpecies>> getPlantsInCategory(@PathVariable(value = "categoryID") Long categoryID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(catalogueService.getPlantsInCategory(categoryID));
    }

    // Gets the info related to a Species
    @GetMapping("/species/{speciesID}")
    public ResponseEntity<PlantSpecies> getSpeciesInfo(@PathVariable(value = "speciesID") Long speciesID) throws ResourceNotFoundException {
        return ResponseEntity.ok().body(catalogueService.getSpeciesInfo(speciesID));
    }

    // Gets the Species matching a query
    @GetMapping("/species/")
    public ResponseEntity<Set<PlantSpecies>> getSpeciesInfo(@RequestParam(value = "query") String query) {
        return ResponseEntity.ok().body(catalogueService.findPlantSpeciesSearch(query));
    }

}

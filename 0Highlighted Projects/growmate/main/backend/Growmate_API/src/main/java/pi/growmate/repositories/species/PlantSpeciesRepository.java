package pi.growmate.repositories.species;

import org.springframework.stereotype.Repository;

import pi.growmate.datamodel.species.PlantSpecies;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PlantSpeciesRepository extends JpaRepository<PlantSpecies, Long>{
    List<PlantSpecies> findAllByCommonNameContaining(String query);
    List<PlantSpecies> findAllByScientificNameContaining(String query);
    List<PlantSpecies> findAllByCommonNameContainingIgnoreCase(String lowercaseQuery);
    List<PlantSpecies> findAllByScientificNameContainingIgnoreCase(String lowercaseQuery);
}

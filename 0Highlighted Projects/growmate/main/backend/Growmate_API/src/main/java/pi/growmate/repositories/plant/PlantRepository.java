package pi.growmate.repositories.plant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.plant.Plant;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findPlantsByDivision(Division division);
}

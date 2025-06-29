package pi.growmate.repositories.plant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.sensors.PlantSensor;

@Repository
public interface PlantSensorRepository extends JpaRepository<PlantSensor, Long> {
    PlantSensor findPlantSensorBySensorCode(String code);
}

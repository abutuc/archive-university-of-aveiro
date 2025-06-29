package pi.growmate.repositories.measurements;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.growmate.datamodel.measurements.SoilQualityMeasurement;
import pi.growmate.datamodel.sensors.PlantSensor;

import java.time.LocalDateTime;
import java.util.List;

public interface SoilQualityRepository extends JpaRepository<SoilQualityMeasurement, Long> {
    SoilQualityMeasurement findFirstBySensorOrderByPostDateDesc(PlantSensor sensor);
    List<SoilQualityMeasurement> findAllByPostDateAfterAndSensorOrderByPostDateDesc(LocalDateTime date, PlantSensor sensor);
}

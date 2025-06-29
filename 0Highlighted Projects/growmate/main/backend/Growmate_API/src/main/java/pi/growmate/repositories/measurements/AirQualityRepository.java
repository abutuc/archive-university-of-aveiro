package pi.growmate.repositories.measurements;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.growmate.datamodel.sensors.DivisionSensor;
import pi.growmate.datamodel.measurements.AirQualityMeasurement;

import java.time.LocalDateTime;
import java.util.List;


public interface AirQualityRepository extends JpaRepository<AirQualityMeasurement, Long> {
    AirQualityMeasurement findFirstBySensorOrderByPostDateDesc(DivisionSensor sensor);
    List<AirQualityMeasurement> findAllByPostDateAfterAndSensorOrderByPostDateDesc(LocalDateTime date, DivisionSensor sensor);
}

package pi.growmate.repositories.measurements;

import org.springframework.data.jpa.repository.JpaRepository;
import pi.growmate.datamodel.sensors.DivisionSensor;
import pi.growmate.datamodel.measurements.AirTemperatureMeasurement;

import java.time.LocalDateTime;
import java.util.List;

public interface AirTemperatureRepository extends JpaRepository<AirTemperatureMeasurement, Long> {
    AirTemperatureMeasurement findFirstBySensorOrderByPostDateDesc(DivisionSensor sensor);
    List<AirTemperatureMeasurement> findAllByPostDateAfterAndSensorOrderByPostDateDesc(LocalDateTime date, DivisionSensor sensor);
}

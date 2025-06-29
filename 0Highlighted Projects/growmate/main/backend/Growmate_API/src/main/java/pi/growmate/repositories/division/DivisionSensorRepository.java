package pi.growmate.repositories.division;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.growmate.datamodel.sensors.DivisionSensor;

@Repository
public interface DivisionSensorRepository extends JpaRepository<DivisionSensor, Long> {
    DivisionSensor findDivisionSensorBySensorCode(String code);
}

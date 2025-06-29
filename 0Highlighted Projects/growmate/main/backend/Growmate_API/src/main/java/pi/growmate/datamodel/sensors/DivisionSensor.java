package pi.growmate.datamodel.sensors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.forum.Comment;
import pi.growmate.datamodel.measurements.AirQualityMeasurement;
import pi.growmate.datamodel.measurements.AirTemperatureMeasurement;
import pi.growmate.datamodel.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DivisionSensor implements GenericSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private User owner;

    @Column(nullable = false)
    private String name;

    @Column(name = "sensor_code", nullable = false)
    private String sensorCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<AirQualityMeasurement> airQualityMeasurements = new ArrayList<>();

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<AirTemperatureMeasurement> airTemperatureMeasurements = new ArrayList<>();

    // Getter methods that need to be ignored on JSON replies
    @JsonIgnore
    public List<AirQualityMeasurement> getAirQualityMeasurements() {
        return airQualityMeasurements;
    }

    @JsonIgnore
    public List<AirTemperatureMeasurement> getAirTemperatureMeasurements() {
        return airTemperatureMeasurements;
    }
}

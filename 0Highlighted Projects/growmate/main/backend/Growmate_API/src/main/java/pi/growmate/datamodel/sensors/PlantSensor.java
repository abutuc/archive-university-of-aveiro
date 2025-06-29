package pi.growmate.datamodel.sensors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pi.growmate.datamodel.measurements.SoilQualityMeasurement;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlantSensor implements GenericSensor {
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
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL)
    private List<SoilQualityMeasurement> soilQualityMeasurements = new ArrayList<>();

    // Getter methods that need to be ignored on JSON replies
    @JsonIgnore
    public void setSoilQualityMeasurements(List<SoilQualityMeasurement> soilQualityMeasurements) {
        this.soilQualityMeasurements = soilQualityMeasurements;
    }
}

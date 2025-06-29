package pi.growmate.datamodel.division;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.sensors.DivisionSensor;
import pi.growmate.datamodel.species.LuminosityConverter;
import pi.growmate.datamodel.species.OptimalLuminosity;
import pi.growmate.datamodel.user.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(nullable = false)
    private String name;

    @Convert(converter = LuminosityConverter.class)
    private OptimalLuminosity luminosity;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Plant> plantsOnDivision = new ArrayList<>();

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DivisionSensor> sensors;

    // Getter methods that need to be ignored on JSON replies
    @JsonIgnore
    public List<Plant> getPlantsOnDivision() {
        return plantsOnDivision;
    }
    @JsonIgnore
    public List<DivisionSensor> getSensors() {
        return sensors;
    }
}

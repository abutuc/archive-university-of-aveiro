package pi.growmate.datamodel.species;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class PlantSpecies {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String scientificName;

    @Column
    private String commonName;

    @Column(name = "photo")
    private String speciesPhoto;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="family_id", nullable = false)
    private SpeciesFamily family;

    @Convert(converter = TemperatureConverter.class)
    private OptimalTemperature optimalTemperature;

    @Convert(converter = LuminosityConverter.class)
    private OptimalLuminosity optimalLuminosity;

    @Convert(converter = HumidityConverter.class)
    private OptimalHumidity optimalHumidity;

    @Convert(converter = WateringFrequencyConverter.class)
    private WateringFrequency wateringFrequency;

    @Max(5)
    @Min(1)
    @Column
    private Integer difficulty;

    @Column
    private String cycle;

    @Column(name = "usual_size")
    private Double usualSize;

    @Column
    private Boolean flowering;

    @Convert(converter = SeasonConverter.class)
    private Season season;

    @Column
    private String leafColor;

    @JsonIgnoreProperties("diseases")
    @ManyToMany
    @JoinTable(
            name = "disease_species",
            joinColumns = @JoinColumn(name = "species_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id"))
    Set<Disease> diseases;

    @JsonIgnore
    public Set<Disease> getDiseases() {
        return diseases;
    }
}

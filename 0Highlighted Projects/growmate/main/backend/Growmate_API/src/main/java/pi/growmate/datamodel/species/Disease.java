package pi.growmate.datamodel.species;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String scientificName;

    @Column
    private String commonName;

    @Column
    private String description;

    @Column
    private String solution;

    @ManyToMany(mappedBy = "diseases")
    private Set<PlantSpecies> speciesWithDisease;

    // Getter methods that need to be ignored on JSON replies
    @JsonIgnore
    public Set<PlantSpecies> getSpeciesWithDisease() {
        return speciesWithDisease;
    }
}

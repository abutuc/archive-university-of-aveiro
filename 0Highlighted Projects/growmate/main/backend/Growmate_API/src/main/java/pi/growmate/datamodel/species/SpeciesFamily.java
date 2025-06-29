package pi.growmate.datamodel.species;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private Integer optSoilMix;

    @Column
    private Integer difficulty;

    @Column
    private String photo;

    @JsonIgnore
    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private List<PlantSpecies> species = new ArrayList<>();

    // Getter methods that need to be ignored on JSON replies
    @JsonIgnore
    public List<PlantSpecies> getSpecies() {
        return species;
    }
}

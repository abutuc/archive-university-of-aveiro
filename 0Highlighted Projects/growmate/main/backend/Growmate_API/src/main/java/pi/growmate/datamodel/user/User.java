package pi.growmate.datamodel.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.sensors.DivisionSensor;
import pi.growmate.datamodel.sensors.GenericSensor;
import pi.growmate.datamodel.sensors.PlantSensor;
import pi.growmate.datamodel.forum.Comment;
import pi.growmate.datamodel.plant.JournalEntry;
import pi.growmate.datamodel.plant.Plant;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="utilizador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "profile_photo")
    private String profilePhoto;

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name = "experience")
    private Long exp;

    @Column(name = "dead_plant_count")
    private Long dead_plants;

    @Column
    private String address;

    @Column(columnDefinition = "integer default 3.0")
    private Double rating;

    @Convert(converter = UserTypeConverter.class)
    private UserType userType;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("owner")
    private List<Plant> plants = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("owner")
    private List<Division> divisions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> commentsByUser = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JournalEntry> journalEntries = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<PlantSensor> plantSensors = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<DivisionSensor> divisionSensors = new ArrayList<>();

    // Getter methods that need to be ignored on JSON replies
    @JsonIgnore
    public List<Plant> getPlants() {
        return plants;
    }

    @JsonIgnore
    public List<Division> getDivisions() {
        return divisions;
    }

    @JsonIgnore
    public List<Comment> getCommentsByUser() {
        return commentsByUser;
    }

    @JsonIgnore
    public List<JournalEntry> getJournalEntries() {
        return journalEntries;
    }

    @JsonIgnore
    public List<PlantSensor> getPlantSensors() {
        return plantSensors;
    }

    @JsonIgnore
    public List<DivisionSensor> getDivisionSensors() {
        return divisionSensors;
    }

    @JsonIgnore
    public List<GenericSensor> getAllSensors(){
        List<GenericSensor> allSensors = new ArrayList<>();

        allSensors.addAll(plantSensors);
        allSensors.addAll(divisionSensors);

        return allSensors;
    }
}


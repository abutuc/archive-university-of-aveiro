package pi.growmate.datamodel.plant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
import pi.growmate.datamodel.division.Division;
import pi.growmate.datamodel.forum.Comment;
import pi.growmate.datamodel.sensors.PlantSensor;
import pi.growmate.datamodel.species.PlantSpecies;
import pi.growmate.datamodel.task.Task_Settings;
import pi.growmate.datamodel.task.Tasks_Current;
import pi.growmate.datamodel.task.Tasks_History;
import pi.growmate.datamodel.user.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "photo")
    private String plantPhoto;

    @Column(name = "plantation_Date")
    @Temporal(TemporalType.DATE)
    private Date plantationDate;

    @Convert(converter = PlantConditionConverter.class)
    private PlantCondition plantCondition;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    @ToString.Exclude
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="species_id", nullable = false)
    @ToString.Exclude
    private PlantSpecies species;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="division_id")
    @ToString.Exclude
    private Division division;

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<PlantSensor> sensors = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> commentsOnPlant = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<JournalEntry> journalEntries = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Tasks_Current> currentTasks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Tasks_History> historyTasks = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Task_Settings> taskSettings = new ArrayList<>();

    // Getter methods that need to be ignored on JSON replies
    @JsonIgnore
    public List<Comment> getCommentsOnPlant() {
        return commentsOnPlant;
    }

    @JsonIgnore
    public void setJournalEntries(List<JournalEntry> journalEntries) {
        this.journalEntries = journalEntries;
    }

    @JsonIgnore
    public List<PlantSensor> getSensors() {
        return sensors;
    }

    @JsonIgnore
    public List<Tasks_Current> getCurrentTasks() {
        return currentTasks;
    }

    @JsonIgnore
    public List<Tasks_History> getHistoryTasks() {
        return historyTasks;
    }

    @JsonIgnore
    public List<Task_Settings> getTaskSettings() {
        return taskSettings;
    }

    @JsonIgnore
    public void addSensor(PlantSensor sensor){
        this.sensors.add(sensor);
    }

    @JsonIgnore
    public void removeSensor(PlantSensor sensor){
        this.sensors.removeIf(psensor -> psensor.getId().equals(sensor.getId()));
    }

    @JsonIgnore
    public void removeAllSensors(){
        this.sensors.clear();
    }
}

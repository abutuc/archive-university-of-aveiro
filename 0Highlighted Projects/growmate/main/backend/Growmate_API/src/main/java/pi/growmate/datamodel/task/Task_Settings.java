package pi.growmate.datamodel.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pi.growmate.datamodel.plant.Plant;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task_Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @Convert(converter = TaskTypeConverter.class)
    private TaskType taskType;

    @Column
    private int taskFrequency;

    @Column
    private boolean isAutomatic;

    public void toggleMode(){
        this.isAutomatic = !this.isAutomatic;
    }

    public boolean isTaskAutomatic(){return this.isAutomatic;}
}

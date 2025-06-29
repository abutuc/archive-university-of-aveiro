package pi.growmate.datamodel.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pi.growmate.datamodel.plant.Plant;

import java.sql.Date;


@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tasks_Current {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @Column(nullable = false)
    private String name;

    @Column(name = "task_date")
    @Temporal(TemporalType.DATE)
    private Date taskDate;

    @Convert(converter = TaskTypeConverter.class)
    private TaskType taskType;
}

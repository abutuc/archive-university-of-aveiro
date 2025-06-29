package pi.growmate.datamodel.plant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pi.growmate.datamodel.plant.Plant;
import pi.growmate.datamodel.user.User;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="plant_id", nullable = false)
    private Plant plant;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String text;

    @Column
    private String photo;

    @Column(name = "post_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime postDate;
}

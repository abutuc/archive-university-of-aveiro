package pi.growmate.datamodel.forum;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pi.growmate.datamodel.user.User;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reaction {
    @EmbeddedId
    private ReactionID id;

    @ManyToOne
    @MapsId("userID")
    private User user;

    @ManyToOne
    @MapsId("commentID")
    private Comment comment;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime reactionDate;

    @Column
    private Boolean type;                           // 0 - Thumbs Down; 1 - Thumbs Up
}

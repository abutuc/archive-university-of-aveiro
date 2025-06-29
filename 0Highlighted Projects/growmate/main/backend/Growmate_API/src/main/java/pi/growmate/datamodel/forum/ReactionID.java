package pi.growmate.datamodel.forum;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@EqualsAndHashCode
public class ReactionID implements Serializable {
    private Long userID;
    private Long commentID;
}

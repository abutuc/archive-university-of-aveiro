package pi.growmate.repositories.division;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.growmate.datamodel.division.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Long> {
}

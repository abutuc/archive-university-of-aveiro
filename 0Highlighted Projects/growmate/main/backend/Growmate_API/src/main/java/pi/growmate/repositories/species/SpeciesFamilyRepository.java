package pi.growmate.repositories.species;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pi.growmate.datamodel.species.SpeciesFamily;

@Repository
public interface SpeciesFamilyRepository extends JpaRepository<SpeciesFamily, Long> {
}

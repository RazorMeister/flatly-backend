package pw2021.backend.Flatly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pw2021.backend.Flatly.enities.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
}

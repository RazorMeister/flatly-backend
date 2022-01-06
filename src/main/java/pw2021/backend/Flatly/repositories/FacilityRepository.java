package pw2021.backend.Flatly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pw2021.backend.Flatly.enities.Facility;

import java.util.Optional;

import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {
    @Query("SELECT f FROM Facility f WHERE f.name = ?1")
    Optional<Facility> getFacilityByName(String name);
}

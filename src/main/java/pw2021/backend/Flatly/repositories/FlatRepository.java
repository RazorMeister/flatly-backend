package pw2021.backend.Flatly.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pw2021.backend.Flatly.enities.Flat;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    @Query("select flat from Flat flat where flat.name LIKE %:search% OR flat.description LIKE %:search%")
    Page<Flat> findFlatsByName(String search, Pageable pageable);
}

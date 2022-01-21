package pw2021.backend.Flatly.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pw2021.backend.Flatly.enities.Flat;

import java.time.LocalDateTime;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    @Query("select flat from Flat flat where flat.address.streetName LIKE %:street% AND flat.address.city LIKE %:city% AND flat.name LIKE %:name%")
    Page<Flat> findFlats(String name, String city, String street, Pageable pageable);

    @Query("select flat from Flat flat where flat.address.streetName LIKE %:street% AND flat.address.city LIKE %:city% AND flat.name LIKE %:name% order by flat.rooms")
    Page<Flat> findFlatsSortedAscending(String name, String city, String street, Pageable pageable);

    @Query("select flat from Flat flat where flat.address.streetName LIKE %:street% AND flat.address.city LIKE %:city% AND flat.name LIKE %:name% order by flat.rooms desc")
    Page<Flat> findFlatsSortedDescending(String name, String city, String street, Pageable pageable);

    @Modifying
    @Query("UPDATE Flat flat SET flat.active = false WHERE flat.endDateTime <= :dateTime")
    void setInactiveAfterEndDateTime(@Param("dateTime") LocalDateTime dateTime);
}

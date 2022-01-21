package pw2021.backend.Flatly.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pw2021.backend.Flatly.enities.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select booking from Booking booking where booking.userData LIKE %:search% and booking.active = :active")
    Page<Booking> findBookingsByNameAndActive(String search, boolean active, Pageable pageable);

    @Query("select booking from Booking booking where booking.flat.id = :id")
    Page<Booking> findBookingsById(Long id, Pageable pageable);
}

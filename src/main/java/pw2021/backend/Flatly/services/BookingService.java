package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.Booking;
import pw2021.backend.Flatly.exceptions.NotFoundException;
import pw2021.backend.Flatly.exceptions.UnprocessableEntityException;
import pw2021.backend.Flatly.repositories.BookingRepository;
import pw2021.backend.Flatly.responses.PaginationData;
import pw2021.backend.Flatly.responses.PaginationResponse;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    private final Integer RECORDS_ON_PAGE = 10;

    @Autowired
    public BookingService(
            BookingRepository bookingRepository
    ) {
        this.bookingRepository = bookingRepository;
    }

    public PaginationResponse<List<Booking>> getBookings(
            Optional<Integer> page,
            Optional<String> search,
            Optional<Boolean> active
    ) {
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, this.RECORDS_ON_PAGE);
        Page<Booking> bookingsPage = this.bookingRepository
                .findBookingsByNameAndActive(search.orElse(""), active.orElse(true), pageable);

        return new PaginationResponse<>(
                bookingsPage.getContent(),
                new PaginationData(bookingsPage)
        );
    }

    public Booking getBooking(long id) throws NotFoundException {
        Optional<Booking> bookingOptional = this.bookingRepository.findById(id);
        if (bookingOptional.isEmpty()) {
            throw new NotFoundException(String.format("Booking with id: %s does not exist", id));
        }

        return bookingOptional.get();
    }

    @Transactional
    public Booking storeBooking(Booking booking) throws UnprocessableEntityException {
        return booking;
    }

    public String cancelBooking(long id) throws NotFoundException, UnprocessableEntityException {
        Booking booking = this.getBooking(id);
        if (!booking.getActive()) {
            throw new UnprocessableEntityException("Booking has been already canceled");
        }
        booking.setActive(false);
        this.bookingRepository.save(booking);
        return "Booking canceled";
    }
}

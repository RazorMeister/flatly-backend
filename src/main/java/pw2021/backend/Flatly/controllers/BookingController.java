package pw2021.backend.Flatly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import pw2021.backend.Flatly.enities.Booking;
import pw2021.backend.Flatly.exceptions.NotFoundException;
import pw2021.backend.Flatly.exceptions.UnauthorizedException;
import pw2021.backend.Flatly.exceptions.UnprocessableEntityException;
import pw2021.backend.Flatly.responses.PaginationResponse;
import pw2021.backend.Flatly.services.BookingService;
import pw2021.backend.Flatly.services.SecurityProvider;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "bookings")
public class BookingController {
    private final BookingService bookingService;
    private final SecurityProvider securityService;

    @Autowired
    public BookingController(BookingService bookingService, SecurityProvider securityService) {
        this.bookingService = bookingService;
        this.securityService = securityService;
    }

    @GetMapping
    public PaginationResponse<List<Booking>> getBookings(
            @RequestHeader HttpHeaders headers,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("search") Optional<String> search,
            @RequestParam("id") Optional<Long> id,
            @RequestParam("active") Optional<Boolean> active
    ) throws UnauthorizedException {
        this.securityService.checkAuthenticated(headers);
        return this.bookingService.getBookings(page, search, id, active);
    }

    @GetMapping(path = "{bookingId}")
    public Booking getBooking(@RequestHeader HttpHeaders headers, @PathVariable long bookingId)
            throws NotFoundException, UnauthorizedException {
        this.securityService.checkAuthenticated(headers);
        return this.bookingService.getBooking(bookingId);
    }

    @DeleteMapping(path = "{bookingId}")
    public String cancelBooking(@RequestHeader HttpHeaders headers, @PathVariable long bookingId)
            throws UnauthorizedException, UnprocessableEntityException, NotFoundException {
        this.securityService.checkAuthenticated(headers);
        return this.bookingService.cancelBooking(bookingId);
    }

    @PostMapping
    public Booking storeBooking(@RequestHeader HttpHeaders headers, @RequestBody Booking booking)
            throws UnprocessableEntityException, UnauthorizedException, NotFoundException {
        this.securityService.checkAuthenticated(headers);
        return this.bookingService.storeBooking(booking);
    }
}

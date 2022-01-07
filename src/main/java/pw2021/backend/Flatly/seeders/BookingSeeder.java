package pw2021.backend.Flatly.seeders;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import pw2021.backend.Flatly.enities.Address;
import pw2021.backend.Flatly.enities.Booking;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.repositories.BookingRepository;
import pw2021.backend.Flatly.utils.DataConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BookingSeeder extends BasicSeeder<BookingRepository, Booking> {
    @Override
    protected List<Booking> getSeeders() {
        Faker faker = new Faker();

        List<Booking> bookings = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Address newAddress = new Address(
                    faker.address().streetName(),
                    faker.address().buildingNumber(),
                    faker.address().buildingNumber(),
                    faker.address().zipCode(),
                    faker.address().city()
            );

            Flat newFlat = new Flat(
                    faker.funnyName().name(),
                    faker.number().numberBetween(3, 100),
                    faker.number().numberBetween(3, 100),
                    true,
                    DataConverter.convertToLocalDateTime(new Date()),
                    DataConverter.convertToLocalDateTime(DateUtils.addMonths(new Date(), 3)),
                    faker.number().numberBetween(30, 300),
                    faker.shakespeare().hamletQuote(),
                    newAddress
            );

            Booking newBooking = new Booking(
                String.format("%s %s %s",
                        faker.name().firstName(), faker.name().lastName(), faker.phoneNumber().cellPhone()),
                DataConverter.convertToLocalDateTime(DateUtils.addDays(new Date(), faker.random().nextInt(0, 50))),
                DataConverter.convertToLocalDateTime(DateUtils.addDays(new Date(), faker.random().nextInt(50, 100))),
                newFlat,
                faker.bool().bool()
            );

            bookings.add(newBooking);
        }

        return bookings;
    }
}

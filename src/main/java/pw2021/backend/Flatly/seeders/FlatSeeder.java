package pw2021.backend.Flatly.seeders;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;
import pw2021.backend.Flatly.enities.Address;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.repositories.FlatRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlatSeeder extends BasicSeeder<FlatRepository, Flat> {
    @Override
    protected List<Flat> getSeeders() {
        Faker faker = new Faker();

        List<Flat> flats = new ArrayList<>();

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
                    faker.number().numberBetween(30, 300),
                    faker.shakespeare().hamletQuote(),
                    newAddress
            );

            flats.add(newFlat);
        }

        return flats;
    }
}

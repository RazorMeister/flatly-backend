package pw2021.backend.Flatly.seeders;

import com.github.javafaker.Faker;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import pw2021.backend.Flatly.enities.Address;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.enities.Image;
import pw2021.backend.Flatly.repositories.FlatRepository;
import pw2021.backend.Flatly.utils.DataConverter;

import java.util.*;

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

            Image img1 = new Image("https://backend.flatly.online/api/v1/flatsStock/inside1.jpg");
            Image img2 = new Image("https://backend.flatly.online/api/v1/flatsStock/inside2.jpg");
            Image img3 = new Image("https://backend.flatly.online/api/v1/flatsStock/outside1.jpg");

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

            newFlat.setImages(new LinkedHashSet<Image>(Arrays.asList(img1, img2, img3)));
            flats.add(newFlat);
        }

        return flats;
    }
}

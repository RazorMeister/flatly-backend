package pw2021.backend.Flatly.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private final List<Runnable> seeders = new ArrayList<>();

    @Autowired
    public DatabaseSeeder(
            UserSeeder userSeeder,
            FacilitySeeder facilitySeeder,
            FlatSeeder flatSeeder,
            FlatFacilitySeeder flatFacilitySeeder,
            BookingSeeder bookingSeeder
    ) {
        this.seeders.add(userSeeder);
        this.seeders.add(facilitySeeder);
        this.seeders.add(flatSeeder);
        this.seeders.add(bookingSeeder);
        this.seeders.add(flatFacilitySeeder);
    }

    @Override
    @Transactional
    public void run(String... args) {
        this.seeders.forEach(Runnable::run);
    }
}

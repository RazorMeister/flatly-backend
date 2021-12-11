package pw2021.backend.Flatly.seeders;

import org.springframework.stereotype.Component;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.repositories.FacilityRepository;

import java.util.List;

@Component
public class FacilitySeeder extends BasicSeeder<FacilityRepository, Facility> {
    @Override
    protected List<Facility> getSeeders() {
        return List.of(
                new Facility("Parking Space"),
                new Facility("Free WiFi"),
                new Facility("TV"),
                new Facility("Lift"),
                new Facility("Air conditioning"),
                new Facility("Gym"),
                new Facility("Playground"),
                new Facility("Pet-friendly"),
                new Facility("Electric Vehicle Charging Station")
        );
    }
}

package pw2021.backend.Flatly.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.repositories.FacilityRepository;
import pw2021.backend.Flatly.repositories.FlatRepository;

import java.util.*;

@Component
public class FlatFacilitySeeder implements Runnable {
    private final FlatRepository flatRepository;
    private final FacilityRepository facilityRepository;

    @Autowired
    public FlatFacilitySeeder(FlatRepository flatRepository, FacilityRepository facilityRepository) {
        this.flatRepository = flatRepository;
        this.facilityRepository = facilityRepository;
    }

    @Override
    public void run() {
        List<Flat> flats = this.flatRepository.findAll();
        List<Facility> facilities = this.facilityRepository.findAll();

        for (Flat flat : flats) {
            if (flat.getFacilities().size() > 0) continue;

            Collections.shuffle(facilities);
            flat.setFacilities(new HashSet<>(facilities.subList(0, 3)));
            this.flatRepository.save(flat);
        }
    }
}

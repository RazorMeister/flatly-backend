package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.exceptions.NotFoundException;
import pw2021.backend.Flatly.repositories.FacilityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> getFacilities() {
        return this.facilityRepository.findAll();
    }

    public Facility getFacility(Long id) throws NotFoundException {
        Optional<Facility> facilityOptional = this.facilityRepository.findById(id);

        if (facilityOptional.isEmpty()) {
            throw new NotFoundException(String.format("Facility with id: %d does not exist", id));
        }

        return facilityOptional.get();
    }
}

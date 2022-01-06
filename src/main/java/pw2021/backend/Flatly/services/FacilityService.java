package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.exceptions.BadRequestException;
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

    public Facility getFacilityByName(String name) throws NotFoundException {
        Optional<Facility> facilityOptional = this.facilityRepository.getFacilityByName(name);

        if (facilityOptional.isEmpty())
            throw new NotFoundException((String.format("Facility with name: %s does not exist", name)));

        return facilityOptional.get();
    }

    public Facility storeFacility(Facility facility) throws BadRequestException {
        try {
            this.getFacilityByName(facility.getName());
        } catch (NotFoundException e) {
            // if facility is not found add it to the database
            return this.facilityRepository.save(facility);
        }

        throw new BadRequestException(String.format("Facility with the name: %s already exists!", facility.getName()));
    }

    public Facility updateFacility(long facilityId, Facility facility) throws NotFoundException, BadRequestException {
        Facility f = this.getFacility(facilityId);
        f.setName(facility.getName());

        return this.storeFacility(f);
    }

    public String deleteFacility(long facilityId) throws NotFoundException {
        this.facilityRepository.delete(this.getFacility(facilityId));
        return "deleted";
    }
}

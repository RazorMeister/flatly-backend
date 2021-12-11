package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.exceptions.NotFoundException;
import pw2021.backend.Flatly.exceptions.UnprocessableEntityException;
import pw2021.backend.Flatly.repositories.FacilityRepository;
import pw2021.backend.Flatly.repositories.FlatRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FlatService {
    private final FlatRepository flatRepository;
    private final FacilityService facilityService;

    @Autowired
    public FlatService(FlatRepository flatRepository, FacilityService facilityService) {
        this.flatRepository = flatRepository;
        this.facilityService = facilityService;
    }

    private Flat saveWithFacilities(Flat flat) throws UnprocessableEntityException {
        Set<Facility> facilitiesToSave = flat.getFacilities();
        flat.setFacilities(new HashSet<>());

        Flat savedFlat = this.flatRepository.save(flat);

        for (Facility facility : facilitiesToSave) {
            try {
                savedFlat.addFacility(this.facilityService.getFacility(facility.getId()));
            } catch (NotFoundException e) {
                throw new UnprocessableEntityException(e);
            }
        }

        return this.flatRepository.save(savedFlat);
    }

    public List<Flat> getFlats() {
        return this.flatRepository.findAll();
    }

    public Flat getFlat(long id) throws NotFoundException {
        Optional<Flat> flatOptional = this.flatRepository.findById(id);
        if (flatOptional.isEmpty()) {
            throw new NotFoundException(String.format("Flat with id: %s does not exist", id));
        }

        return flatOptional.get();
    }

    @Transactional
    public Flat storeFlat(Flat flat) throws UnprocessableEntityException {
        return this.saveWithFacilities(flat);
    }

    @Transactional
    public Flat updateFlat(long flatId, Flat newFlat) throws NotFoundException, UnprocessableEntityException {
        Flat flat = this.getFlat(flatId);

        flat.setName(newFlat.getName());
        flat.setDescription(newFlat.getDescription());
        flat.setRooms(newFlat.getRooms());
        flat.setArea(newFlat.getArea());
        flat.setAddress(newFlat.getAddress());
        flat.setFacilities(newFlat.getFacilities());

        return this.saveWithFacilities(flat);
    }

    public void deleteFlat(long flatId) throws NotFoundException {
        Flat flat = this.getFlat(flatId);
        this.flatRepository.delete(flat);
    }
}

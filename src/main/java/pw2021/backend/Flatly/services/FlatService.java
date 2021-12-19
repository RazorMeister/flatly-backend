package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.enities.Image;
import pw2021.backend.Flatly.exceptions.NotFoundException;
import pw2021.backend.Flatly.exceptions.UnprocessableEntityException;
import pw2021.backend.Flatly.repositories.FlatRepository;
import pw2021.backend.Flatly.responses.PaginationData;
import pw2021.backend.Flatly.responses.PaginationResponse;
import pw2021.backend.Flatly.utils.DataConverter;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class FlatService {
    private final FlatRepository flatRepository;
    private final FacilityService facilityService;
    private final ImageService imageService;

    private final Integer RECORDS_ON_PAGE = 10;

    @Autowired
    public FlatService(
            FlatRepository flatRepository,
            FacilityService facilityService,
            ImageService imageService
    ) {
        this.flatRepository = flatRepository;
        this.facilityService = facilityService;
        this.imageService = imageService;
    }

    private Flat saveWithFacilitiesAndImages(Flat flat) throws UnprocessableEntityException {
        Set<Facility> facilitiesToSave = flat.getFacilities();
        Set<Image> imagesToSave = flat.getImages();
        flat.setFacilities(new HashSet<>());

        flat.setImages(new HashSet<>());

        Flat savedFlat = this.flatRepository.save(flat);

        for (Facility facility : facilitiesToSave) {
            try {
                savedFlat.addFacility(this.facilityService.getFacility(facility.getId()));
            } catch (NotFoundException e) {
                throw new UnprocessableEntityException(e);
            }
        }

        for (Image image : imagesToSave) {
            try {
                savedFlat.addImage(this.imageService.getImage(image.getId()));
            } catch (NotFoundException e) {
                throw new UnprocessableEntityException(e);
            }
        }

        return this.flatRepository.save(savedFlat);
    }

    public PaginationResponse<List<Flat>> getFlats(Optional<Integer> page, Optional<String> search) {
        Pageable pageable = PageRequest.of(page.orElse(1) - 1, this.RECORDS_ON_PAGE);
        Page<Flat> flatsPage = this.flatRepository.findFlatsByName(search.orElse(""), pageable);
        return new PaginationResponse<>(
                flatsPage.getContent(),
                new PaginationData(flatsPage)
        );
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
        return this.saveWithFacilitiesAndImages(flat);
    }

    @Transactional
    public Flat updateFlat(long flatId, Flat newFlat) throws NotFoundException, UnprocessableEntityException {
        Flat flat = this.getFlat(flatId);

        flat.setName(newFlat.getName());
        flat.setDescription(newFlat.getDescription());
        flat.setNumberOfGuests(newFlat.getNumberOfGuests());
        flat.setActive(newFlat.getActive());
        flat.setStartDateTime(newFlat.getStartDateTime());
        flat.setEndDateTime(newFlat.getEndDateTime());
        flat.setRooms(newFlat.getRooms());
        flat.setArea(newFlat.getArea());
        flat.setAddress(newFlat.getAddress());
        flat.setFacilities(newFlat.getFacilities());
        flat.setImages(newFlat.getImages());

        return this.saveWithFacilitiesAndImages(flat);
    }

    @Transactional
    public String deleteFlat(long flatId) throws NotFoundException {
        Flat flat = this.getFlat(flatId);
        flat.setFacilities(new HashSet<Facility>());
        flat.setImages(new HashSet<Image>());
        this.flatRepository.delete(flat);
        return "deleted";
    }

    @Transactional
    public void setFlatsInactiveAfterEndDateTime() {
        this.flatRepository.setInactiveAfterEndDateTime(DataConverter.convertToLocalDateTime(new Date()));
    }
}

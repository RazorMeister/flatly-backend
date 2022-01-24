package pw2021.backend.Flatly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.exceptions.*;
import pw2021.backend.Flatly.services.FacilityService;
import pw2021.backend.Flatly.services.SecurityProvider;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "facilities")
public class FacilityController {
    private final FacilityService facilityService;
    private final SecurityProvider securityService;

    @Autowired
    public FacilityController(FacilityService facilityService, SecurityProvider securityService) {
        this.facilityService = facilityService;
        this.securityService = securityService;
    }

    @GetMapping
    public List<Facility> getFacilities(@RequestHeader HttpHeaders headers) throws UnauthorizedException, ForbiddenException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.facilityService.getFacilities();
    }

    @GetMapping(path = "{name}")
    public Facility getFacilityByName(
            @RequestHeader HttpHeaders headers,
            @PathVariable String name) throws UnauthorizedException, NotFoundException, ForbiddenException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.facilityService.getFacilityByName(name);
    }

    @PostMapping
    public Facility storeFacility(@RequestHeader HttpHeaders headers, @RequestBody Facility facility)
            throws UnprocessableEntityException, UnauthorizedException, BadRequestException, ForbiddenException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.facilityService.storeFacility(facility);
    }

    @PutMapping(path = "{facilityId}")
    public Facility updateFacility(@RequestHeader HttpHeaders headers, @PathVariable long facilityId, @RequestBody Facility facility)
            throws NotFoundException, UnprocessableEntityException, UnauthorizedException, BadRequestException, ForbiddenException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.facilityService.updateFacility(facilityId, facility);
    }

    @DeleteMapping(path = "{facilityId}")
    public String deleteFacility(@RequestHeader HttpHeaders headers, @PathVariable long facilityId)
            throws NotFoundException, UnauthorizedException, ForbiddenException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.facilityService.deleteFacility(facilityId);
    }
}

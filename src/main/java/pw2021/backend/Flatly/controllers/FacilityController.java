package pw2021.backend.Flatly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import pw2021.backend.Flatly.enities.Facility;
import pw2021.backend.Flatly.exceptions.UnauthorizedException;
import pw2021.backend.Flatly.services.FacilityService;
import pw2021.backend.Flatly.services.SecurityProvider;

import java.util.List;

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
    public List<Facility> getFacilities(@RequestHeader HttpHeaders headers) throws UnauthorizedException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.facilityService.getFacilities();
    }
}

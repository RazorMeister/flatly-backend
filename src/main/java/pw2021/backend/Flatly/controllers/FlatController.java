package pw2021.backend.Flatly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import pw2021.backend.Flatly.enities.Flat;
import pw2021.backend.Flatly.exceptions.NotFoundException;
import pw2021.backend.Flatly.exceptions.UnauthorizedException;
import pw2021.backend.Flatly.exceptions.UnprocessableEntityException;
import pw2021.backend.Flatly.services.FlatService;
import pw2021.backend.Flatly.services.SecurityProvider;

import java.util.List;

@RestController
@RequestMapping(path = "flats")
public class FlatController {
    private final FlatService flatService;
    private final SecurityProvider securityService;

    @Autowired
    public FlatController(FlatService flatService, SecurityProvider securityService) {
        this.flatService = flatService;
        this.securityService = securityService;
    }

    @GetMapping
    public List<Flat> getFlats(@RequestHeader HttpHeaders headers) throws UnauthorizedException {
        this.securityService.checkAuthenticated(headers);
        return this.flatService.getFlats();
    }

    @GetMapping(path = "{flatId}")
    public Flat getFlat(@RequestHeader HttpHeaders headers, @PathVariable long flatId)
            throws NotFoundException, UnauthorizedException {
        this.securityService.checkAuthenticated(headers);
        return this.flatService.getFlat(flatId);
    }

    @PostMapping
    public Flat storeFlat(@RequestHeader HttpHeaders headers, @RequestBody Flat flat)
            throws UnprocessableEntityException, UnauthorizedException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.flatService.storeFlat(flat);
    }

    @PutMapping(path = "{flatId}")
    public Flat updateFlat(@RequestHeader HttpHeaders headers, @PathVariable long flatId, @RequestBody Flat flat)
            throws NotFoundException, UnprocessableEntityException, UnauthorizedException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        return this.flatService.updateFlat(flatId, flat);
    }

    @DeleteMapping(path = "{flatId}")
    public void deleteFlat(@RequestHeader HttpHeaders headers, @PathVariable long flatId)
            throws NotFoundException, UnauthorizedException {
        this.securityService.checkAuthenticatedAndMainAdmin(headers);
        this.flatService.deleteFlat(flatId);
    }
}

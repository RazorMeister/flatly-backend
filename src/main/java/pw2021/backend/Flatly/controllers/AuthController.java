package pw2021.backend.Flatly.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pw2021.backend.Flatly.enities.SecurityToken;
import pw2021.backend.Flatly.exceptions.UnauthorizedException;
import pw2021.backend.Flatly.requests.LoginRequest;
import pw2021.backend.Flatly.services.AuthService;

@RestController
@RequestMapping(path = "auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "login")
    public SecurityToken login(@RequestBody LoginRequest loginRequest) throws UnauthorizedException {
        return this.authService.login(loginRequest);
    }
}

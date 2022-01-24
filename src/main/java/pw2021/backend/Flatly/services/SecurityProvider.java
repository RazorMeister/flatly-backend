package pw2021.backend.Flatly.services;

import org.springframework.http.HttpHeaders;
import pw2021.backend.Flatly.exceptions.ForbiddenException;
import pw2021.backend.Flatly.exceptions.UnauthorizedException;

public interface SecurityProvider {
    void checkAuthenticated(HttpHeaders headers) throws UnauthorizedException;
    void checkAuthenticatedAndMainAdmin(HttpHeaders headers) throws UnauthorizedException, ForbiddenException;
}

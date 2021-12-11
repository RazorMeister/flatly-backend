package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.SecurityToken;
import pw2021.backend.Flatly.exceptions.UnauthorizedException;
import pw2021.backend.Flatly.repositories.SecurityTokenRepository;

@Service
class SecurityService implements SecurityProvider {
    private final SecurityTokenRepository securityTokenRepository;
    private static final String SECURITY_HEADER = "Authentication";

    @Autowired
    SecurityService(SecurityTokenRepository securityTokenRepository) {
        this.securityTokenRepository = securityTokenRepository;
    }

    public boolean isAuthenticated(HttpHeaders headers) {
        if (headers == null || !headers.containsKey(SECURITY_HEADER)) return false;

        String headerValue = headers.getFirst(SECURITY_HEADER);
        SecurityToken securityToken = this.securityTokenRepository.findSecurityTokenByValue(headerValue);

        return securityToken != null;
    }

    @Override
    public void checkAuthenticated(HttpHeaders headers) throws UnauthorizedException {
        if (!this.isAuthenticated(headers)) {
            throw new UnauthorizedException("You are not authenticated");
        }
    }

    @Override
    public void checkAuthenticatedAndMainAdmin(HttpHeaders headers) throws UnauthorizedException {
        if (!this.isAuthenticated(headers)) {
            throw new UnauthorizedException("You are not authenticated");
        }
    }
}

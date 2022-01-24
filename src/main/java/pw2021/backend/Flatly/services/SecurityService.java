package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.SecurityToken;
import pw2021.backend.Flatly.exceptions.ForbiddenException;
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

    public SecurityToken getSecurityToken(HttpHeaders headers) {
        if (headers == null || !headers.containsKey(SECURITY_HEADER)) return null;

        String headerValue = headers.getFirst(SECURITY_HEADER);
        return this.securityTokenRepository.findSecurityTokenByValue(headerValue);
    }

    @Override
    public void checkAuthenticated(HttpHeaders headers) throws UnauthorizedException {
        if (this.getSecurityToken(headers) == null) {
            throw new UnauthorizedException("You are not authenticated");
        }
    }

    @Override
    public void checkAuthenticatedAndMainAdmin(HttpHeaders headers) throws UnauthorizedException, ForbiddenException {
        SecurityToken securityToken = this.getSecurityToken(headers);

        if (securityToken == null) {
            throw new UnauthorizedException("You are not authenticated");
        }

        if (!securityToken.getUser().getIsAdmin()) {
            throw new ForbiddenException("You do not have insufficient permissions");
        }
    }
}

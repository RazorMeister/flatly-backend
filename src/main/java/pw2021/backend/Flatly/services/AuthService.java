package pw2021.backend.Flatly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pw2021.backend.Flatly.enities.SecurityToken;
import pw2021.backend.Flatly.enities.User;
import pw2021.backend.Flatly.exceptions.UnauthorizedException;
import pw2021.backend.Flatly.repositories.SecurityTokenRepository;
import pw2021.backend.Flatly.repositories.UserRepository;
import pw2021.backend.Flatly.requests.LoginRequest;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final SecurityTokenRepository securityTokenRepository;

    @Autowired
    public AuthService(UserRepository userRepository, SecurityTokenRepository securityTokenRepository) {
        this.userRepository = userRepository;
        this.securityTokenRepository = securityTokenRepository;
    }

    public SecurityToken login(LoginRequest loginRequest) throws UnauthorizedException {
        User user = this.userRepository.findByLoginAndPassword(loginRequest.getLogin(), loginRequest.getPassword());
        if (user == null) {
            throw new UnauthorizedException("Login or password is invalid");
        }

        SecurityToken securityToken = new SecurityToken(UUID.randomUUID().toString(), user);

        return this.securityTokenRepository.save(securityToken);
    }
}

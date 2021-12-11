package pw2021.backend.Flatly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pw2021.backend.Flatly.enities.SecurityToken;
import pw2021.backend.Flatly.enities.User;

@Repository
public interface SecurityTokenRepository extends JpaRepository<SecurityToken, Long> {
    SecurityToken findSecurityTokenByValue(String value);
}

package pw2021.backend.Flatly.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pw2021.backend.Flatly.enities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login = ?1 AND u.password = ?2")
    User findByLoginAndPassword(String login, String password);
}

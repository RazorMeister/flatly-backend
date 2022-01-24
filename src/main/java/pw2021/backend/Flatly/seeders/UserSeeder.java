package pw2021.backend.Flatly.seeders;

import org.springframework.stereotype.Component;
import pw2021.backend.Flatly.enities.User;
import pw2021.backend.Flatly.repositories.UserRepository;

import java.util.List;

@Component
public class UserSeeder extends BasicSeeder<UserRepository, User> {
    @Override
    protected List<User> getSeeders() {
        return List.of(
                new User("john@doe.com", "password#1234", "Main", "Admin", true),
                new User("external@admin.com", "password", "External", "Admin", false)
        );
    }
}

package pw2021.backend.Flatly.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pw2021.backend.Flatly.enities.User;
import pw2021.backend.Flatly.repositories.UserRepository;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
           /* User user1 = new User("login1", "password", "Jan", "Kowalski");
            User user2 = new User("login2", "password", "Adrian", "Nowak");

            userRepository.saveAll(List.of(user1, user2));*/
        };
    }
}

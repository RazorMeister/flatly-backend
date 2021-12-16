package pw2021.backend.Flatly.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import pw2021.backend.Flatly.services.FlatService;

@Configuration
@EnableScheduling
public class Scheduler {
    private final FlatService flatService;

    @Autowired
    public Scheduler(FlatService flatService) {
        this.flatService = flatService;
    }

    @Scheduled(fixedDelay = 60000) // One minute
    public void setFlatsInactiveAfterEndDateTime() {
        this.flatService.setFlatsInactiveAfterEndDateTime();
    }
}

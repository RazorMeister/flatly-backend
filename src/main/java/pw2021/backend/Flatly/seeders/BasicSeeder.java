package pw2021.backend.Flatly.seeders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BasicSeeder <T extends JpaRepository<Y, ?>, Y> implements Runnable {
    protected T repository;

    @Autowired
    public final void setRepository(T repository) {
        this.repository = repository;
    }

    @Override
    public void run() {
        if (this.repository.count() == 0) this.seed();
    }

    protected void seed() {
        this.repository.saveAll(this.getSeeders());
    }

    protected abstract List<Y> getSeeders();
}

package pw2021.backend.Flatly.enities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pw2021.backend.Flatly.utils.JsonDateDeserializer;
import pw2021.backend.Flatly.utils.JsonDateSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userData;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime startDateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flatId")
    private Flat flat;

    private boolean active;

    public Booking() {
    }

    public Booking(
            String userData,
            LocalDateTime startDateTime,
            Flat flat,
            boolean active
    ) {
        this.userData = userData;
        this.startDateTime = startDateTime;
        this.flat = flat;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", userData='" + userData + '\'' +
                ", startDateTime=" + startDateTime +
                ", flat=" + flat +
                ", active=" + active +
                '}';
    }
}

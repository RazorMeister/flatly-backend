package pw2021.backend.Flatly.enities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pw2021.backend.Flatly.utils.JsonDateDeserializer;
import pw2021.backend.Flatly.utils.JsonDateSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flats")
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer rooms;
    private Integer numberOfGuests;
    private Boolean active;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime startDateTime;
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDateTime endDateTime;

    private Integer area;
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "flat_facility",
            joinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flat_id", referencedColumnName = "id")
    )
    private Set<Facility> facilities = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "flat_image",
            joinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "flat_id", referencedColumnName = "id")
    )
    private Set<Image> images = new HashSet<>();

    public Flat() {
    }

    public Flat(String name, Integer rooms, Integer numberOfGuests, Boolean active, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer area, String description, Address address, Set<Facility> facilities, Set<Image> images) {
        this.name = name;
        this.rooms = rooms;
        this.numberOfGuests = numberOfGuests;
        this.active = active;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.area = area;
        this.description = description;
        this.address = address;
        this.facilities = facilities;
        this.images = images;
    }

    public Flat(String name, Integer rooms, Integer numberOfGuests, Boolean active, LocalDateTime startDateTime, LocalDateTime endDateTime, Integer area, String description, Address address) {
        this.name = name;
        this.rooms = rooms;
        this.numberOfGuests = numberOfGuests;
        this.active = active;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.area = area;
        this.description = description;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(Integer numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (this.address != null) {
            address.setId(this.address.getId());
        }

        this.address = address;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    public void addFacility(Facility facility) {
        this.facilities.add(facility);
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", numberOfGuests=" + numberOfGuests +
                ", active=" + active +
                ", startDateTime=" + startDateTime +
                ", endDateTime=" + endDateTime +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", facilities=" + facilities +
                ", images=" + images +
                '}';
    }
}

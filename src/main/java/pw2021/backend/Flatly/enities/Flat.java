package pw2021.backend.Flatly.enities;

import javax.persistence.*;
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

    public Flat() {
    }

    public Flat(String name, Integer rooms, Integer area, String description, Address address) {
        this.name = name;
        this.rooms = rooms;
        this.area = area;
        this.description = description;
        this.address = address;
    }

    public Flat(Long id, String name, Integer rooms, Integer area, String description, Address address, Set<Facility> facilities) {
        this.id = id;
        this.name = name;
        this.rooms = rooms;
        this.area = area;
        this.description = description;
        this.address = address;
        this.facilities = facilities;
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

    @Override
    public String toString() {
        return "Flat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rooms=" + rooms +
                ", area=" + area +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", facilities=" + facilities +
                '}';
    }
}

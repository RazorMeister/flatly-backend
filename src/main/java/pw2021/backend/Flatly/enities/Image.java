package pw2021.backend.Flatly.enities;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image implements Comparable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String path;

    public Image() {
    }

    public Image(String path) {
        this.path = path;
    }

    public Image(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Path{" +
                "id=" + id +
                ", path='" + path + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Image b = (Image)o;
        return this.getId().compareTo(b.getId());
    }
}

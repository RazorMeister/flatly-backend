package pw2021.backend.Flatly.enities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String secondName;
    private boolean isAdmin;

    public User() {

    }

    public User(
            Long id,
            String login,
            String password,
            String firstName,
            String secondName
    ) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public User(
            String login,
            String password,
            String firstName,
            String secondName,
            boolean isAdmin
    ) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.isAdmin = isAdmin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}

package pl.beny.rental.model;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Token token;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USR_ID")
    private Long id;

    @Column(name = "USR_EMAIL", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "USR_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USR_FIRST_NAME", length = 60, nullable = false)
    private String firstName;

    @Column(name = "USR_LAST_NAME", length = 60, nullable = false)
    private String lastName;

    @Column(name = "USR_CITY", length = 60, nullable = false)
    private String city;

    @Column(name = "USR_PHONE", length = 30)
    private String phone;

    @Column(name = "USR_ACTIVE")
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }
}
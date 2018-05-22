package pl.beny.rental.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ROLES")
public class Role {

    public enum Roles {
        USER("USER"),
        EMPLOYEE("EMPLOYEE"),
        ADMIN("ADMIN");

        private String role;

        Roles(String role) {
            this.role = role;
        }

        public String getRole() {
            return role;
        }
    }

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ROL_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_ROLE", length = 10, updatable = false, nullable = false, unique = true)
    private Roles role = Roles.USER;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

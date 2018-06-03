package pl.beny.rental.security;

import org.springframework.security.core.authority.AuthorityUtils;
import pl.beny.rental.model.Role;
import pl.beny.rental.model.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserContext extends org.springframework.security.core.userdetails.User {

    private User user;
    private List<Role.Roles> roles;

    public UserContext(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRoles().stream().map(role -> role.getRole().getRole()).toArray(String[]::new)));
        this.user = user;
        this.roles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
    }

    public User getUser() {
        return user;
    }

    public boolean isAdmin() {
        return roles.contains(Role.Roles.ADMIN);
    }

    public boolean isEmployee() {
        return roles.contains(Role.Roles.EMPLOYEE);
    }

}

package pl.beny.rental.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.beny.rental.model.Role;
import pl.beny.rental.service.RoleService;

@Component
public class RoleUtil {

    private static RoleService roleService;

    @Autowired
    public RoleUtil(RoleService roleService) {
        RoleUtil.roleService = roleService;
    }

    public static Role findAdmin() {
        return roleService.findByRole(Role.Roles.ADMIN);
    }

    public static Role findEmployee() {
        return roleService.findByRole(Role.Roles.EMPLOYEE);
    }

    public static Role findUser() {
        return roleService.findByRole(Role.Roles.USER);
    }

}

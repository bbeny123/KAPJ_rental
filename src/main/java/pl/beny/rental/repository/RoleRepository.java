package pl.beny.rental.repository;

import org.springframework.stereotype.Repository;
import pl.beny.rental.model.Role;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Role findByRole(Role.Roles role);

}

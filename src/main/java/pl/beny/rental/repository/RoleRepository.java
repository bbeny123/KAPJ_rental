package pl.beny.rental.repository;

import org.springframework.stereotype.Repository;
import pl.beny.rental.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends BaseRepository<Role> {

    Optional<Role> findByRole(Role.Roles role);

}

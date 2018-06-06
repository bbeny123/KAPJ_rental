package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.Role;
import pl.beny.rental.repository.RoleRepository;
import pl.beny.rental.util.RentalException;

@Service
public class RoleService extends BaseService<Role> {

    private RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Role findByRole(Role.Roles role) throws RentalException {
        return repository.findByRole(role).orElseThrow(() -> new RentalException(RentalException.RentalErrors.ROLE_NOT_EXISTS));
    }

}

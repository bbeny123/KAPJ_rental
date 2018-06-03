package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.Role;
import pl.beny.rental.repository.RoleRepository;

@Service
public class RoleService extends BaseService<Role> {

    private RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Role findByRole(Role.Roles role) {
        return repository.findByRole(role);
    }

}

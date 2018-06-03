package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.repository.UserRepository;

@Service
public class UserContextService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserContextService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserContext loadUserByUsername(String email) throws UsernameNotFoundException {
        return new UserContext(repository.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException("The e-mail does not exist in database")));
    }

}

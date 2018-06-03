package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beny.rental.model.Token;
import pl.beny.rental.model.User;
import pl.beny.rental.repository.UserRepository;
import pl.beny.rental.util.MailUtil;
import pl.beny.rental.util.RoleUtil;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService extends BaseService<User> {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Transactional
    public User create(User user) {
        user.setRoles(Collections.singleton(RoleUtil.findUser()));
        user.setActive(false);
        user.setToken(UUID.randomUUID().toString());
        user = saveAndFlush(user);
        MailUtil.sendActivationEmail(user.getEmail(), user.getToken().getToken());
        return user;
    }

    public User activate(User user) {
        user.setActive(true);
        user.setToken((Token) null);
        return saveAndFlush(user);
    }

}

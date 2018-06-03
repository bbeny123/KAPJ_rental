package pl.beny.rental.repository;

import org.springframework.stereotype.Repository;
import pl.beny.rental.model.User;

@Repository
public interface UserRepository extends BaseRepository<User> {

    boolean existsByEmail(String email);

}

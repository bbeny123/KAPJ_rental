package pl.beny.rental.repository;

import org.springframework.stereotype.Repository;
import pl.beny.rental.model.Token;

import java.util.Optional;

@Repository
public interface TokenRepository extends BaseRepository<Token> {

    Optional<Token> findByToken(String token);

}

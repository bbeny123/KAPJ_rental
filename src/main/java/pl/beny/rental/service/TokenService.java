package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.Token;
import pl.beny.rental.repository.TokenRepository;
import pl.beny.rental.util.RentalException;

@Service
public class TokenService extends BaseService<Token> {

    private TokenRepository repository;

    @Autowired
    public TokenService(TokenRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public Token findByToken(String token) throws RentalException {
        return repository.findByToken(token).orElseThrow(() -> new RentalException(RentalException.RentalErrors.TOKEN_NOT_EXISTS));
    }

}

package pl.beny.rental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beny.rental.repository.BaseRepository;
import pl.beny.rental.security.UserContext;
import pl.beny.rental.util.RentalException;

import java.util.List;

@Service
public abstract class BaseService<T> {

    protected final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    public T saveAdmin(UserContext ctx, T data) throws RentalException {
        checkAdmin(ctx);
        return repository.save(data);
    }

    @Transactional
    public T saveEmployee(UserContext ctx, T data) throws RentalException {
        checkEmployee(ctx);
        return repository.save(data);
    }

    @Transactional
    public T save(T data) {
        return repository.save(data);
    }

    @Transactional
    public T saveAndFlushAdmin(UserContext ctx, T data) throws RentalException {
        checkAdmin(ctx);
        return repository.saveAndFlush(data);
    }

    @Transactional
    public T saveAndFlushEmployee(UserContext ctx, T data) throws RentalException {
        checkEmployee(ctx);
        return repository.saveAndFlush(data);
    }

    @Transactional
    public T saveAndFlush(T data) {
        return repository.saveAndFlush(data);
    }

    public List<T> findAllAdmin(UserContext ctx) throws RentalException {
        checkAdmin(ctx);
        return repository.findAll();
    }

    public List<T> findAllEmployee(UserContext ctx) throws RentalException {
        checkEmployee(ctx);
        return repository.findAll();
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findOneAdmin(UserContext ctx, Long id) throws RentalException {
        checkAdmin(ctx);
        return repository.findById(id).orElse(null);
    }

    public T findOneEmployee(UserContext ctx, Long id) throws RentalException {
        checkEmployee(ctx);
        return repository.findById(id).orElse(null);
    }

    public T findOne( Long id) {
        return repository.findById(id).orElse(null);
    }

    private void checkAdmin(UserContext ctx) throws RentalException {
        if (!ctx.isAdmin()) throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
    }

    private void checkEmployee(UserContext ctx) throws RentalException {
        if (!ctx.isEmployee()) throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
    }

}

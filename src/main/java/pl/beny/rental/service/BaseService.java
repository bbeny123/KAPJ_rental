package pl.beny.rental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.repository.BaseRepository;
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
        return save(data);
    }

    @Transactional
    public T saveEmployee(UserContext ctx, T data) throws RentalException {
        checkEmployee(ctx);
        return save(data);
    }

    @Transactional
    public T save(T data) {
        return repository.save(data);
    }

    @Transactional
    public T saveAndFlushAdmin(UserContext ctx, T data) throws RentalException {
        checkAdmin(ctx);
        return saveAndFlush(data);
    }

    @Transactional
    public T saveAndFlushEmployee(UserContext ctx, T data) throws RentalException {
        checkEmployee(ctx);
        return saveAndFlush(data);
    }

    @Transactional
    public T saveAndFlush(T data) {
        return repository.saveAndFlush(data);
    }

    public List<T> findAllAdmin(UserContext ctx) throws RentalException {
        checkAdmin(ctx);
        return findAll();
    }

    public List<T> findAllEmployee(UserContext ctx) throws RentalException {
        checkEmployee(ctx);
        return findAll();
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public T findOneAdmin(UserContext ctx, Long id) throws RentalException {
        checkAdmin(ctx);
        return findOne(id);
    }

    public T findOneEmployee(UserContext ctx, Long id) throws RentalException {
        checkEmployee(ctx);
        return findOne(id);
    }

    public T findOne(Long id) throws RentalException {
        return repository.findById(id).orElseThrow(() -> new RentalException(RentalException.RentalErrors.ITEM_NOT_EXISTS));
    }

    private void checkAdmin(UserContext ctx) throws RentalException {
        if (!ctx.isAdmin()) throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
    }

    private void checkEmployee(UserContext ctx) throws RentalException {
        if (!ctx.isEmployee() && !ctx.isAdmin()) throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
    }

}

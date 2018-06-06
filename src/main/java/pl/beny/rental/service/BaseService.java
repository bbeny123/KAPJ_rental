package pl.beny.rental.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.repository.BaseRepository;
import pl.beny.rental.util.RentalException;

import java.util.List;

@Service
public abstract class BaseService<T> {

    private final BaseRepository<T> repository;

    public BaseService(BaseRepository<T> repository) {
        this.repository = repository;
    }

    @Transactional
    void saveAdmin(UserContext ctx, T data) throws RentalException {
        checkAdmin(ctx);
        save(data);
    }

    @Transactional
    void saveEmployee(UserContext ctx, T data) throws RentalException {
        checkEmployee(ctx);
        save(data);
    }

    @Transactional
    void save(T data) {
        repository.save(data);
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
    T saveAndFlush(T data) {
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

    private List<T> findAll() {
        return repository.findAll();
    }

    T findOneAdmin(UserContext ctx, Long id) throws RentalException {
        checkAdmin(ctx);
        return findOne(id);
    }

    T findOneEmployee(UserContext ctx, Long id) throws RentalException {
        checkEmployee(ctx);
        return findOne(id);
    }

    T findOne(Long id) throws RentalException {
        return repository.findById(id).orElseThrow(() -> new RentalException(RentalException.RentalErrors.ITEM_NOT_EXISTS));
    }

    void checkAdmin(UserContext ctx) throws RentalException {
        if (!ctx.isAdmin()) throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
    }

    private void checkEmployee(UserContext ctx) throws RentalException {
        if (!ctx.isEmployee() && !ctx.isAdmin()) throw new RentalException(RentalException.RentalErrors.NOT_AUTHORIZED);
    }

}

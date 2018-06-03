package pl.beny.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.beny.rental.util.Exclude;

@Exclude
public interface BaseRepository<T> extends CrudRepository<T, Long>, JpaRepository<T, Long> {
}

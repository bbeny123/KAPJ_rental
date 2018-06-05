package pl.beny.rental.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import pl.beny.rental.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends BaseRepository<Car> {

    @EntityGraph(value = Car.EntityGraphs.WITH_RESERVATIONS)
    List<Car> findAll();

    @EntityGraph(value = Car.EntityGraphs.WITH_RESERVATIONS)
    List<Car> findAllByAvailable(boolean available);

    @EntityGraph(value = Car.EntityGraphs.WITH_RESERVATIONS)
    Optional<Car> findById(Long id);

}

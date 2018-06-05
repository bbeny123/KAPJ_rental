package pl.beny.rental.repository;

import org.springframework.stereotype.Repository;
import pl.beny.rental.model.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends BaseRepository<Reservation> {

    List<Reservation> findAllByUserId(Long userId);

}

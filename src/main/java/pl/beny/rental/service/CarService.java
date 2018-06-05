package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.Car;
import pl.beny.rental.model.Reservation;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.repository.CarRepository;
import pl.beny.rental.util.RentalException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class CarService extends BaseService<Car> {

    private CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public List<Car> findAll(UserContext ctx) {
        return ctx.isEmployee() || ctx.isAdmin() ? repository.findAll() : repository.findAllByAvailable(true);
    }

    public Car changeAvailability(UserContext ctx, Long carId, boolean available) throws RentalException {
        Car car = repository.findById(carId).orElseThrow(() -> new RentalException(RentalException.RentalErrors.ITEM_NOT_EXISTS));
        if (car.isAvailable() == available) {
            return car;
        }
        car.getReservations().stream()
                .filter(rsv -> Arrays.asList(Reservation.Status.ACTIVE, Reservation.Status.WAITING).contains(rsv.getStatus()))
                .forEach(rsv -> {
                    rsv.setDateEnd(LocalDate.now());
                    rsv.setStatus(rsv.getStatus().equals(Reservation.Status.ACTIVE) ? Reservation.Status.FINISHED : Reservation.Status.CANCELED);
                });
        car.setAvailable(available);
        return saveEmployee(ctx, car);
    }

}

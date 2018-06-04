package pl.beny.rental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.beny.rental.model.Car;
import pl.beny.rental.repository.CarRepository;

@Service
public class CarService extends BaseService<Car> {

    private CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        super(repository);
        this.repository = repository;
    }

}

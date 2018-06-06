package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.beny.rental.dto.CarResponse;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.service.CarService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RESTController {

    private CarService carService;

    @Autowired
    public RESTController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/rest/cars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cars() {
        List<CarResponse> cars = carService.findAll(new UserContext()).stream().map(CarResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(cars);
    }

}

package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.beny.rental.dto.CarResponse;
import pl.beny.rental.service.CarService;

import java.util.stream.Collectors;

@Controller
public class CarController extends BaseController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService, MessageSource messageSource) {
		super("redirect:/cars", messageSource);
		this.carService = carService;
	}

	@GetMapping("/cars")
	public String cars(Model model) {
		model.addAttribute("cars", carService.findAll(getUserContext()).stream().map(CarResponse::new).collect(Collectors.toList()));
		return "cars";
	}

	@PostMapping("/cars/{carId}/available/{available}")
	public String cars(@PathVariable("carId") Long carId, @PathVariable("available") boolean available) throws Exception {
		carService.changeAvailability(getUserContext(), carId, available);
		return viewName;
	}

}

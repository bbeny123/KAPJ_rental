package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.beny.rental.dto.CarResponse;
import pl.beny.rental.service.CarService;

import java.util.stream.Collectors;

@Controller
public class CarController extends BaseController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService, MessageSource messageSource) {
		super("cars", messageSource);
		this.carService = carService;
	}

	@GetMapping("/cars")
	public String cars(Model model) {
		model.addAttribute("cars", carService.findAll().stream().map(CarResponse::new).collect(Collectors.toList()));
		return viewName;
	}

}

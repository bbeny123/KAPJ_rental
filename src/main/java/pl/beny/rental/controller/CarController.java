package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.beny.rental.dto.CarRequest;
import pl.beny.rental.dto.CarResponse;
import pl.beny.rental.service.CarService;

import java.util.stream.Collectors;

@Controller
public class CarController extends BaseController {

	private final CarService carService;

	@Autowired
	public CarController(CarService carService, MessageSource messageSource) {
		super("cars", "/cars", messageSource, true);
		this.carService = carService;
	}

	@GetMapping("/cars")
	public String cars(Model model) {
		model.addAttribute("cars", carService.findAll(getUserContext()).stream().map(CarResponse::new).collect(Collectors.toList()));
		return viewName;
	}

	@PostMapping("/cars/{carId}/available/{available}")
	public String changeAvailability(@PathVariable("carId") Long carId, @PathVariable("available") boolean available) throws Exception {
		carService.changeAvailability(getUserContext(), carId, available);
		return redirectToUrl();
	}

	@PostMapping("/cars/{carId}/rent")
	public String rent(@PathVariable("carId") Long carId) throws Exception {
		carService.rent(getUserContext(), carId);
		return redirectToUrl();
	}

	@GetMapping("/cars/new")
	public String carsForm() {
		return "car";
	}

	@PostMapping("/cars/new")
	public RedirectView newCar(RedirectAttributes attributes, CarRequest carRequest) throws Exception {
		carService.create(carRequest.getCar());
		return responseInfo(url, attributes, "info.added");
	}

}

package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.beny.rental.dto.ReservationResponse;
import pl.beny.rental.service.ReservationService;

import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Controller
public class ReservationController extends BaseController {

	private ReservationService reservationService;

	@Autowired
	public ReservationController(ReservationService reservationService, MessageSource messageSource) {
		super("reservations", "/reservations", messageSource, true);
		this.reservationService = reservationService;
	}

	@GetMapping("/reservations")
	public String reservation(Model model) throws Exception {
		model.addAttribute("reservations", reservationService.findAllEmployee(getUserContext()).stream().map(ReservationResponse::new).collect(Collectors.toList()));
		return viewName;
	}

	@PostMapping("/reservations/{rsvId}/{action}")
	public String changeStatus(@PathVariable("rsvId") Long rsvId, @PathVariable("action") String action) throws Exception {
		reservationService.changeStatus(getUserContext(), rsvId, action);
		return redirectToUrl();
	}

	@GetMapping(value = "/reservations/invoice/{rsvId}")
	public void getInvoice(@PathVariable("rsvId") Long rsvId, HttpServletResponse response) throws Exception {
		reservationService.prepareInvoiceResponse(getUserContext(), rsvId, response);
	}

}

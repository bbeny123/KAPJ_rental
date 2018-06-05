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
public class UserReservationController extends BaseController {

	private ReservationService reservationService;

	@Autowired
	public UserReservationController(ReservationService reservationService, MessageSource messageSource) {
		super("redirect:/user-reservations", messageSource);
		this.reservationService = reservationService;
	}

	@GetMapping("/user-reservations")
	public String reservation(Model model) {
		model.addAttribute("reservations", reservationService.findAllByUserId(getUserContext()).stream().map(ReservationResponse::new).collect(Collectors.toList()));
		return "user-reservations";
	}

	@PostMapping("/user-reservations/{rsvId}/cancel")
	public String changeStatus(@PathVariable("rsvId") Long rsvId) throws Exception {
		reservationService.cancel(getUserContext(), rsvId);
		return viewName;
	}

	@GetMapping(value = "/user-reservations/invoice/{rsvId}")
	public void getInvoice(@PathVariable("rsvId") Long rsvId, HttpServletResponse response) throws Exception {
		reservationService.prepareInvoiceResponse(getUserContext(), rsvId, response);
	}

}

package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.beny.rental.dto.RegistrationRequest;

@Controller
public class RegistrationController extends BaseController {

	@Autowired
	public RegistrationController(MessageSource messageSource) {
		super("registration", messageSource);
	}

	@RequestMapping(value = "/register")
	public String register() {
		return viewName;
	}

	@PostMapping(value="/register")
	public ModelAndView register(Model model, RegistrationRequest user, @RequestParam("g-recaptcha-response") String captcha) {
		return new ModelAndView("registration", "message", model);
	}

}

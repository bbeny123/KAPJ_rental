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
import pl.beny.rental.util.MailUtil;

@Controller
public class RegistrationController extends BaseController {

	private MailUtil mailUtil;

	@Autowired
	public RegistrationController(MessageSource messageSource, MailUtil mailUtil) {
		super("registration", messageSource);
		this.mailUtil = mailUtil;
	}

	@RequestMapping(value = "/register")
	public String register() {
		return viewName;
	}

	@PostMapping(value="/register")
	public ModelAndView register(Model model, RegistrationRequest user, @RequestParam("g-recaptcha-response") String captcha) {
		mailUtil.sendActivationEmail(user.getEmail(), "abs");
		return new ModelAndView("registration", "message", model);
	}

}

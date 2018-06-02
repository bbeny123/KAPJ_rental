package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends BaseController {

	@Autowired
	public LoginController(MessageSource messageSource) {
		super("login", messageSource);
	}

	@RequestMapping(value="/login")
	public String login() {
		return viewName;
	}

	@PostMapping(value="/logout")
	public ModelAndView logout(Model model) {
		model.addAttribute("logout", true);
		return new ModelAndView(viewName, "message", model);
	}

}

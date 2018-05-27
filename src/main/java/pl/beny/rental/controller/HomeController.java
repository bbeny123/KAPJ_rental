package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private MessageSource messageSource;

	@Autowired
	public HomeController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@RequestMapping(value="/")
	public ModelAndView hello() {
		return new ModelAndView("index", "message", "Hello World");
	}

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}

	@PostMapping(value="/logout")
	public ModelAndView logout(Model model) {
		model.addAttribute("logout", true);
		return new ModelAndView("login", "message", model);
	}


}

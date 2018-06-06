package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.beny.rental.dto.UserResponse;
import pl.beny.rental.service.UserService;

import java.util.stream.Collectors;

@Controller
public class UserController extends BaseController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService, MessageSource messageSource) {
		super("users", "/users", messageSource, true);
		this.userService = userService;
	}

	@GetMapping("/users")
	public String users(Model model) throws Exception {
		model.addAttribute("userId", getUserContext().getUser().getId());
		model.addAttribute("users", userService.findAllAdmin(getUserContext()).stream().map(UserResponse::new).collect(Collectors.toList()));
		return viewName;
	}

	@PostMapping("/users/{userId}/activate")
	public String activate(@PathVariable("userId") Long userId) throws Exception {
		userService.activate(getUserContext(), userId);
		return redirectToUrl();
	}

	@PostMapping("/users/{userId}/{action}/{role}")
	public String changeRole(@PathVariable("userId") Long userId, @PathVariable("action") String action, @PathVariable("role") String role) throws Exception {
		userService.changeRole(getUserContext(), userId, action, role);
		return redirectToUrl();
	}

}

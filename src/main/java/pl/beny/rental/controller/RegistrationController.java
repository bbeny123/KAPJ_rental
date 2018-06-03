package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.beny.rental.dto.RegistrationRequest;
import pl.beny.rental.service.TokenService;
import pl.beny.rental.service.UserService;
import pl.beny.rental.util.CaptchaUtil;
import pl.beny.rental.util.RentalException;

@Controller
public class RegistrationController extends BaseController {

    private UserService userService;
    private TokenService tokenService;
    private PasswordEncoder encoder;

	@Autowired
	public RegistrationController(UserService userService, TokenService tokenService, PasswordEncoder encoder, MessageSource messageSource) {
		super("registration", messageSource);
		this.userService = userService;
		this.tokenService = tokenService;
		this.encoder = encoder;
	}

	@RequestMapping("/register")
	public String register() {
		return viewName;
	}

	@PostMapping("/register")
	public ModelAndView register(Model model, RegistrationRequest userRequest, @RequestParam("g-recaptcha-response") String captchaResponse) throws Exception {
	    if (!CaptchaUtil.checkCaptcha(captchaResponse)) throw new RentalException(RentalException.RentalErrors.CAPTCHA_ERROR);
	    if (userService.existsByEmail(userRequest.getEmail())) throw new RentalException(RentalException.RentalErrors.USER_EXISTS);
	    userService.create(userRequest.getUser(encoder));
	    return responseInfo("login", model, "info.registered");
	}

	@GetMapping("/register/activate")
	public ModelAndView activate(Model model, @RequestParam("token") String token) throws Exception {
		userService.activate(tokenService.findByToken(token).getUser());
		return responseInfo("login", model, "info.activated");
	}

    @GetMapping("/register/resend")
    public String resend(){
        return "token";
    }

    @PostMapping("/register/resend")
    public ModelAndView resendToken(Model model, String email) throws Exception {
        userService.resendToken(userService.findByEmail(email));
		return responseInfo("login", model, "info.resend");
    }

}

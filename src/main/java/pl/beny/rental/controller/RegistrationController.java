package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		super("registration", "/register", messageSource);
		this.userService = userService;
		this.tokenService = tokenService;
		this.encoder = encoder;
	}

	@GetMapping("/register")
	public String register() {
		return viewOrForwardToHome(viewName);
	}

	@PostMapping("/register")
	public String register(Model model, RegistrationRequest userRequest, @RequestParam("g-recaptcha-response") String captchaResponse) throws Exception {
		if (isAuthenticated()) {
			return redirect;
		}
		if (!CaptchaUtil.checkCaptcha(captchaResponse)) throw new RentalException(RentalException.RentalErrors.CAPTCHA_ERROR);
	    userService.create(userRequest.getUser(encoder));
	    return responseInfo("login", model, "info.registered");
	}

	@GetMapping("/register/activate")
	public String activate(Model model, @RequestParam("token") String token) throws Exception {
		if (isAuthenticated()) {
			return redirect;
		}
		userService.activate(tokenService.findByToken(token).getUser());
		return responseInfo("login", model, "info.activated");
	}

    @GetMapping("/register/resend")
    public String resend(){
		return viewOrForwardToHome("token");
    }

    @PostMapping("/register/resend")
    public String resendToken(Model model, String email) throws Exception {
		if (isAuthenticated()) {
			return redirect;
		}
        userService.resendToken(userService.findByEmail(email));
		return responseInfo("login", model, "info.resend");
    }

}

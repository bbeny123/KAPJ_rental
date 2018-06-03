package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends BaseController {

    @Autowired
    public LoginController(MessageSource messageSource) {
        super("login", messageSource);
    }

    @GetMapping("/login")
    public ModelAndView login(HttpServletRequest request, Model model) {
        if (isAuthenticated()) {
            return forwardToHome();
        }
        if (request.getParameter("logout") != null) {
            return responseInfo(viewName, model, "info.logout");
        }
        return new ModelAndView(viewName);
    }

    @GetMapping("/login/failure")
    public ModelAndView failure(Model model) {
        if (isAuthenticated()) {
            return forwardToHome();
        }
        return responseInfo(viewName, model, "info.invalid.credentials");
    }

}

package pl.beny.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends BaseController {

    @Autowired
    public HomeController(MessageSource messageSource) {
        super("index", messageSource);
    }

    @GetMapping("/")
    public String home() {
        return viewName;
    }

}

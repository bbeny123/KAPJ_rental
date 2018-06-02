package pl.beny.rental.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    private Logger logger;
    private MessageSource messageSource;
    String viewName;

    public BaseController(String viewName, MessageSource messageSource) {
        this.logger = LogManager.getLogger(this.getClass());
        this.messageSource = messageSource;
        this.viewName = viewName;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {
        logger.warn(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return new ModelAndView(viewName, "message", model);
    }

}

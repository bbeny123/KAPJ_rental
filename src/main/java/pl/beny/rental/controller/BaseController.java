package pl.beny.rental.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import pl.beny.rental.util.RentalException;

public abstract class BaseController {

    private Logger logger;
    private MessageSource messageSource;
    String viewName;

    public BaseController(String viewName, MessageSource messageSource) {
        this.logger = LogManager.getLogger(this.getClass());
        this.messageSource = messageSource;
        this.viewName = viewName;
    }

    ModelAndView responseInfo(String viewName, Model model, String source) {
        model.addAttribute("info", messageSource.getMessage(source, null, LocaleContextHolder.getLocale()));
        return new ModelAndView(viewName, "message", model);
    }

    String viewOrForwardToHome(String viewName) {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        return viewName;
    }

    boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    ModelAndView forwardToHome() {
        return new ModelAndView("redirect:/");
    }


    @ExceptionHandler(RentalException.class)
    public ModelAndView handleRentalException(RentalException ex, Model model) {
        logger.warn(ex.getMessage());
        return responseInfo(ex.getViewName() != null ? ex.getViewName() : viewName, model, ex.getMessageSource());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {
        logger.warn(ex.getMessage());
        model.addAttribute("info", ex.getMessage());
        return new ModelAndView(viewName, "message", model);
    }

}

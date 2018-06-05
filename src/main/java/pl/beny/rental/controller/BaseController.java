package pl.beny.rental.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.util.RentalException;

public abstract class BaseController {

    private Logger logger;
    private MessageSource messageSource;
    String viewName;
    String redirect = "redirect:/";

    public BaseController(String viewName, MessageSource messageSource) {
        this.logger = LogManager.getLogger(this.getClass());
        this.messageSource = messageSource;
        this.viewName = viewName;
    }

    @ExceptionHandler(RentalException.class)
    public String handleRentalException(RentalException ex, Model model) {
        logger.warn(ex.getMessage());
        return responseInfo(ex.getViewName() != null ? ex.getViewName() : viewName, model, ex.getMessageSource());
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        logger.warn(ex.getMessage());
        model.addAttribute("info", ex.getMessage());
        return viewName;
    }

    UserContext getUserContext() {
        return isAuthenticated() ? (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : new UserContext();
    }

    String viewOrForwardToHome(String viewName) {
        return isAuthenticated() ? redirect : viewName;
    }

    boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    String responseInfo(String viewName, Model model, String source) {
        model.addAttribute("info", messageSource.getMessage(source, null, LocaleContextHolder.getLocale()));
        return viewName;
    }

}

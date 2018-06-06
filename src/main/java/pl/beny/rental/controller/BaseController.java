package pl.beny.rental.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import pl.beny.rental.model.UserContext;
import pl.beny.rental.util.RentalException;

public abstract class BaseController {

    private Logger logger;
    private MessageSource messageSource;
    private boolean listView;
    String viewName;
    String url;
    String redirect = "redirect:/";

    public BaseController(String viewName, String url, MessageSource messageSource) {
        this.logger = LogManager.getLogger(this.getClass());
        this.messageSource = messageSource;
        this.viewName = viewName;
        this.url = url;
    }

    public BaseController(String viewName, String url, MessageSource messageSource, boolean listView) {
        this(viewName, url, messageSource);
        this.listView = listView;
    }

    @ExceptionHandler(RentalException.class)
    public RedirectView handleRentalException(RentalException ex, RedirectAttributes attributes) {
        logger.warn(ex.getMessage());
        return responseInfo(ex.getUrl() != null ? ex.getUrl() : url, attributes, ex.getMessageSource());
    }

    @ExceptionHandler(Exception.class)
    public RedirectView handleException(Exception ex, RedirectAttributes attributes) {
        logger.warn(ex.getMessage());
        attributes.addFlashAttribute("info", ex.getMessage());
        return new RedirectView(listView ? "/" : url);
    }

    RedirectView responseInfo(String url, RedirectAttributes attributes, String source) {
        attributes.addFlashAttribute("info", messageSource.getMessage(source, null, LocaleContextHolder.getLocale()));
        return new RedirectView(url);
    }

    String responseInfo(String viewName, Model model, String source) {
        model.addAttribute("info", messageSource.getMessage(source, null, LocaleContextHolder.getLocale()));
        return viewName;
    }

    String viewOrForwardToHome(String viewName) {
        return isAuthenticated() ? redirect : viewName;
    }

    String redirectToUrl() {
        return redirect+viewName;
    }

    UserContext getUserContext() {
        return isAuthenticated() ? (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : new UserContext();
    }

    boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }
}

package pl.beny.rental.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

    protected ModelAndView responseInfo(Model model, String viewName, String source) {
        model.addAttribute("info", messageSource.getMessage(source, null, LocaleContextHolder.getLocale()));
        return new ModelAndView(viewName, "message", model);
    }

    @ExceptionHandler(RentalException.class)
    public ModelAndView handleRentalException(RentalException ex, Model model) {
        logger.warn(ex.getMessage());
        return responseInfo(model, viewName, ex.getMessageSource());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {
        logger.warn(ex.getMessage());
        model.addAttribute("info", ex.getMessage());
        return new ModelAndView(viewName, "message", model);
    }

}

package pl.beny.rental;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import pl.beny.rental.config.SpringConfig;

public class RentalAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { SpringConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
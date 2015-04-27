package org.inline.config;

import org.inline.config.root.DevelopmentConfiguration;
import org.inline.config.root.RootContextConfig;
import org.inline.config.root.TestConfiguration;
import org.inline.config.servlet.WebMvcContextConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{ "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootContextConfig.class, DevelopmentConfiguration.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ WebMvcContextConfig.class };
    }
}
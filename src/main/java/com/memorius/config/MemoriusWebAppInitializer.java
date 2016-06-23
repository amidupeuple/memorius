package com.memorius.config;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by dpivovar on 18.05.2016.
 */
public class MemoriusWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {RootConfig.class, JobConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    //This is required in order to use profiles in configuration
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        WebApplicationContext context = (WebApplicationContext) super.createRootApplicationContext();
        ((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles("dev");
        //((ConfigurableEnvironment) context.getEnvironment()).setActiveProfiles("dev_mysql");
        return context;
    }

}

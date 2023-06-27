package com.github.fabriciolfj.javaexamples.config;

import com.github.fabriciolfj.javaexamples.JavaExamplesApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Configuration
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
            JavaExamplesApplication.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }
}

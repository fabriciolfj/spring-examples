package com.github.fabriciolfj.javaexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
public class SoapConfig {

    @Bean
    public XsdSchema temperatureSchema() {
        var xsd = new ClassPathResource("/META-INF/xsd/temperature.xsd");
        return new SimpleXsdSchema(xsd);
    }

    @Bean
    public DefaultWsdl11Definition temperature() {
        var temperature = new DefaultWsdl11Definition();
        temperature.setPortTypeName("weather");
        temperature.setLocationUri("/");
        temperature.setSchema(temperatureSchema());

        return temperature;
    }

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        var webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setDefaultUri("http://localhost:8080/springws/services");
        return webServiceTemplate;
    }
}

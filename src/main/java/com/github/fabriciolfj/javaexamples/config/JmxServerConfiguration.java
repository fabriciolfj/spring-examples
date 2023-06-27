package com.github.fabriciolfj.javaexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.jmx.support.MBeanServerConnectionFactoryBean;

import java.net.MalformedURLException;

/*@Configuration
@Import(FileReplicatorConfig.class)
public class JmxServerConfiguration {

    @Bean
    public MBeanServerConnectionFactoryBean mbeanServerConnection()
            throws MalformedURLException {
        var url = "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/replicator";
        var mBeanServerConnectionFactoryBean = new MBeanServerConnectionFactoryBean();
        mBeanServerConnectionFactoryBean.setServiceUrl(url);
        return mBeanServerConnectionFactoryBean;
    }
}*/

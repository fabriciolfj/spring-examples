package com.github.fabriciolfj.javaexamples.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@ComponentScan("com.github.fabriciolfj")
@PropertySource("classpath:/batch.properties")
public class BatchConfiguration {

    @Bean
    public DataSource dataSource(final Environment env) {
        var dataSource = new DriverManagerDataSource();
        dataSource.setUrl(env.getRequiredProperty("datasource.url"));
        dataSource.setUsername(env.getProperty("datasource.username"));
        dataSource.setUsername(env.getProperty("datasource.password"));
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(final DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DataSourceInitializer databasePopulator(DataSource dataSource) {
        var populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("org/springframework/batch/core/schema-h2.sql"));
        populator.addScript(new ClassPathResource("sql/reset_user_registration.sql"));
        populator.setContinueOnError(true);
        populator.setIgnoreFailedDrops(true);

        var initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}

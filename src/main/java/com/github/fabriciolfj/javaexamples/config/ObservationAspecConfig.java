package com.github.fabriciolfj.javaexamples.config;

import com.github.fabriciolfj.javaexamples.aspects.PerformanceTrackerHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObservationAspecConfig {

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry registry) {
        registry.observationConfig().observationHandler(new PerformanceTrackerHandler());
        return new ObservedAspect(registry);
    }
}

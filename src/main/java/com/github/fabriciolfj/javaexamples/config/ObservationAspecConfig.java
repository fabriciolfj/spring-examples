package com.github.fabriciolfj.javaexamples.config;

import com.github.fabriciolfj.javaexamples.aspects.PerformanceTrackerHandler;
import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.observation.DefaultMeterObservationHandler;
import io.micrometer.jmx.JmxConfig;
import io.micrometer.jmx.JmxMeterRegistry;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ServerHttpObservationFilter;

@Configuration
public class ObservationAspecConfig {

    @Autowired
    private ObjectProvider<MeterBinder> binders;


    @Bean
    public JmxMeterRegistry meterRegistry() {
        return new JmxMeterRegistry(JmxConfig.DEFAULT, Clock.SYSTEM);
    }

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry registry) {
        registry.observationConfig().observationHandler(new PerformanceTrackerHandler());
        return new ObservedAspect(registry);
    }

    @Bean
    public ObservationRegistry observationRegistry(MeterRegistry meterRegistry) {
        var registry = ObservationRegistry.create();
        var handler = new DefaultMeterObservationHandler(meterRegistry);
        registry.observationConfig().observationHandler(handler);

        this.binders.forEach(b -> b.bindTo(meterRegistry));
        return registry;
    }

    @Bean
    public ServerHttpObservationFilter metricsFilter(ObservationRegistry or) {
        return new ServerHttpObservationFilter(or);
    }
}

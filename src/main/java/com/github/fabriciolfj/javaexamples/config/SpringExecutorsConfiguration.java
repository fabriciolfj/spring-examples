package com.github.fabriciolfj.javaexamples.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executors;

@Configuration
public class SpringExecutorsConfiguration {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        var task = new ThreadPoolTaskExecutor();
        task.setCorePoolSize(50);
        task.setMaxPoolSize(100);
        task.setAllowCoreThreadTimeOut(true);
        task.setWaitForTasksToCompleteOnShutdown(true);

        return task;
    }

    /*@Bean java 19
    public ConcurrentTaskExecutor virtualThreadsTaskExecutor() {
        var virtualThreadsExecutor = Executors.newVirtualThreadPerTaskExecutor();
        return new ConcurrentTaskExecutor(virtualThreadsExecutor);
    }*/
}

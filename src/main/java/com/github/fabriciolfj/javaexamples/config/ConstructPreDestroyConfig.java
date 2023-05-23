package com.github.fabriciolfj.javaexamples.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
//prototype cria uma instancia sempre que e utilizado via injecao de dependencia
public class ConstructPreDestroyConfig {

    @PostConstruct
    public void init() {
        //ideal para validar algum, como a existencia de um arquivo
        log.info("construindo");
    }

    public void test() {
        log.info("teste");
    }

    @PreDestroy
    public void destroy() {
        //ideal para liberar recursos, como fechar um arquivo
        log.info("destruindo");
    }
}

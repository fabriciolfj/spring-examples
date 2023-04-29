package com.github.fabriciolfj.javaexamples.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/secrets")
public class TestGetSecretAwsController {

    @Value("${api-key1}")
    private String value1;

    @Value("${api-key2}")
    private String value2;

    @GetMapping
    public void printValues() {
        log.info("value 1 {}", value1);
        log.info("value 2 {}", value2);
    }
}

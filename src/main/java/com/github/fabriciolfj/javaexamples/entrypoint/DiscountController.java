package com.github.fabriciolfj.javaexamples.entrypoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PropertySource("classpath:desconto.properties")
@RestController
@Slf4j
@RequestMapping("/api/v1/discount")
public class DiscountController {

    @Value("${endofyear.discount:0}")
    public double discount;

    @GetMapping
    public void test() {
        log.info("value {}", discount);
    }
}

package com.github.fabriciolfj.javaexamples.config;

import com.github.fabriciolfj.javaexamples.entity.Product;
import com.github.fabriciolfj.javaexamples.factory.ProductCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShopConfiguration {

    @Bean
    public Box box() {
        final Box box = new Box();
        box.setBeanName("teste");

        return box;
    }

    @Bean
    public Product aaa() {
        return ProductCreator.createProduct("aaa");
    }

    @Bean
    public Product cdrw() {
        return ProductCreator.createProduct("cdrw");
    }

    @Bean
    public Product dvdrw() {
        return ProductCreator.createProduct("dvdrw");
    }
}

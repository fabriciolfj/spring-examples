package com.github.fabriciolfj.javaexamples.config;

import org.springframework.beans.factory.BeanNameAware;

public class Box implements BeanNameAware {
    private String name;
    @Override
    public void setBeanName(String name) {
        this.name = name;
    }

    public void test() {
        System.out.println("teste");
    }
}

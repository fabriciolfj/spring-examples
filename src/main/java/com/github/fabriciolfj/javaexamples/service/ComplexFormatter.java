package com.github.fabriciolfj.javaexamples.service;

import org.springframework.stereotype.Component;

@Component
public class ComplexFormatter {
    private String pattern = "(a + bi)";
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    public String format(ComplexService complex) {
        return pattern
                .replaceAll("a", Integer.toString(complex.getReal()))
                .replaceAll("b", Integer.toString(complex.getImaginary()));
    }
}

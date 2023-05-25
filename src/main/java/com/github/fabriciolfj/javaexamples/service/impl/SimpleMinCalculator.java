package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.service.MinCalculator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleMinCalculator implements MinCalculator {

    @Override
    public double min(double a, double b) {
        var result = Math.min(a, b);
        log.info("min {}", result);
        return result;
    }
}

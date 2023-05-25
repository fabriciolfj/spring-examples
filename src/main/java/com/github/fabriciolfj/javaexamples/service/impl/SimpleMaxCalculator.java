package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.service.MaxCalculator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleMaxCalculator implements MaxCalculator {

    @Override
    public double max(double a, double b) {
        var result = Math.max(a, b);
        log.info("max {}", result);
        return result;
    }
}

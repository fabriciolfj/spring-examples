package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.entity.Complex;
import com.github.fabriciolfj.javaexamples.service.ComplexCalculator;
import org.springframework.stereotype.Component;

@Component
public class StandardComplexCalculator implements ComplexCalculator {

    @Override
    public Complex add(Complex a, Complex b) {
        var result = new Complex(a.real() + b.real(), a.imaginary() + a.imaginary());
        System.out.println("resultado add " + result);
        return result;
    }

    @Override
    public Complex sub(Complex a, Complex b) {
        var result = new Complex(a.real() - b.real(), a.imaginary() - a.imaginary());
        System.out.println("resultado sub " + result);
        return result;
    }
}

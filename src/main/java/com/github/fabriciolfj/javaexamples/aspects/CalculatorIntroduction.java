package com.github.fabriciolfj.javaexamples.aspects;

import com.github.fabriciolfj.javaexamples.service.MaxCalculator;
import com.github.fabriciolfj.javaexamples.service.MinCalculator;
import com.github.fabriciolfj.javaexamples.service.impl.SimpleMaxCalculator;
import com.github.fabriciolfj.javaexamples.service.impl.SimpleMinCalculator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorIntroduction {

    @DeclareParents(
            value = "com.github.fabriciolfj.javaexamples.service.impl.StandardArithmeticCalculator",
            defaultImpl = SimpleMaxCalculator.class
    )
    public MaxCalculator maxCalculator;

    @DeclareParents(
            value = "com.github.fabriciolfj.javaexamples.service.impl.StandardArithmeticCalculator",
            defaultImpl = SimpleMinCalculator.class
    )
    public MinCalculator minCalculator;
}

package com.github.fabriciolfj.javaexamples.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(0)
public class CalculorValidationAspect {

    @Before("execution(* com.github.fabriciolfj.javaexamples.service.impl.StandardArithmeticCalculator.*(..))")
    public void validBefore(final JoinPoint joinPoint) {
        for (var arg: joinPoint.getArgs()) {
            validar((double) arg);
        }
    }

    private void validar(double a) {
        if(a < 0) {
            throw new IllegalArgumentException("apenas numeros positivos");
        }
    }
}

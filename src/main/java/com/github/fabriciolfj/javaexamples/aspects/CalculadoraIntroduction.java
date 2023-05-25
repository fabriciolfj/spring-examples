package com.github.fabriciolfj.javaexamples.aspects;

import com.github.fabriciolfj.javaexamples.service.Counter;
import com.github.fabriciolfj.javaexamples.service.impl.SimpleCounter;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculadoraIntroduction {

    @DeclareParents(
            value = "com.github.fabriciolfj.javaexamples.service.impl.Standard*Calculator",
            defaultImpl = SimpleCounter.class
    )
    public Counter counter;

    @After("execution(* com.github.fabriciolfj.javaexamples.service.impl.*Calculator.*(..)) && this(counter)")
    public void increaseCount(final Counter counter) {
        counter.increase();
    }
}

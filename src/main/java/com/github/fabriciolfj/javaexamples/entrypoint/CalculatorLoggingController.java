package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.entity.Complex;
import com.github.fabriciolfj.javaexamples.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculator")
@RequiredArgsConstructor
public class CalculatorLoggingController {

    private final ArithmeticCalculator calculator;
    private final ComplexCalculator complexCalculator;

    @GetMapping("/complex")
    public void testComplex() {
        complexCalculator.add(new Complex(1, 2), new Complex(2, 3));
        complexCalculator.sub(new Complex(5, 8), new Complex(2, 3));
    }

    @GetMapping
    public void test() {
        calculator.add(1.2, 2);
        calculator.mul(3, 3);
        calculator.div(4, 2);
        var maxCalculator = (MaxCalculator) calculator;
        maxCalculator.max(1, 2);

        var minCalculator = (MinCalculator) calculator;
        minCalculator.min(4, 5);

        var counter = (Counter) calculator;
        System.out.println("Quantidade de chamadas " + counter.getCount());
    }

}

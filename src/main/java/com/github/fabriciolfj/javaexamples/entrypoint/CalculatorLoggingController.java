package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.service.ArithmeticCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/calculator")
@RequiredArgsConstructor
public class CalculatorLoggingController {

    private final ArithmeticCalculator calculator;

    @GetMapping
    public void test() {
        calculator.add(1.2, 2);
        calculator.mul(3, 3);
        calculator.div(4, 2);
    }

}

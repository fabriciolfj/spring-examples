package com.github.fabriciolfj.javaexamples.service;

import com.github.fabriciolfj.javaexamples.entity.Complex;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Configurable
@Scope("prototype")
@Getter
public class ComplexService {

    private final int real;
    private final int imaginary;
    private ComplexFormatter formatter;

    public ComplexService() {
        this.real = 8;
        this.imaginary = 9;
    }

    @Autowired
    public void setFormatter(ComplexFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public String toString() {
        return formatter.format(this);
    }
}

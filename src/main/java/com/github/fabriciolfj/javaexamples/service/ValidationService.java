package com.github.fabriciolfj.javaexamples.service;

import com.github.fabriciolfj.javaexamples.annotations.LogAop;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    @LogAop
    public void execute(final String args) {
        if (args.equals("test")) {
            throw new RuntimeException("invalid param args");
        }
    }
}

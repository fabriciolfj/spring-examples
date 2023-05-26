package com.github.fabriciolfj.javaexamples.threads;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DemonstrationRunnable implements Runnable {
    @Override
    public void run() {
        System.out.printf("Hello at %s from %s%n", LocalDate.now(), Thread.currentThread().getName());
    }
}

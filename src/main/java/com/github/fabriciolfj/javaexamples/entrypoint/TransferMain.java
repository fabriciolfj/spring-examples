package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.async.Sl4jRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.github.fabriciolfj.javaexamples.factory.TransferFactory.toTransfer;

public class TransferMain {

    public static void main(String[] args) {
        final ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) {
            final var runnable = new Sl4jRunnable(toTransfer());
            executor.submit(runnable);
        }

        executor.shutdown();
    }
}

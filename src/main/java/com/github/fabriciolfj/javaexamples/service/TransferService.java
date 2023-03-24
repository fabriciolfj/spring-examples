package com.github.fabriciolfj.javaexamples.service;

import java.util.Random;

public abstract class TransferService {

    private final Random random = new Random();

    public boolean transfer(long amount) {
        beforeTransfer(amount);

        var result = random.nextInt(100) % 2 == 0;

        afeterTransfer(amount, result);
        return result;
    }

    abstract protected void beforeTransfer(long amount);

    abstract protected void afeterTransfer(long amount, boolean outcome);
}

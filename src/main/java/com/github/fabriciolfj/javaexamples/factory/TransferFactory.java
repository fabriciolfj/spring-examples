package com.github.fabriciolfj.javaexamples.factory;

import com.github.fabriciolfj.javaexamples.entity.Transfer;
import java.util.Random;
import java.util.UUID;

public class TransferFactory {

    public static final Random RANDOM = new Random();

    private TransferFactory() { }

    public static Transfer toTransfer() {
        final Transfer transfer = new Transfer(UUID.randomUUID().toString(), "teste", RANDOM.nextLong(100));
        return transfer;
    }
}

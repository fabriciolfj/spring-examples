package com.github.fabriciolfj.javaexamples.async;

import com.github.fabriciolfj.javaexamples.entity.Transfer;
import com.github.fabriciolfj.javaexamples.service.impl.Slf4jTransferService;
import lombok.AllArgsConstructor;
import org.slf4j.MDC;

@AllArgsConstructor
public class Sl4jRunnable implements Runnable {

    private Transfer tx;

    @Override
    public void run() {
        MDC.put("transaction.id", tx.getTransactionId());
        MDC.put("transaction.owner", tx.getSender());
        new Slf4jTransferService().transfer(tx.getAmount());
        MDC.clear();
    }
}

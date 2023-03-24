package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.service.TransferService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Slf4jTransferService extends TransferService {

    @Override
    protected void beforeTransfer(long amount) {
        log.info("Preparing to transfer {} $", amount);
    }

    @Override
    protected void afeterTransfer(long amount, boolean outcome) {
        log.info("has transfer of {} $ complete successfully ? {} .", amount, outcome);
    }
}

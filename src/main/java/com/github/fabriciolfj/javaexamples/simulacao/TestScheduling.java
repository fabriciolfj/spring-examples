package com.github.fabriciolfj.javaexamples.simulacao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class TestScheduling {

    private AtomicInteger atomicInteger = new AtomicInteger();

    @Scheduled(cron = "0/01 * * * * ?")
    public void test() {
        log.info("executed {}, thread {} ", atomicInteger.incrementAndGet(), Thread.currentThread().getName());
    }
}

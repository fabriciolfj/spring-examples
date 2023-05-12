package com.github.fabriciolfj.javaexamples.entrypoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/load")
public class LoadController {

    @GetMapping
    public void doDomething() throws InterruptedException {
        log.info("hey, i'm doing something");
        Thread.sleep(1000);
    }
}

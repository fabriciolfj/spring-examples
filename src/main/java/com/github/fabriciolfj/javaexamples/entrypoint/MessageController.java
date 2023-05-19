package com.github.fabriciolfj.javaexamples.entrypoint;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageSource messageSource;

    @GetMapping
    public void test() {
        log.info(messageSource.getMessage("alert.checkout", null, Locale.getDefault()));
    }
}

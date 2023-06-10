package com.github.fabriciolfj.javaexamples.entrypoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/test")
public class ApiV2 {

    @GetMapping
    public String ok() {
        return "ok";
    }
}

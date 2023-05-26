package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.entity.ShoppingCart;
import com.github.fabriciolfj.javaexamples.events.Cashier;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final Cashier cashier;

    @GetMapping
    public void send() {
        cashier.checkout(ShoppingCart.builder().name("americanas").build());
    }
}

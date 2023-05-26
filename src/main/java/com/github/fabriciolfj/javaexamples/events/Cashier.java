package com.github.fabriciolfj.javaexamples.events;

import com.github.fabriciolfj.javaexamples.entity.ShoppingCart;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Cashier implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void checkout(final ShoppingCart cart) {
        var event = new CheckoutEvent(cart, LocalDateTime.now());
        applicationEventPublisher.publishEvent(event);
    }
}

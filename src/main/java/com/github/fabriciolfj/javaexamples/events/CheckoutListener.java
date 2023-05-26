package com.github.fabriciolfj.javaexamples.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CheckoutListener /*implements ApplicationListener<CheckoutEvent>*/ {

    /*@Override
    public void onApplicationEvent(CheckoutEvent event) {
        System.out.println("event checkout " + event);
    }*/

    @EventListener
    public void onApplicationEvent(final CheckoutEvent event) {
        System.out.println("event checkout " + event);
    }
}

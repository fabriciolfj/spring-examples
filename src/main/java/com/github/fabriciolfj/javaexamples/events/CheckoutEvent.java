package com.github.fabriciolfj.javaexamples.events;

import com.github.fabriciolfj.javaexamples.entity.ShoppingCart;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
@ToString
public class CheckoutEvent/* extends ApplicationEvent*/ {

    private ShoppingCart cart;
    private LocalDateTime localDateTime;

    public CheckoutEvent(final ShoppingCart cart, final LocalDateTime localDateTime) {
        //super(cart);
        this.cart = cart;
        this.localDateTime = localDateTime;
    }
}

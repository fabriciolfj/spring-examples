package com.github.fabriciolfj.javaexamples.factory;

import com.github.fabriciolfj.javaexamples.entity.Battery;
import com.github.fabriciolfj.javaexamples.entity.Disc;
import com.github.fabriciolfj.javaexamples.entity.Product;

import java.math.BigDecimal;

public class ProductCreator {

    public static Product createProduct(final String id) {
        return switch (id) {
            case "aaa" -> new Battery("AAA", "battery", BigDecimal.valueOf(1.2));
            case "cdrw" -> new Disc("CD-RW", "cdrw", BigDecimal.valueOf(9.2));
            case "dvdrw" -> new Disc("DVD-RW", "dvrw", BigDecimal.valueOf(15.9));
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }
}

package com.github.fabriciolfj.javaexamples.simulacao;

import com.github.fabriciolfj.javaexamples.repository.BookShop;
import com.github.fabriciolfj.javaexamples.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookTest implements CommandLineRunner {

    @Autowired
    private Cashier bookShop;

    @Override
    public void run(String... args) throws Exception {
        //var isbn = List.of("0001", "0002");
        //bookShop.checkout(isbn, "user1");
    }
}

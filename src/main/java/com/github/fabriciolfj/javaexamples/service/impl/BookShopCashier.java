package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.repository.BookShop;
import com.github.fabriciolfj.javaexamples.service.Cashier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookShopCashier implements Cashier {

    private final BookShop bookShop;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void checkout(List<String> isbn, String username) {
        isbn.forEach(i -> bookShop.purchase(i, username));
    }
}

package com.github.fabriciolfj.javaexamples.service;

import java.util.List;

public interface Cashier {

    void checkout(List<String> isbn, String username);

}

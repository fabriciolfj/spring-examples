package com.github.fabriciolfj.javaexamples.simulacoes;

import java.util.HashMap;
import java.util.Map;

public class ComputeTest {

    public static void main(String[] args) {
        final Map<Integer, String> values = new HashMap<>();

        values.put(1, "teste");
        values.put(2, "teste2");

        values.compute(3, (key, val) -> "teste3");
        values.compute(2 , (key, val) -> "teste4");

        System.out.println(values);
    }
}

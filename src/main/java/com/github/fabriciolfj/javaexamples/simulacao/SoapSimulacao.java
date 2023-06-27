package com.github.fabriciolfj.javaexamples.simulacao;

import com.github.fabriciolfj.javaexamples.service.WeatherServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


public class SoapSimulacao {

    @Autowired
    private WeatherServiceClient client;


    public void run(String... args) throws Exception {
        var result = client.getTodayTemperature("Houston");
        System .out.println("Temperatura mínima: " + result.min());
        System .out.println("Temperatura máxima: " + result.max());
        System .out.println("Temperatura média: " + result.average());
    }
}

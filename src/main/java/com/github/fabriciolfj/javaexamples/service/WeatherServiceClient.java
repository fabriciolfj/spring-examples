package com.github.fabriciolfj.javaexamples.service;

import com.github.fabriciolfj.javaexamples.entity.TemperatureInfo;
import com.github.fabriciolfj.javaexamples.service.impl.WeatherServiceProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class WeatherServiceClient {

    private final WeatherServiceProxy proxy;

    public TemperatureInfo getTodayTemperature(String city) {

        var dates = Collections.singletonList(LocalDate.now());
        var temperatures = proxy.getTemperatures(city, dates);
        return temperatures.get(0);
    }
}

package com.github.fabriciolfj.javaexamples.service.impl;


import com.github.fabriciolfj.javaexamples.entity.TemperatureInfo;
import com.github.fabriciolfj.javaexamples.service.WeatherService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class WeatherServiceImpl implements WeatherService {

    public List<TemperatureInfo> getTemperatures(String city, List<LocalDate> dates) {
        return dates.stream().map(date -> createRandom(city, date)).toList();
    }

    private TemperatureInfo createRandom(String city, LocalDate date) {
        var max = ThreadLocalRandom.current().nextDouble(30);
        var min = ThreadLocalRandom.current().nextDouble(max);
        var avg = (min + max) / 2;
        return new TemperatureInfo(city, date, min, max, avg);
    }
}

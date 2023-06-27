package com.github.fabriciolfj.javaexamples.service;

import com.github.fabriciolfj.javaexamples.entity.TemperatureInfo;

import java.time.LocalDate;
import java.util.List;

public interface WeatherService {

	List<TemperatureInfo> getTemperatures(String city, List<LocalDate> dates);
}

package com.github.fabriciolfj.javaexamples.entity;

import java.time.LocalDate;

public record TemperatureInfo(
				String city, LocalDate date, double min, double max, double average) {
}

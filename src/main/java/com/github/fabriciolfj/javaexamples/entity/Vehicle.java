package com.github.fabriciolfj.javaexamples.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private String vehicleNo;
    private String color;
    private int wheel;
    private int seat;
}

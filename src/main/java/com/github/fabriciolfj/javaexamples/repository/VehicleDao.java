package com.github.fabriciolfj.javaexamples.repository;

import com.github.fabriciolfj.javaexamples.entity.Vehicle;

import java.util.Collection;
import java.util.List;

public interface VehicleDao {

    void insert(Vehicle vehicle);
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);
    Vehicle findByVehicleNo(String vehicleNo);
    List<Vehicle> findAll();

    default String getColor(String vehicleNo) {
        throw new IllegalArgumentException("method not implemented");
    }

    default int countAll() {
        throw new IllegalArgumentException("method not implemented");
    }

    default void insert(List<Vehicle> vehicles) {
        vehicles.forEach(this::insert);
    }
}

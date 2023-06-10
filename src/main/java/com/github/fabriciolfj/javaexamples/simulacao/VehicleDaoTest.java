package com.github.fabriciolfj.javaexamples.simulacao;

import com.github.fabriciolfj.javaexamples.entity.Vehicle;
import com.github.fabriciolfj.javaexamples.repository.VehicleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VehicleDaoTest implements CommandLineRunner {

    @Autowired
    private VehicleDao vehicleDao;

    @Override
    public void run(String... args) throws Exception {
        var vehicles = new ArrayList<>();
        var vehicle = new Vehicle("TEM00011", "red", 4, 4);
        vehicleDao.insert(vehicle);

        var color = vehicleDao.getColor(vehicle.getVehicleNo());
        var total = vehicleDao.countAll();

        System.out.println(color);
        System.out.println(total);

    }
}

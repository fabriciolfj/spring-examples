package com.github.fabriciolfj.javaexamples.service;

import com.github.fabriciolfj.javaexamples.entity.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> query(final String courtName);
}

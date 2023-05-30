package com.github.fabriciolfj.javaexamples.service;

import com.github.fabriciolfj.javaexamples.entity.Player;
import com.github.fabriciolfj.javaexamples.entity.Reservation;
import com.github.fabriciolfj.javaexamples.entity.SportType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InMemoryReservationService implements ReservationService {
    private final List<Reservation> reservations =
            Collections.synchronizedList(new ArrayList<>());

    public InMemoryReservationService() {
        var roger = new Player("Roger", "");
        var james = new Player("James", "");
        var type = new SportType(1, "TENNIS");
        var date = LocalDate.of(2022, 10, 18);
        reservations.add(new Reservation("Tennis #1", date, 16, roger, type));
        reservations.add(new Reservation("Tennis #2", date, 20, james, type));
    }

    @Override
    public List<Reservation> query(String courtName) {
        return this.reservations.stream()
                .filter((r) -> StringUtils.startsWithIgnoreCase(r.getCourtName(), courtName))
                .collect(Collectors.toList());
    }
}

package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.entity.Reservation;
import com.github.fabriciolfj.javaexamples.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping("/reserveQuery")
@RequiredArgsConstructor
public class ReservationQueryController {

    private final ReservationService reservationService;

    @GetMapping
    public String setupForm() {
        return "reserveQuery";
    }

    @PostMapping
    public String submitForm(@RequestParam("courtName") final String courtName, final Model model) {
        var reservations = Collections.<Reservation>emptyList();

        if (courtName != null) {
            reservations = reservationService.query(courtName);
        }

        model.addAttribute("reservations", reservations);
        return "reserveQuery";
    }
}

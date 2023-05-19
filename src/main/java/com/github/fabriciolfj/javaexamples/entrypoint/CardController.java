package com.github.fabriciolfj.javaexamples.entrypoint;

import com.github.fabriciolfj.javaexamples.entity.Card;
import com.github.fabriciolfj.javaexamples.service.ValidateBalance;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final Map<String, ValidateBalance> validateBalances;

    @Qualifier("visa-card")
    @Autowired
    private ValidateBalance validateBalance;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public boolean process(@RequestBody final Card card) {
        final var validate = validateBalances.get(card.getType());
        return validate.execute(card);
    }

    @GetMapping("/visa")
    @ResponseStatus(HttpStatus.ACCEPTED )
    public boolean processVisa(@RequestBody final Card card) {
        return validateBalance.execute(card);
    }
}

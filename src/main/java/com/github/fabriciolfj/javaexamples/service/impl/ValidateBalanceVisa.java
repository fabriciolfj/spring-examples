package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.entity.Card;
import com.github.fabriciolfj.javaexamples.service.ValidateBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Slf4j
@Service("visa")
@Qualifier(value = "visa-card")
public class ValidateBalanceVisa implements ValidateBalance {

    @Override
    public boolean execute(final Card card) {
        log.info("validate visa");
        return card.getCode() >= 11;
    }
}

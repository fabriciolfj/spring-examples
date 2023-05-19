package com.github.fabriciolfj.javaexamples.service.impl;

import com.github.fabriciolfj.javaexamples.entity.Card;
import com.github.fabriciolfj.javaexamples.service.ValidateBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service("master")
@Slf4j
public class ValidateBalanceMaster implements ValidateBalance {

    @Override
    public boolean execute(final Card card) {
        log.info("validate master");
        return card.getCode() <= 10;
    }
}

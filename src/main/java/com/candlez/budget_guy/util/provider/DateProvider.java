package com.candlez.budget_guy.util.provider;

import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DateProvider {

    public Instant getCurrentTimestamp() {
        return Instant.now();
    }
}

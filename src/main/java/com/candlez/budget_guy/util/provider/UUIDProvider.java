package com.candlez.budget_guy.util.provider;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDProvider {

    public UUID generateUUID() {
        return UUID.randomUUID();
    }
}

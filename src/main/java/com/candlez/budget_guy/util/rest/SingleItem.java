package com.candlez.budget_guy.util.rest;

import java.util.List;
import java.util.UUID;

public class SingleItem<T> {

    private UUID id;
    private List<T> items;

    public SingleItem(UUID id, T item) {
        this.id = id;
        this.items = List.of(item);
    }

    // getters and setters
    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

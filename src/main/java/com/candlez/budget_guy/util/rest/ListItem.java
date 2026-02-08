package com.candlez.budget_guy.util.rest;

import java.util.List;

public class ListItem<T> {

    private List<T> items;

    public ListItem(List<T> items) {
        this.items = items;
    }

    // getters and setters
    public List<T> getItems() {
        return this.items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}

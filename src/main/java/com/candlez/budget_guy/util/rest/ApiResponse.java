package com.candlez.budget_guy.util.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class ApiResponse<T> {

    private final String status = "success";

    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public static <T> ResponseEntity<ApiResponse<SingleItem<T>>> sendOne(UUID id, T item) {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(new SingleItem<>(id, item)));
    }

    public static <T> ResponseEntity<ApiResponse<SingleItem<T>>> sendCreated(UUID id, T item) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(new SingleItem<>(id, item)));
    }

    // getters and setters
    public String getStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

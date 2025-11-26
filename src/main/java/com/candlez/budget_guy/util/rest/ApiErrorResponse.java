package com.candlez.budget_guy.util.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiErrorResponse<T> {

    private final String status = "error";

    private T error;

    public ApiErrorResponse(T error) {
        this.error = error;
    }

    public static ResponseEntity<ApiErrorResponse<SingleError>> sendOne(HttpStatus code, String message) {
        return ResponseEntity.status(code).body(new ApiErrorResponse<>(new SingleError(code, message)));
    }

    // getters and setters
    public String getStatus() {
        return this.status;
    }

    public T getError() {
        return this.error;
    }

    public void setError(T error) {
        this.error = error;
    }
}

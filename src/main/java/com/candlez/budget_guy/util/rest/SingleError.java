package com.candlez.budget_guy.util.rest;

import org.springframework.http.HttpStatus;

public class SingleError {

    private HttpStatus code;

    private String message;

    public SingleError(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    // getters and setters
    public HttpStatus getCode() {
        return this.code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.candlez.budget_guy.data.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;

    // getters and setters
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

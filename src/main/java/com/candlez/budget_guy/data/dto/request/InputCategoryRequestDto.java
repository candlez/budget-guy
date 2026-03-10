package com.candlez.budget_guy.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * this DTO contains the necessary information to create or fully replace a Category.
 * it's used in PUT and POST endpoints
 */
public class InputCategoryRequestDto {

    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    private String description;

    // getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

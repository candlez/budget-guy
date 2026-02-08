package com.candlez.budget_guy.data.dto.response;

import com.candlez.budget_guy.data.entity.Category;

import java.time.Instant;
import java.util.UUID;

public class CategoryResponseDto {

    private UUID categoryID;
    private UUID userID;
    private String name;
    private String description;
    private Instant createdAt;

    public static CategoryResponseDto fromCategory(Category category) {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setCategoryID(category.getCategoryID());
        categoryResponseDto.setUserID(category.getUserID());
        categoryResponseDto.setName(category.getName());
        categoryResponseDto.setDescription(category.getDescription());
        categoryResponseDto.setCreatedAt(category.getCreatedAt());
        return categoryResponseDto;
    }

    // getters and setters
    public UUID getCategoryID() {
        return this.categoryID;
    }

    public void setCategoryID(UUID statementID) {
        this.categoryID = statementID;
    }

    public UUID getUserID() {
        return this.userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

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

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

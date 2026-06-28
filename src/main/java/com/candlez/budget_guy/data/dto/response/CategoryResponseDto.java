package com.candlez.budget_guy.data.dto.response;

import java.time.Instant;
import java.util.UUID;

public class CategoryResponseDto {

    private UUID categoryId;
    private UUID userId;
    private String name;
    private String description;
    private Instant createdAt;

    // getters and setters
    public UUID getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(UUID statementID) {
        this.categoryId = statementID;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

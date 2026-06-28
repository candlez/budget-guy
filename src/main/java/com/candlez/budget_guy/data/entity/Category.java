package com.candlez.budget_guy.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at")
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

    public void setUserId(UUID userID) {
        this.userId = userID;
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

package com.candlez.budget_guy.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID transactionId;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

    @Column(name = "category_id")
    private UUID categoryId;

    @Column(name = "statement_id")
    private UUID statementId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "created_at")
    private Instant createdAt;

    // getters and setters
    public UUID getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(UUID transactionID) {
        this.transactionId = transactionID;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(UUID categoryID) {
        this.categoryId = categoryID;
    }

    public UUID getStatementId() {
        return this.statementId;
    }

    public void setStatementId(UUID statementID) {
        this.statementId = statementID;
    }

    public UUID getUserId() {
        return this.userId;
    }

    public void setUserId(UUID userID) {
        this.userId = userID;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

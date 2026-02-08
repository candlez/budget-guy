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
@Table(name = "statements")
public class Statement {

    @Id
    @Column(name = "id", columnDefinition = "BINARY(16)")
    private UUID statementId;

    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "income")
    private BigDecimal income;

    @Column(name = "expenses")
    private BigDecimal expenses;

    @Column(name = "created_at")
    private Instant createdAt;

    // getters and setters
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

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getIncome() {
        return this.income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getExpenses() {
        return this.expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

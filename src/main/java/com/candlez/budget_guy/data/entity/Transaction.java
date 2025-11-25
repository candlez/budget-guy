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
    private UUID transactionID;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "description")
    private String description;

//    @Column(name = "subcategory_id")
//    private UUID subcategoryID;

//    @Column(name = "statement_id")
//    private UUID statementID;

    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "created_at")
    private Instant createdAt;

    // getters and setters
    public UUID getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(UUID transactionID) {
        this.transactionID = transactionID;
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

//    public UUID getSubcategoryID() {
//        return this.subcategoryID;
//    }
//
//    public void setSubcategoryID(UUID subcategoryID) {
//        this.subcategoryID = subcategoryID;
//    }
//
//    public UUID getStatementID() {
//        return this.statementID;
//    }
//
//    public void setStatementID(UUID statementID) {
//        this.statementID = statementID;
//    }

    public UUID getUserID() {
        return this.userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
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

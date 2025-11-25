package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Transaction;
import com.candlez.budget_guy.data.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction createTransaction(BigDecimal amount, String description, UUID subcategoryID, UUID statementID, UUID userID, LocalDate transactionDate) {
        Transaction transaction = new Transaction();

        if (userID == null) {
            // TODO pull user from AsyncLocalStorage (or whatever the Java equivalent is)
        }

        transaction.setAmount(amount);
        transaction.setDescription(description);
//        transaction.setSubcategoryID(subcategoryID);
//        transaction.setStatementID(statementID);
        transaction.setUserID(userID);
        transaction.setTransactionDate(transactionDate);

        transaction.setCreatedAt(Instant.now()); // right now!
        transaction.setTransactionID(UUID.randomUUID());

        return this.transactionRepository.save(transaction);
    }
}

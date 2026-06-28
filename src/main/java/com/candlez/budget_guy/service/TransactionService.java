package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Transaction;
import com.candlez.budget_guy.data.repository.TransactionRepository;
import com.candlez.budget_guy.util.provider.DateProvider;
import com.candlez.budget_guy.util.provider.UUIDProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UUIDProvider uuidProvider;
    private final DateProvider dateProvider;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UUIDProvider uuidProvider, DateProvider dateProvider) {
        this.transactionRepository = transactionRepository;
        this.uuidProvider = uuidProvider;
        this.dateProvider = dateProvider;
    }

    public Transaction createTransaction(
            BigDecimal amount,
            String description,
            UUID categoryId,
            UUID statementId,
            UUID userId,
            LocalDate transactionDate
    ) {
        Transaction transaction = new Transaction();

        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setCategoryId(categoryId);
        transaction.setStatementId(statementId);
        transaction.setUserId(userId);
        transaction.setTransactionDate(transactionDate);

        transaction.setCreatedAt(dateProvider.getCurrentTimestamp());
        transaction.setTransactionId(uuidProvider.generateUUID());

        return this.transactionRepository.save(transaction);
    }
}

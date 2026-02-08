package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Statement;
import com.candlez.budget_guy.data.repository.StatementRepository;
import com.candlez.budget_guy.util.provider.DateProvider;
import com.candlez.budget_guy.util.provider.UUIDProvider;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class StatementService {

    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private final TransactionService transactionService;

    private final StatementRepository statementRepository;

    private final UUIDProvider uuidProvider;
    private final DateProvider dateProvider;

    @Autowired
    public StatementService(
            TransactionService transactionService,
            StatementRepository statementRepository,
            UUIDProvider uuidProvider,
            DateProvider dateProvider
    ) {
        this.transactionService = transactionService;
        this.statementRepository = statementRepository;
        this.uuidProvider = uuidProvider;
        this.dateProvider = dateProvider;
    }

    public Statement createStatementFromCSV(
            MultipartFile file,
            LocalDate startDate,
            LocalDate endDate,
            UUID userId
    ) throws CsvValidationException, IOException {

        Statement statement = this.createStatement(startDate, endDate, userId);
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] row;
            while ((row = reader.readNext()) != null) {
                // 0 -> date
                LocalDate transactionDate = LocalDate.parse(row[0].trim(), CSV_DATE_FORMAT);

                // 1 -> amount
                BigDecimal amount = new BigDecimal(row[1].trim());

                // 4 -> description
                String description = row[4].trim();

                transactionService.createTransaction(
                        amount,
                        description,
                        null,
                        statement.getStatementId(),
                        userId,
                        transactionDate
                );
            }
        }
        return statement;
    }

    private Statement createStatement(LocalDate startDate, LocalDate endDate, UUID userId) {

        Statement statement = new Statement();

        statement.setStartDate(startDate);
        statement.setEndDate(endDate);
        statement.setUserId(userId);

        statement.setIncome(BigDecimal.ZERO);
        statement.setExpenses(BigDecimal.ZERO);
        statement.setCreatedAt(dateProvider.getCurrentTimestamp());
        statement.setStatementId(uuidProvider.generateUUID());

        return this.statementRepository.save(statement);
    }

}

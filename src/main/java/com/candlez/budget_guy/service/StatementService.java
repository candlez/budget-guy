package com.candlez.budget_guy.service;

import com.candlez.budget_guy.data.entity.Statement;
import com.candlez.budget_guy.data.repository.StatementRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class StatementService {

    private static final DateTimeFormatter CSV_DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatementRepository statementRepository;

    public Statement createStatementFromCSV(MultipartFile file, LocalDate startDate, LocalDate endDate) throws CsvValidationException, IOException {

        UUID userID = UUID.fromString("59293386-c9a8-11f0-b987-0242ac110003");
        Statement statement = this.createStatement(startDate, endDate, userID);
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] row;
            while ((row = reader.readNext()) != null) {
                // 0 -> date
                LocalDate transactionDate = LocalDate.parse(row[0].trim(), CSV_DATE_FORMAT);

                // 1 -> amount
                BigDecimal amount = new BigDecimal(row[1].trim());

                // 4 -> description
                String description = row[4].trim();

                transactionService.createTransaction(amount, description, null, null, userID, transactionDate);
            }
        }
        return statement;
    }

    private Statement createStatement(LocalDate startDate, LocalDate endDate, UUID userID) {

        Statement statement = new Statement();

        if (userID == null) {
            // TODO pull userID from whatever and whatnot (and remove it from method signature)
        }

        statement.setStartDate(startDate);
        statement.setEndDate(endDate);
        statement.setUserID(userID);

        statement.setIncome(BigDecimal.ZERO);
        statement.setExpenses(BigDecimal.ZERO);
        statement.setCreatedAt(Instant.now());
        statement.setStatementID(UUID.randomUUID());

        return this.statementRepository.save(statement);
    }

}

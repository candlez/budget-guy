package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.data.entity.Statement;
import com.candlez.budget_guy.service.StatementService;
import com.candlez.budget_guy.util.rest.ApiResponse;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/statement")
public class StatementController {

    private final StatementService statementService;

    @Autowired
    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadStatement(
            @RequestParam("statement") MultipartFile file,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @AuthenticationPrincipal UUID userId
    ) throws IOException, CsvValidationException {

        Statement createdStatement = this.statementService.createStatementFromCSV(file, startDate, endDate, userId);
        return ApiResponse.sendCreated(createdStatement.getStatementId(), createdStatement);
    }
}

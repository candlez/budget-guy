package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.data.entity.Statement;
import com.candlez.budget_guy.service.StatementService;
import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import com.candlez.budget_guy.util.rest.ApiResponse;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/statement")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadStatement(
            @RequestParam("statement") MultipartFile file,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {

        Statement createdStatement;
        try { // need logging
            createdStatement = this.statementService.createStatementFromCSV(file, startDate, endDate);
        } catch (CsvValidationException e) {
            return ApiErrorResponse.sendOne(HttpStatus.BAD_REQUEST, "The CSV you gave me could not be processed");
        } catch (Exception e) {
            return ApiErrorResponse.sendOne(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong unexpectedly");
        }
        return ApiResponse.sendCreated(createdStatement.getStatementID(), createdStatement);
    }
}

package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.service.StatementService;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController("/statement")
public class StatementController {

    @Autowired
    private StatementService statementService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadStatement(
            @RequestParam("statement") MultipartFile file,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate
    ) {

        try {
            this.statementService.createStatementFromCSV(file, startDate, endDate);
        } catch (CsvValidationException e) {
            return ResponseEntity.badRequest().body("The CSV you gave me could not be processed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Something went wrong unexpectedly");
        }
        return ResponseEntity.ok("erm wattesigma");
    }
}

package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.service.StatementService;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;

@RestController("/statement")
public class StatementController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadStatement(@RequestParam("statement") MultipartFile file) {

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            String[] row;
            while ((row = reader.readNext()) != null) {

                for (int i = 0; i < row.length; i++) {
                    System.out.println("[ " + row[i] + " ]");
                }
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong unexpectedly");
        }
        return ResponseEntity.ok("erm wattesigma");
    }
}

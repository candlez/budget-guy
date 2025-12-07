package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.exception.UnauthorizedException;
import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Optional;

@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleUncaughtException(HttpServletRequest req, Exception e) {

        LOGGER.error("Encountered an unhandled Exception.", e);
        return ApiErrorResponse.sendOne(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong unexpectedly");
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(HttpServletRequest req, NoResourceFoundException e) {

        String errMsg = Optional.ofNullable(e.getMessage()).orElse("The server could not find the resource you requested.");
        return ApiErrorResponse.sendOne(HttpStatus.NOT_FOUND, errMsg);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(HttpServletRequest req, UnauthorizedException e) {

        String errMsg = Optional.ofNullable(e.getMessage()).orElse("You are not authorized to view this resource");
        return ApiErrorResponse.sendOne(HttpStatus.UNAUTHORIZED, errMsg);
    }
}

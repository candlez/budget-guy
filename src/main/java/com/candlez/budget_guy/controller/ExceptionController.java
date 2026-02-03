package com.candlez.budget_guy.controller;

import com.candlez.budget_guy.annotation.SupportsHTML;
import com.candlez.budget_guy.exception.NotFoundException;
import com.candlez.budget_guy.exception.UnauthorizedException;
import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Optional;

@ControllerAdvice
public class ExceptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @SupportsHTML(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleUncaughtException(HttpServletRequest req, Exception e) {

        // interesting note: Exceptions that occur in these handlers will not be caught by another handler.
        // instead, they will be routed to the AuthenticationEntryPoint, so users may get a 401 in that case.
        // currently looking into a way to correct this behavior.

        LOGGER.error("Encountered an unhandled Exception.", e);
        return ApiErrorResponse.sendOne(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong unexpectedly");
    }

    @SupportsHTML(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoResourceFoundException.class)
    public Object handleNoResourceFoundException(HttpServletRequest req, HttpServletResponse res, NoResourceFoundException e) {

        String errMsg = Optional.ofNullable(e.getMessage()).orElse("The server could not find the resource you requested.");
        return ApiErrorResponse.sendOne(HttpStatus.NOT_FOUND, errMsg);
    }

    @SupportsHTML(value = HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = HttpMediaTypeNotAcceptableException.class)
    public Object handleMediaTypeNotAcceptableException(
            HttpServletRequest req,
            HttpServletResponse res,
            HttpMediaTypeNotAcceptableException e
    ) {

        String errMsg = Optional.ofNullable(e.getMessage())
                .orElse("The server does not support the media type you requested.");
        return ApiErrorResponse.sendOne(HttpStatus.NOT_ACCEPTABLE, errMsg);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(HttpServletRequest req, NotFoundException e) {

        String errMsg = Optional.ofNullable(e.getMessage()).orElse("The server could not find the resource you requested.");
        return ApiErrorResponse.sendOne(HttpStatus.NOT_FOUND, errMsg);
    }

    @SupportsHTML(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException(HttpServletRequest req, UnauthorizedException e) {

        String errMsg = Optional.ofNullable(e.getMessage()).orElse("You are not authorized to view this resource");
        return ApiErrorResponse.sendOne(HttpStatus.UNAUTHORIZED, errMsg);
    }
}

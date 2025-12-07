package com.candlez.budget_guy.security;

import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Spring handles Exceptions in the security chain differently than it handles
 * exceptions thrown in the controllers, so I'm going to make this custom
 * component to write my errors and keep my responses consistent.
 */
@Component
public class SecurityErrorResponseWriter {

    private final ObjectMapper objectMapper;

    @Autowired
    public SecurityErrorResponseWriter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void write(HttpServletResponse response, HttpStatus status, ApiErrorResponse<?> body) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        this.objectMapper.writeValue(response.getWriter(), body);
    }
}

package com.candlez.budget_guy.security;

import com.candlez.budget_guy.exception.UnauthorizedException;
import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import com.candlez.budget_guy.util.rest.SingleError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * replaces the default AuthenticationEntryPoint from Spring Security
 * <br>
 * allows for sending consistent response structures
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final SecurityErrorResponseWriter writer;

    @Autowired
    public JwtAuthenticationEntryPoint(SecurityErrorResponseWriter writer) {
        this.writer = writer;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        ApiErrorResponse<SingleError> errorResponse;
        if (authException instanceof UnauthorizedException) {
            errorResponse = new ApiErrorResponse<>(new SingleError(HttpStatus.UNAUTHORIZED, authException.getMessage()));
        } else if (request.getAttribute(SecurityConfig.REQUEST_ERROR_HOLDER_NAME) instanceof UnauthorizedException ue) {
            errorResponse = new ApiErrorResponse<>(new SingleError(HttpStatus.UNAUTHORIZED, ue.getMessage()));
        } else {
            errorResponse = new ApiErrorResponse<>(new SingleError(HttpStatus.UNAUTHORIZED, "Unauthorized"));
        }
        writer.write(response, HttpStatus.UNAUTHORIZED, errorResponse);
    }
}

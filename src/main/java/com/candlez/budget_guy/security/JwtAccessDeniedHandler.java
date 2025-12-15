package com.candlez.budget_guy.security;

import com.candlez.budget_guy.exception.ForbiddenException;
import com.candlez.budget_guy.util.rest.ApiErrorResponse;
import com.candlez.budget_guy.util.rest.SingleError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * replaces the default AccessDeniedHandler from Spring Security
 * <br>
 * allows for sending consistent response structures
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final SecurityErrorResponseWriter writer;

    @Autowired
    public JwtAccessDeniedHandler(SecurityErrorResponseWriter writer) {
        this.writer = writer;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        ApiErrorResponse<SingleError> errorResponse;
        if (accessDeniedException instanceof ForbiddenException) {
            errorResponse = new ApiErrorResponse<>(new SingleError(HttpStatus.FORBIDDEN, accessDeniedException.getMessage()));
        } else if (request.getAttribute(SecurityConfig.REQUEST_ERROR_HOLDER_NAME) instanceof ForbiddenException fe) {
            // check the pattern out above
            // I didn't know about this until Intellij recommended it to me
            errorResponse = new ApiErrorResponse<>(new SingleError(HttpStatus.FORBIDDEN, fe.getMessage()));
        } else {
            errorResponse = new ApiErrorResponse<>(new SingleError(HttpStatus.FORBIDDEN, "Forbidden"));
        }
        writer.write(response, HttpStatus.FORBIDDEN, errorResponse);
    }
}

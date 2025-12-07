package com.candlez.budget_guy.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * this is a custom Exception class designed to encapsulate all scenarios in
 * which the server should return a 403 to the client.
 * <br>
 * it extends AccessDeniedException in order to play nice with the Spring
 * authentication filter chain
 * <br>
 * however, it is no longer thrown in the filter chain because that is
 * not how Spring auth should work
 */
public class ForbiddenException extends AccessDeniedException {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Exception cause) {
        super(message, cause);
    }
}

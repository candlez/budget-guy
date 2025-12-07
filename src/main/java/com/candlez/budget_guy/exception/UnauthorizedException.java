package com.candlez.budget_guy.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * this is a custom Exception class designed to encapsulate all scenarios in
 * which the server should return a 401 to the client.
 * <br>
 * it extends Authentication in order to play nice with the Spring
 * authentication filter chain
 * <br>
 * however, it is no longer thrown in the filter chain because that is
 * not how Spring auth should work
 */
public class UnauthorizedException extends AuthenticationException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Exception cause) {
        super(message, cause);
    }
}

package com.candlez.budget_guy.security;

import com.candlez.budget_guy.exception.UnauthorizedException;
import com.candlez.budget_guy.service.AuthService;
import com.candlez.budget_guy.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest req,
            @NonNull HttpServletResponse res,
            @NonNull FilterChain chain
    ) throws ServletException, IOException {

        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            // we can't throw an exception here or anything like that
            // because we need to let the AuthorizationFilter throw.
            // instead, we will simply pass the request along with nothing
            // in the SecurityContext holder and attach some information to
            // the request to be used later.
            LOGGER.debug("Request has no cookies.");
            this.setSecurityError(req, new UnauthorizedException("Missing Token"));
            chain.doFilter(req, res);
            return;
        }

        Optional<Cookie> optionalCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(AuthService.JWT_COOKIE_NAME))
                .findFirst();

        if (optionalCookie.isEmpty()) {
            LOGGER.debug("Request has no cookie named: {}.", AuthService.JWT_COOKIE_NAME);
            this.setSecurityError(req, new UnauthorizedException("Missing Token"));
            chain.doFilter(req, res);
            return;
        }
        Cookie cookie = optionalCookie.get();

        UUID userId;
        try {
            userId = jwtService.extractUserId(cookie.getValue());
        } catch (Exception e) {
            LOGGER.debug("Could not extract User ID from token.", e);
            this.setSecurityError(req, new UnauthorizedException("Invalid Token", e));
            chain.doFilter(req, res);
            return;
        }

        Authentication auth = new PreAuthenticatedAuthenticationToken(userId, null, List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(req, res);
    }

    private void setSecurityError(HttpServletRequest req, Exception e) {
        req.setAttribute(SecurityConfig.REQUEST_ERROR_HOLDER_NAME, e);
    }
}

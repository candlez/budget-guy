package com.candlez.budget_guy.security;

import org.springframework.http.ResponseCookie;

public interface CookieBuilderFactory {
    ResponseCookie.ResponseCookieBuilder getBuilder();
}

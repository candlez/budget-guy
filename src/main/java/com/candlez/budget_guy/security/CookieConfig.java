package com.candlez.budget_guy.security;

import com.candlez.budget_guy.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseCookie;

@Configuration
public class CookieConfig {

    @Bean
    @Profile("local")
    public CookieBuilderFactory getCookieBuilderLocal() {
        // apparently you can just return a lambda that conforms to the interface.
        // the interface can only have one method and the lambda's signature must
        // exactly match the interface's method.
        // the more you know!
        return () -> ResponseCookie.from(AuthService.JWT_COOKIE_NAME)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(AuthService.THREE_DAYS)
                .sameSite("Lax");
    }

    @Bean
    @Profile("!local")
    public CookieBuilderFactory getCookieBuilder() {
        return () -> ResponseCookie.from(AuthService.JWT_COOKIE_NAME)
                .httpOnly(true)
                .secure(true) // requires https in the browser
                .path("/")
                .maxAge(AuthService.THREE_DAYS)
                .sameSite("Lax");

        // secure means that browsers will not send the cookie over an http connection.
        // this doesn't affect Postman at all, but if testing the UI locally against a
        // local copy of the API, this would be a problem.
        // thankfully, I have set these profiles up to turn this off when developing locally.
    }
}

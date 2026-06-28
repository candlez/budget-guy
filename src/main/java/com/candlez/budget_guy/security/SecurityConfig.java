package com.candlez.budget_guy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    public static final String REQUEST_ERROR_HOLDER_NAME = "securityError";

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) throws Exception {

        // disable csrf tokens and the form based logout stuff
        http.csrf(AbstractHttpConfigurer::disable);
        http.logout(AbstractHttpConfigurer::disable);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // all requests starting with "/api" will require a JWT except "/api/auth/login" and "/api/auth/signup"
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/login", "/api/auth/signup").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
        );

        http.addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        /*
         * documenting what I learned, so I can remind myself later:
         * all exceptions in chain will get sent to one of these two
         * handlers
         *
         * AccessDeniedExceptions   -> AccessDeniedHandler
         * AuthenticationException  -> AuthenticationEntryPoint
         * All Other Exceptions     -> AuthenticationEntryPoint
         */
        http.exceptionHandling(e -> e
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        );

        return http.build();
    }

    /**
     * we don't need a UserDetailsService, but we will define one to placate Spring Security
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            throw new RuntimeException("This is a fake service. It should not be used.");
        };
    }
}

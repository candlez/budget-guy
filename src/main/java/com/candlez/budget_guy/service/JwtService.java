package com.candlez.budget_guy.service;

import com.candlez.budget_guy.util.provider.DateProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final SecretKey key;

    private final DateProvider dateProvider;

    @Autowired
    public JwtService(SecretKey key, DateProvider dateProvider) {
        this.key = key;
        this.dateProvider = dateProvider;
    }

    public String generateToken(String userId) {
        Instant now = dateProvider.getCurrentTimestamp();

        return Jwts.builder()
                .subject(userId)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(AuthService.THREE_DAYS)))
                .signWith(this.key)
                .compact();
    }

    public UUID extractUserId(String token) {
        return  UUID.fromString(this.extractPayload(token).getSubject());
    }

    private Claims extractPayload(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith(this.key)
                .build();

        return parser.parseSignedClaims(token).getPayload();
    }
}

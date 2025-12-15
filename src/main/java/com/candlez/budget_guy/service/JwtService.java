package com.candlez.budget_guy.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private static final String JWT_SECRET = "placeholderplaceholderplaceholder";

    private final SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes());

    public String generateToken(String userId) {
        Instant now = Instant.now();

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

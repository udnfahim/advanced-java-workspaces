package com.quiz4.service;

import com.quiz4.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private static final long EXPIRATION = 24 * 60 * 60 * 1000; // 1 day default

    private final SecretKey JWT_SECRET_KEY = Keys.hmacShaKeyFor(
            "using-256-bit-secret-key-what-must-be-at-least-32bytes!".getBytes(StandardCharsets.UTF_8)
    );

    public String generateToken(String username, Set<Role> roles, boolean rememberMe) {

        long expirationMillis = rememberMe ? 3 * 24 * 60 * 60 * 1000L : EXPIRATION; // 3 days if rememberMe

        String rolesString = roles.stream()
                .map(Role::name)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", rolesString)
                .claim("rememberMe", rememberMe)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(JWT_SECRET_KEY)
                .compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsername(String token) {
        return parseToken(token).getSubject();
    }

    public Set<Role> getRoles(String token) {

        String rolesStr = parseToken(token).get("roles", String.class);

        if (rolesStr == null || rolesStr.isEmpty()) return Set.of();

        return Set.of(rolesStr.split(",")).stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}

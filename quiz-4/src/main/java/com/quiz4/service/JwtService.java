//package com.quiz4.service;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//import java.util.Map;
//
//@Service
//public class JwtService {
//
//    private final byte[] JWT_SECRET_BYTES = "your-256-bit-secret-key-must-be-32bytes!!".getBytes(StandardCharsets.UTF_8);
//    private final SecretKey JWT_SECRET_KEY = Keys.hmacShaKeyFor(JWT_SECRET_BYTES);
//
//    public String generateToken(String username, String role) {
//        Map<String, Object> claims = Map.of(
//                "role", role,
//                "name", username
//        );
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setClaims(claims)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
//                .signWith(JWT_SECRET_KEY)
//                .compact();
//    }
//
//    public Claims parseToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(JWT_SECRET_KEY)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    public String getUsername(String token) {
//        return parseToken(token).getSubject();
//    }
//
//    public String getRole(String token) {
//        return parseToken(token).get("role", String.class);
//    }
//
//}

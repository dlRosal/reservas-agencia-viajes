package org.example.authservice.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

/**
 * Clase para gestionar tokens JWT:
 * - Generar token con usuario.
 * - Validar token.
 * - Extraer información del token.
 */
@Component
public class JwtUtil {

    private final String SECRET_KEY = "12345678901234567890123456789012"; // Clave secreta (32+ caracteres)
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hora

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // Genera clave segura
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Guarda el usuario en el token
                .setIssuedAt(new Date()) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Expira en 1 hora
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Algoritmo de firma
                .compact();
    }

    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token); // Verifica usuario y expiración
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject); // Extrae el usuario del token
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date()); // Comprueba si expiró
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // Usa la clave secreta
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims); // Extrae información específica
    }
}

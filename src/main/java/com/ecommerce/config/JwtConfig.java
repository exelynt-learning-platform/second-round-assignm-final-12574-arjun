package com.ecommerce.config;


import com.ecommerce.util.AppConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtConfig {

//    String secret = "mySuperSecretKeyForJwtAuthentication123456";
//    private final Key key = Keys.hmacShaKeyFor(AppConstants.SECRET_KEY.getBytes());

    private final SecretKey key;

    public JwtConfig() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // ✅ FIXED
    }

    //  Generate Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + AppConstants.JWT_EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Username
    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Validate Token
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    //  Get Claims
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

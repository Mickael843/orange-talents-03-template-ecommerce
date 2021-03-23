package com.mikkaeru.ecommerce.security;

import com.mikkaeru.ecommerce.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManager {

    @Value("${ecommerce.jwt.secret}")
    private String secret;
    @Value("${ecommerce.jwt.expiration}")
    private String expiration;

    public String tokenGeneration(Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Date today = new Date();

        return Jwts.builder()
                .setIssuer("Ecommerce API")
                .setSubject(user.getId().toString())
                .setIssuedAt(today)
                .setExpiration(new Date(today.getTime() + Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public boolean isValidToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public long getIdUser(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}

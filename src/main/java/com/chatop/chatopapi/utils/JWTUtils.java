package com.chatop.chatopapi.utils;

import com.chatop.chatopapi.exceptions.AccessDeniedException;
import com.chatop.chatopapi.exceptions.InvalidTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;



@Component
public class JWTUtils {



    @Autowired
    private Environment env;



    private final Logger logger = LogManager.getLogger(JWTUtils.class);
    @Value("${JWT_SECRET}")
    private String jwtSecret;


    private static final int MINUTES = 60;





    public String generateToken(String email, String username, Integer userId) {
        var now = Instant.now();


        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .claim("userId", userId)
                .setExpiration(Date.from(now.plus(MINUTES, ChronoUnit.MINUTES)))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    public Integer extractId(String token) throws InvalidTokenException {
        try {
            Claims claims = getTokenBody(token);
            return claims.get("userId", Integer.class);
        } catch (MalformedJwtException e) {
            throw new InvalidTokenException("Invalid JWT token");
        }
    }


    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Claims getTokenBody(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        } catch (SignatureException | ExpiredJwtException e) { // Invalid signature or expired token
            throw new AccessDeniedException("Access denied: " + e.getMessage());
        }
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private boolean isTokenExpired(String token) {
        Claims claims = getTokenBody(token);
        return claims.getExpiration().before(new Date());
    }






}

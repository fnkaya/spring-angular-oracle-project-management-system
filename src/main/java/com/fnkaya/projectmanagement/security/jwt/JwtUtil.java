package com.fnkaya.projectmanagement.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JwtUtil {

    public static String generateToken(Authentication authentication, String key, Integer expirationDay) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", getAuthorities(authentication))
                .setIssuedAt(new Date())
                .setExpiration(expirationDate(expirationDay))
                .signWith(Keys.hmacShaKeyFor(key.getBytes()))
                .compact();
    }

    private static List<String> getAuthorities(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    private static Date expirationDate(Integer expirationDay) {
        Instant expirationTime = LocalDate.now()
                .plusDays(expirationDay)
                .atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Date.from(expirationTime);
    }

    public static String extractUsername(String token, String key) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

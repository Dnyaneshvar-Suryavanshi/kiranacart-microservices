package com.kiranacart.auth.security;

import com.kiranacart.auth.entity.Permission;
import com.kiranacart.auth.entity.Role;
import com.kiranacart.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    private static final long ACCESS_TOKEN_VALIDITY_MS = 15 * 60 * 1000;

    public String generateToken(User user){
        List<String> roles = user.getRoles().stream()
                .map(Role::getName)
                .toList();

        List<String> permissions = user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission:: getCode)
                .distinct()
                .toList();

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .claim("roles", roles)
                .claim("permissions", permissions)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_MS))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }
}

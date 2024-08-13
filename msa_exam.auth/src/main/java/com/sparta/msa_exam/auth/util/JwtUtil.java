package com.sparta.msa_exam.auth.util;

import com.sparta.msa_exam.auth.dto.JwtTokenResponseDto;
import com.sparta.msa_exam.auth.dto.SignInRequestDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil implements InitializingBean {
    @Value("${service.jwt.secret-key}")
    private String secretKey;

    private Key key;

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public JwtTokenResponseDto createAuthToken(Long userId, String role) {
        String accessToken = Jwts.builder()
                .claim("user_id", userId)
                .claim("role", role)
                .issuer(issuer)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secretKey)
                .compact();
        return new JwtTokenResponseDto(accessToken);
    }

}

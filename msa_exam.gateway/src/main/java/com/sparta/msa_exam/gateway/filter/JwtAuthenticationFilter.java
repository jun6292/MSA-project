package com.sparta.msa_exam.gateway.filter;

import com.sparta.msa_exam.gateway.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.util.Date;

@Slf4j
@Component
public class JwtAuthenticationFilter implements GlobalFilter {

    private final String secretKey;
    private final AuthService authService;

    public JwtAuthenticationFilter(
            @Value("${service.jwt.secret-key}") String secretKey,
            @Lazy AuthService authService
    ) {
        this.secretKey = secretKey;
        this.authService = authService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        if (path.startsWith("/auth")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange);

        if (token == null || !validateToken(token, exchange)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        return chain.filter(exchange);
    }

    private String extractToken(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean validateToken(String token, ServerWebExchange exchange) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
            Jws<Claims> claimsJws = Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token);
            log.info("##### payload :: " + claimsJws.getPayload().toString());
            Claims claims = claimsJws.getBody();

            // 토큰 만료 여부 확인
            if (claims.getExpiration().before(new Date())) {
                return false;
            }

            // JWT 값 중 Payload 부분에 user_id 로 설정된 값이 있는 경우
            if (claimsJws.getPayload().get("user_id") != null) {
                // user_id 추출 로직
                String userId = claimsJws.getPayload().get("user_id").toString();
                // user_id 값으로 해당 유저가 회원가입한 유저인지 auth service를 통해 확인
                log.info("##### user_id :: " + userId);
                return authService.verifyUser(userId);
            } else {
                return false;
            }

            // feign client를 활용하여 auth의 토큰 검사 API 호출
            // DB에 user 정보가 있는지 확인
            // user 정보가 없다면 false 반환

        } catch (ExpiredJwtException e) {
            log.warn("Token expired", e);
            return false;
        } catch (Exception e) {
            log.warn("Invalid token", e);
            return false;
        }
    }
}
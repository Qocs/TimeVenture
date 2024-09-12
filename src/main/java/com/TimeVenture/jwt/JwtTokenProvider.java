package com.TimeVenture.jwt;

import com.TimeVenture.exception.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    /*
    JWT 토큰을 생성하고 검증하는 클래스
     */
    @Value("${jwt.secret}")
    private String secretKeyText; //평문 비밀 키(인코딩 되기 전)

    @Value("${jwt.expiration}")
    private long expiration;

    @Getter
    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenValidityInSeconds;

    private SecretKey secretKey; // 실제 서명에 사용할 SecretKey 객체

    @PostConstruct
    protected void init() {
        // 인코딩된 비밀 키를 사용하여 SecretKey 객체 생성
        byte[] keyBytes = Base64.getDecoder().decode(secretKeyText);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    //JWT 토큰 생성
    public String createToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date validity = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String createRefreshToken(String email) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInSeconds * 1000);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    //JWT 토큰에서 클레임 추출
    public Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //JWT 토큰에서 사용자 이름 추출
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    //토큰 시간 만료 체크
    public boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    //JWT 토큰 유효성 체크
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtTokenInvalidException("유효하지 않은 JWT 토큰입니다.");
        }
    }

    public String getEmailFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            throw new JwtTokenExpiredException("JWT 토큰이 만료되었습니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtTokenUnsupportedException("지원되지 않는 JWT 토큰입니다.");
        } catch (MalformedJwtException e) {
            throw new JwtTokenMalformedException("JWT 토큰이 잘못되었습니다.");
        } catch (SignatureException e) {
            throw new JwtTokenSignatureException("JWT 서명이 잘못되었습니다.");
        } catch (IllegalArgumentException e) {
            throw new JwtTokenMissingException("JWT 토큰이 없습니다.");
        }
    }

    /*
    문제점: 서버 부하 원인
짧은 토큰 수명:

액세스 토큰의 만료 시간이 짧을수록, 클라이언트는 더 자주 리프레시 토큰을 사용하여 새로운 액세스 토큰을 요청하게 됩니다.
대량의 인증 요청:

사용자가 많아질수록 인증 요청과 토큰 갱신 요청이 증가하여 서버의 부하가 가중됩니다.

토큰 수명 최적화:

액세스 토큰의 만료 시간을 적절히 조정하여, 너무 짧지 않도록 설정합니다(예: 15~30분).
리프레시 토큰의 만료 시간을 길게 설정하여, 클라이언트가 주기적으로 새로운 액세스 토큰을 요청할 수 있게 합니다(예: 7일 또는 30일).
캐싱 도입:

인증된 사용자 정보(예: 사용자 권한 정보)를 캐싱하여, 매번 데이터베이스를 조회하지 않고 빠르게 인증을 처리할 수 있습니다.
Redis나 Memcached와 같은 인메모리 데이터베이스를 사용하여 캐싱된 데이터를 효과적으로 관리합니다.
비동기 요청 처리:

비동기 처리 방식을 도입하여, 인증 요청이 병목 현상을 일으키지 않도록 합니다.
예를 들어, 리프레시 토큰을 사용한 요청을 비동기적으로 처리하여 성능을 최적화할 수 있습니다.
     */
}

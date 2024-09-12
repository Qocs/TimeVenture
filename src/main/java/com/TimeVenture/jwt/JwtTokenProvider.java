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
}

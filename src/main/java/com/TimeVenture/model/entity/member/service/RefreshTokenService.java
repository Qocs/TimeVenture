package com.TimeVenture.model.entity.member.service;

import com.TimeVenture.jwt.JwtTokenProvider;
import com.TimeVenture.model.entity.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {

    private final StringRedisTemplate redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private static final long REFRESH_TOKEN_EXPIRY_SECONDS = 7 * 24 * 60 * 60; // 리프레시 토큰 만료 시간 : 7일

    //리프레시 토큰 생성 및 저장
    public String createAndSaveRefreshToken(Member member) {
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getEmail());
        Timestamp refreshTokenExpiry = Timestamp.from(Instant.now().plusSeconds(REFRESH_TOKEN_EXPIRY_SECONDS));

        //Redis에 저장
        redisTemplate.opsForValue().set(getRedisKey(member.getEmail()), refreshToken, REFRESH_TOKEN_EXPIRY_SECONDS, TimeUnit.SECONDS);

        return refreshToken;
    }

    public String getRefreshToken(String email) {
        return redisTemplate.opsForValue().get(getRedisKey(email));
    }

    private String getRedisKey(String email) {
        return "refreshToken: " + email;
    }

    //리프레시 토큰 삭제
    public void deleteRefreshToken(String email) {
        redisTemplate.delete(getRedisKey(email));
    }
}

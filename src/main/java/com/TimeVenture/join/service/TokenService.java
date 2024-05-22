package com.TimeVenture.join.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final static long TOKEN_VALIDITY = 180; // 3분

    public String generateToken(String email) {
        // 간단한 토큰 생성 (실제 환경에서는 더 안전한 방법을 사용해야 합니다)
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(email, token, TOKEN_VALIDITY, TimeUnit.SECONDS);
        return token;
    }

    public boolean validateToken(String email, String token) {
        String redisToken = redisTemplate.opsForValue().get(email);
        return token.equals(redisToken);
    }
}
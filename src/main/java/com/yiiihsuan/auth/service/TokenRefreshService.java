package com.yiiihsuan.auth.service;

import com.yiiihsuan.auth.controller.request.RefreshTokenRequest;
import com.yiiihsuan.auth.controller.response.TokenResponse;
import com.yiiihsuan.auth.utils.JwtUtils;
import com.yiiihsuan.auth.worker.rest.MemberWorker;
import com.yiiihsuan.auth.worker.rest.response.MemberDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
@Service
public class TokenRefreshService {
    private final MemberWorker memberWorker;
    private final JwtUtils jwtUtils;
    private final RedisTemplate<String, String> redisTemplate;


    public TokenResponse request(RefreshTokenRequest input) {
        String refreshToken = input.getRefreshToken();
        Claims claims = jwtUtils.validateToken(refreshToken);

        if (refreshToken == null || claims == null || claims.getSubject() == null) {
            throw new RuntimeException("Invalid token");
        }

        Long memberId = Long.parseLong(claims.getSubject());

        // 從 Redis 檢查 Refresh Token 是否有效
        String storedToken = redisTemplate.opsForValue().get(getRedisKey(memberId));
        if (storedToken == null || !storedToken.equals(refreshToken)) {
            throw new RuntimeException("Invalid or expired refresh token");
        }

        MemberDto memberDto = memberWorker.findById(memberId);

        if (memberDto == null) {
            throw new RuntimeException("Invalid token");
        }

        String accessToken = jwtUtils.generateAccessToken(memberId);
        refreshToken = jwtUtils.generateRefreshToken(memberId);
        redisTemplate.opsForValue().set(getRedisKey(memberId), refreshToken, JwtUtils.REFRESH_TOKEN_EXPIRATION, TimeUnit.SECONDS);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(JwtUtils.ACCESS_TOKEN_EXPIRATION/1000)
                .tokenType("Bearer")
                .build();
    }
    public void storeRefreshToken(Long memberId, String refreshToken) {
        redisTemplate.opsForValue().set(getRedisKey(memberId), refreshToken, JwtUtils.REFRESH_TOKEN_EXPIRATION, TimeUnit.SECONDS);
    }

    public void deleteRefreshToken(Long memberId) {
        redisTemplate.delete(getRedisKey(memberId));
    }

    private String getRedisKey(Long memberId) {
        return "refresh_token:" + memberId;
    }
}

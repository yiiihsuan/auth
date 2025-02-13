package com.yiiihsuan.auth.service;

import com.yiiihsuan.auth.controller.request.MemberLoginRequest;
import com.yiiihsuan.auth.controller.request.RefreshTokenRequest;
import com.yiiihsuan.auth.controller.response.TokenResponse;
import com.yiiihsuan.auth.utils.JwtUtils;
import com.yiiihsuan.auth.worker.rest.MemberWorker;
import com.yiiihsuan.auth.worker.rest.response.MemberDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class TokenRefreshService {
    private final MemberWorker memberWorker;
    private final JwtUtils jwtUtils;

    public TokenResponse request(RefreshTokenRequest input) {
        String refreshToken = input.getRefreshToken();
        Claims claims = jwtUtils.validateToken(refreshToken);

        if (refreshToken == null || claims == null || claims.getSubject() == null) {
            throw new RuntimeException("Invalid token");
        }

        Long memberId = Long.parseLong(claims.getSubject());
        MemberDto memberDto = memberWorker.findById(memberId);

        if (memberDto == null) {
            throw new RuntimeException("Invalid token");
        }

        String accessToken = jwtUtils.generateAccessToken(memberId);
        // do not rotate
        // String refreshToken = jwtUtils.generateRefreshToken(memberId);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(JwtUtils.ACCESS_TOKEN_EXPIRATION/1000)
                .tokenType("Bearer")
                .build();
    }
}

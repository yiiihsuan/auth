package com.yiiihsuan.auth.service;

import com.yiiihsuan.auth.controller.request.MemberLoginRequest;
import com.yiiihsuan.auth.controller.response.TokenResponse;
import com.yiiihsuan.auth.utils.JwtUtils;
import com.yiiihsuan.auth.worker.rest.MemberWorker;
import com.yiiihsuan.auth.worker.rest.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberLoginService {
    private final MemberWorker memberWorker;
    private final JwtUtils jwtUtils;

    public TokenResponse request(MemberLoginRequest input) {
        MemberDto memberDto = memberWorker.findByEmail(input.getEmail());

        if (memberDto == null || memberDto.getPassword() == null || !memberDto.getPassword().equals(input.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        String accessToken = jwtUtils.generateAccessToken(memberDto.getId());
        String refreshToken = jwtUtils.generateRefreshToken(memberDto.getId());

        TokenResponse res = new TokenResponse();
        res.setAccessToken(accessToken);
        res.setRefreshToken(refreshToken);
        res.setExpiresIn(jwtUtils.ACCESS_TOKEN_EXPIRATION/1000);
        res.setTokenType("Bearer");

        return res;
    }
}

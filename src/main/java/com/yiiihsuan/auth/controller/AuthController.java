package com.yiiihsuan.auth.controller;

import com.yiiihsuan.auth.controller.request.MemberLoginRequest;
import com.yiiihsuan.auth.controller.request.RefreshTokenRequest;
import com.yiiihsuan.auth.controller.response.MeResponse;
import com.yiiihsuan.auth.controller.response.TokenResponse;
import com.yiiihsuan.auth.filter.CustomUserDetails;
import com.yiiihsuan.auth.service.MemberLoginService;
import com.yiiihsuan.auth.service.TokenRefreshService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor // IoC
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final MemberLoginService memberLoginService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody MemberLoginRequest input) {
        return ResponseEntity.ok(memberLoginService.request(input));
    }

    private final TokenRefreshService tokenRefreshService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody RefreshTokenRequest input) {
        return ResponseEntity.ok(tokenRefreshService.request(input));
    }

    @PostMapping("/me")
    public ResponseEntity<MeResponse> me(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return ResponseEntity.ok(MeResponse.builder()
                        .name(customUserDetails.getUsername())
                        .email(customUserDetails.getEmail())
                        .phone(customUserDetails.getPhone())
                .build());
    }
}

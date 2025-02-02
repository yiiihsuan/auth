package com.yiiihsuan.auth.controller;

import com.yiiihsuan.auth.controller.request.MemberLoginRequest;
import com.yiiihsuan.auth.controller.response.TokenResponse;
import com.yiiihsuan.auth.service.MemberLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

}

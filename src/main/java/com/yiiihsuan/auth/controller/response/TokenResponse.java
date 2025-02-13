package com.yiiihsuan.auth.controller.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn; // 單位(秒)
    private String tokenType; // Bearer
}

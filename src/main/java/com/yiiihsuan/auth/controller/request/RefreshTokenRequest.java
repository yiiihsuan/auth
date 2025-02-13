package com.yiiihsuan.auth.controller.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
package com.yiiihsuan.auth.controller.request;

import lombok.Data;

@Data
public class MemberLoginRequest {
    private String email;
    private String password;
}

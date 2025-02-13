package com.yiiihsuan.auth.controller.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MeResponse {
    private String name;
    private String email;
    private String phone;
}

package com.yiiihsuan.auth.worker.rest.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

    private LocalDateTime createdTs;

    private LocalDateTime updatedTs;
}


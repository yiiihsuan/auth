package com.yiiihsuan.auth.worker.rest.response;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MemberDto implements Serializable {

    // 皮皮幫我複製過來, MemberDO
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

    private LocalDateTime createdTs;

    private LocalDateTime updatedTs;
}


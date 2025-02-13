package com.yiiihsuan.auth.filter;


import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
public class CustomUserDetails implements UserDetails {
    private final Long memberId;
    private final String username;
    private final String password;
    private final String email;
    private final String phone;
    private final Collection<? extends GrantedAuthority> authorities; //泛型
}

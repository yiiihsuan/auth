package com.yiiihsuan.auth.service;

import com.yiiihsuan.auth.filter.CustomUserDetails;
import com.yiiihsuan.auth.worker.rest.MemberWorker;
import com.yiiihsuan.auth.worker.rest.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberWorker memberWorker;

    public CustomUserDetails loadUserById(Long id){
        MemberDto member = memberWorker.findById(id);

        return CustomUserDetails.builder()
                .memberId(member.getId())
                .username(member.getName())
                .password(member.getPassword())
                .email(member.getEmail())
                .phone(member.getPhone())
                .authorities(new ArrayList<>()) //TODO:user ROLES
                .build();

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

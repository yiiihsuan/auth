package com.yiiihsuan.auth.worker.rest;

import com.yiiihsuan.auth.worker.rest.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class MemberWorker {

    private String GET_MEMBER_BY_ID_URL = "http://localhost:8080/api/members/id/{id}";
    private String GET_MEMBER_BY_EMAIL_URL = "http://localhost:8080/api/members/email?email={email}";
    // http://localhost:8080/api/members/email?email=abc@gmail.com (query string)

    private final RestTemplate restTemplate;

    public MemberDto findById(Long id) {
        return restTemplate.getForObject(GET_MEMBER_BY_ID_URL, MemberDto.class, id);
    }

    public MemberDto findByEmail(String email) {
        return restTemplate.getForObject(GET_MEMBER_BY_EMAIL_URL, MemberDto.class, email);
    }

}

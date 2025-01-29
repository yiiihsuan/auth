package com.yiiihsuan.auth.worker.rest;

import com.yiiihsuan.auth.worker.rest.response.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Component
public class MemberWorker {

    private String GET_MEMBER_BY_ID_URL = "localhost:8080/api/members/id/{id}";

    private final RestTemplate restTemplate;

    public MemberDto findById(Long id) {
        return restTemplate.getForObject(GET_MEMBER_BY_ID_URL.replace(), MemberDto.class, id);
    }
}

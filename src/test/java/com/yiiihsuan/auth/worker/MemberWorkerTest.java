package com.yiiihsuan.auth.worker;

import com.yiiihsuan.auth.worker.rest.MemberWorker;
import com.yiiihsuan.auth.worker.rest.response.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberWorkerTest {

    @Autowired
    private MemberWorker memberWorker;

    @Test
    public void testMemberWorker() {
        MemberDto member = memberWorker.findById(1L);
        System.out.println(member);
        int x = 0;
    }

    @Test
    public void testEmailMemberWorker() {
        MemberDto member = memberWorker.findByEmail("alice@example.com");

        int x = 0;
    }
}

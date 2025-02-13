package com.yiiihsuan.auth.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RedisServiceTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testRedisSetAndGet() {
        // 設定測試的 Key-Value
        String key = "testKey";
        String value = "Hello Redis";

        // 存入 Redis
        redisTemplate.opsForValue().set(key, value);

        // 從 Redis 取出
        String retrievedValue = (String) redisTemplate.opsForValue().get(key);

        // 斷言驗證
        assertThat(retrievedValue).isEqualTo(value);

        // 清理 Redis 內測試的 Key
        redisTemplate.delete(key);
    }
}


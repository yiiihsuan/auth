package com.yiiihsuan.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    // 儲存 Key-Value（永久保存）
    public void saveData(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 儲存 Key-Value 並設置過期時間（秒）
    public void saveDataWithTTL(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    // 讀取 Key-Value
    public String getData(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    // 刪除 Key
    public void deleteData(String key) {
        redisTemplate.delete(key);
    }

    // 檢查 Key 是否存在
    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}


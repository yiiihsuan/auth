package com.yiiihsuan.auth.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestPropertySource(properties = "jwt.secret=mysecretmysecretmysecretmysecret")
public class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    public void testGenerateAccessToken() {
        String token = jwtUtils.generateAccessToken(1L);
        assertNotNull(token);
    }

    @Test
    public void testGenerateRefreshToken() {
        String token = jwtUtils.generateRefreshToken(1L);
        assertNotNull(token);
    }

    @Test
    public void testValidateAccessToken() {
        String token = jwtUtils.generateAccessToken(1L);
        Claims claims = jwtUtils.validateToken(token);
        assertEquals("1", claims.getSubject());
    }

    @Test
    public void testValidateRefreshToken() {
        String token = jwtUtils.generateRefreshToken(1L);
        Claims claims = jwtUtils.validateToken(token);
        assertEquals("1", claims.getSubject());
    }
}

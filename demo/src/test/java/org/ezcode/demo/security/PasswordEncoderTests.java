package org.ezcode.demo.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * PasswordEncoderTests
 */
@SpringBootTest
@Slf4j
public class PasswordEncoderTests {

    @Setter(onMethod_ = {@Autowired})
    private BCryptPasswordEncoder pwEncoder;

    @Test
    public void testEncode() {
        String str = "member";
        String enStr = pwEncoder.encode(str);

        // $2a$10$okrJIOZNAbyX9Ocs8ox58.zJW3SWvMf9m6o9PxCmIIEtIUUv12l1y
        log.info(enStr);
    }

    
}
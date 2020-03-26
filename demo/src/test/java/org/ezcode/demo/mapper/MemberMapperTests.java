package org.ezcode.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * MemberMapperTests
 */
@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testGetProfile() {
        // log.info("" + memberMapper.readProfile("goeu1113@gmail.com"));
    }
}
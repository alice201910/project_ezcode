package org.ezcode.demo.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * TimeMapperTests
 */
@SpringBootTest
@Slf4j
public class TimeMapperTests {

    @Autowired
    private TimeMapper timeMapper;

    @Test
    public void mapperTest() {
        log.info("mapper 테스트 - " + timeMapper.getTime2());
        
    }
    
}
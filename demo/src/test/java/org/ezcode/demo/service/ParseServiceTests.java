package org.ezcode.demo.service;

import org.ezcode.demo.domain.ParseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ParseServiceTests
 */
@SpringBootTest
@Slf4j
public class ParseServiceTests {

    @Setter(onMethod_ = @Autowired)
    private ParseService parseService;

    @Test
    public void insertTest() {
        log.info("insert test......................");

        ParseVO vo = new ParseVO();

        vo.setKeyword("별찍기");
        vo.setComment(1);
        vo.setCode("for(int i=0; i<5; i++) { log.info(22); }");
        vo.setLang("lang");
        vo.setPath("C:");
        vo.setStart(32);

        log.info("" + parseService.insertCode(vo));
    }

    @Test
    public void findByPnoTest() {
        log.info("" + parseService.findByPno(14));
    }
    
}
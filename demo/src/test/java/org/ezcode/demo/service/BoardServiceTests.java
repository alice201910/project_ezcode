package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.InquiryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardServiceTests
 */
@SpringBootTest
@Slf4j
public class BoardServiceTests {

    @Setter(onMethod_ = {@Autowired})
    private BoardService boardService;
    
    @Test
    public void inquiryListTest() {
        // List<InquiryVO> list = boardService.inquiryList("goeu1113@gmail.com");
        // list.forEach(inquiry -> log.info("" + inquiry));
    }
}
package org.ezcode.demo.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.ezcode.demo.domain.InquiryVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * BoardMapperTests
 */
@SpringBootTest
@Slf4j
public class BoardMapperTests {

    @Setter(onMethod_ = { @Autowired })
    private BoardMapper boardMapper;

    @Test
    public void inquiryListTest() {
        // List<InquiryVO> list = boardMapper.inquiryList("goeu1113@gmail.com");
        // list.forEach(inquiry -> log.info("" + inquiry));
    }

    @Test
    public void inquiryInsertTest() {
        InquiryVO vo = new InquiryVO();
        IntStream.range(0, 101).forEach(i -> {
            vo.setTitle("notice title" + i);
            vo.setContent("notice content" + i);
            vo.setWriter("admin");
            boardMapper.testNoticeInsert(vo);
        });
    }

}
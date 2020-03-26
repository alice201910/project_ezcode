package org.ezcode.demo.mapper;

import java.util.stream.IntStream;

import org.ezcode.demo.domain.ReviewVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ReviewMapperTests
 */
@SpringBootTest
@Slf4j
public class ReviewMapperTests {
    
    @Setter(onMethod_ = {@Autowired})
    private ReviewMapper reviewMapper;
    
    @Test
    public void MapperTest(){
        log.info(""+reviewMapper);
    }

    @Test
    public void ReviewInsert(){
        IntStream.range(1,101).forEach(i->{
            ReviewVO vo = new ReviewVO();
            vo.setPno(57);
            vo.setContent("오오오 감사합니다."+i);
            vo.setUid("yangAchi");
            vo.setScore(3.0);
           log.info("cnt : s"+reviewMapper.insert(vo));
        });
    }
    @Test
    public void ReviewDelete(){
        log.info(""+reviewMapper.delete(5));
    }

    @Test
    public void cntTest(){
          //og.info(""+reviewMapper.reviewCnt(43));
    }

    @Test
    public void getReviewBySeller() {
        // log.info("" + reviewMapper.findReviewBySeller("goeu1113@gmail.com"));
    }
}
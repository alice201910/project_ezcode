package org.ezcode.demo.mapper;

import java.util.stream.IntStream;

import org.ezcode.demo.domain.LikeVO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductPagingDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * LikeMapper
 */
@SpringBootTest
@Slf4j
public class LikeMapperTests {

    @Autowired
    private LikeMapper likeMapper;
    // @Test
    // public void likeList(){
    //     String uid = "rosealice201909@gmail.com";
    //     log.info("dddd :"+likeMapper.likeList(uid));
    // }

    @Test
    public void likeCntTest(){
        // log.info("cnt : "+ likeMapper.likeCnt());
    }

    @Test
    public void likeInsert(){
        IntStream.range(1,101).forEach(i->{
            LikeVO vo = new LikeVO();
            vo.setUid("rosealice201909@gmail.com");
            vo.setPno(66);
            log.info(""+likeMapper.ChooseInsert(vo));
        });
    }

    @Test
    public void likeListTest(){
        ProductPagingDTO dto = new ProductPagingDTO();
        dto.setAmount(5);
        dto.setUid("rosealice201909@gmail.com");
        log.info(""+ likeMapper.likeList(dto));
    } 
}
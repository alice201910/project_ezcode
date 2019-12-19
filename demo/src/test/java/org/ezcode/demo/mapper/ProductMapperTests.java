package org.ezcode.demo.mapper;

import java.util.stream.IntStream;

import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductMapperTests
 */
@SpringBootTest
@Slf4j
public class ProductMapperTests {

     @Setter(onMethod_ = {@Autowired})
     private ProductMapper productMapper;

    // @Autowired
    // private TimeMapper timeMapper;

    // @Test
    // public void mapperTest() {
    //     log.info("mapper 테스트 - " + timeMapper.getTime2());
        
    // }

    @Test
    public void mapperInsert(){
        IntStream.range(1, 20).forEach(i->{
            ProductVO vo = new ProductVO();
            vo.setCtno(2);
            vo.setPname("게시판 쿼리 모음집"+i);
            vo.setPrice("99000");
            vo.setExplanation("게시판을 위해 사용하는 쿼리문 총망라 "+i);
            vo.setSummary("게시판 쿼리문 모음");
            vo.setSeller("Sqlsin");
            vo.setState("판매중");
            productMapper.insert(vo);
        });
    }

    @Test
    public void selectAllTest() {
        PagingDTO dto = new PagingDTO(1, 10);
        productMapper.selectAll(dto).forEach(vo -> 
            log.info("" + vo)
        );
    }

    @Test
    public void orderTest() {
        PagingDTO dto = new PagingDTO();
        dto.setOrderType("hit");
        // dto.setOrderOpt("desc");
        log.info("" + dto);
        productMapper.selectAll(dto).forEach(vo -> {
            log.info("\n" + vo);
        });
    }

    @Test
    public void searchOptionTest() {
        PagingDTO dto = new PagingDTO();
        dto.setOrderType("pno");
        dto.setCategory("2");
        dto.setStartPrice("20000");
        dto.setEndPrice("60000");
        dto.setStartDate("2019-12-18");
        dto.setEndDate("2019-12-18");

        log.info("" + dto);
        productMapper.selectAll(dto).forEach(vo -> {
            log.info("\n" + vo);
        });
    }

    @Test
    public void searchKeywordTest() {
        PagingDTO dto = new PagingDTO();
        dto.setKeyword("sql");
        dto.setType("S");

        log.info("" + dto);
        productMapper.selectAll(dto).forEach(vo -> {
            log.info("\n" + vo);
        });
    }

}
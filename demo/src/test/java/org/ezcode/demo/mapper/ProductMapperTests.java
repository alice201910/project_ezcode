package org.ezcode.demo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.ezcode.demo.domain.AttachDTO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductMapperTests
 */
@SpringBootTest
@Slf4j
public class ProductMapperTests {

    private static final int ArrayList = 0;
    @Setter(onMethod_ = { @Autowired })
    private ProductMapper productMapper;

    // @Autowired
    // private TimeMapper timeMapper;

    // @Test
    // public void mapperTest() {
    // log.info("mapper 테스트 - " + timeMapper.getTime2());

    // }

    @Test
    public void mapperInsert() {
        IntStream.range(1, 2).forEach(i -> {
            ProductVO vo = new ProductVO();
            // vo.setCtno(1);
            // vo.setPname("자바 웹 크롤링코드"+i);
            // vo.setPrice("99000");
            // vo.setExplanation("자바 웹 크롤링코드 "+i);
            // vo.setSummary("자바 크롤링 코드");
            // vo.setSeller("Javasin");
            // vo.setState("판매중");
            // productMapper.insert(vo);
            // vo.setCtno(2);
            // vo.setPname("게시판 쿼리 모음집"+i);
            // vo.setPrice("99000");
            // vo.setExplanation("게시판을 위해 사용하는 쿼리문 총망라 "+i);
            // vo.setSummary("게시판 쿼리문 모음");
            // vo.setSeller("Sqlsin");
            // vo.setState("판매중");
            // productMapper.insert(vo);
            vo.setCtno(3);
            vo.setPname("JavaScript 코드" + i);
            vo.setPrice("99000");
            vo.setExplanation("JavaScript 코드 " + i);
            vo.setSummary("JavaScript 코드");
            vo.setSeller("JSsin");
            vo.setState("판매중");
            vo.setDevlang("자바스크립트");
            vo.setTool("자바스크립트");
            productMapper.insert(vo);
        });
    }

    @Test
    public void selectAllTest() {
        PagingDTO dto = new PagingDTO(1, 10);
        // productMapper.selectAll(dto).forEach(vo ->
        // log.info("" + vo)
        // );
    }

    @Test
    public void orderTest() {
        PagingDTO dto = new PagingDTO();
        // dto.setOrderType("hit");
        // // dto.setOrderOpt("desc");
        // log.info("" + dto);
        // productMapper.selectAll(dto).forEach(vo -> {
        // log.info("\n" + vo);
        // });
    }

    @Test
    public void searchOptionTest() {
        PagingDTO dto = new PagingDTO();
        // dto.setOrderType("pno");
        // dto.setCategory("2");
        // dto.setStartPrice("20000");
        // dto.setEndPrice("60000");
        // dto.setStartDate("2019-12-18");
        // dto.setEndDate("2019-12-18");

        // log.info("" + dto);
        // productMapper.selectAll(dto).forEach(vo -> {
        // log.info("\n" + vo);
        // });
    }

    @Test
    public void searchKeywordTest() {
        PagingDTO dto = new PagingDTO();
        // dto.setKeyword("sql");
        // dto.setType("S");

        // log.info("" + dto);
        // productMapper.selectAll(dto).forEach(vo -> {
        // log.info("\n" + vo);
        // });
    }

    @Test
    public void updateTest() {
        ProductVO vo = new ProductVO();
        vo.setPno(218);
        vo.setCtno(3);
        vo.setPrice("1111");
        vo.setPname("게시판 프로젝트");
        vo.setSummary("게시판 만들어드립ㄴ다");
        vo.setExplanation("게시판 만들어드립니다 게시판 만들어드립ㄴ다게시판 만들어드립ㄴ다");

        productMapper.update(vo);
    }

    @Test
    public void readTest() {
        log.info("" + productMapper.findByPno(220));
    }

    @Test
    public void oneFileUpdateTest() {

        AttachDTO dto = new AttachDTO();
        dto.setFiletype(true);
        dto.setFname("image.png");
        dto.setPno(217);
        dto.setUploadpath("uploadpath");
        dto.setUuid("uuidaaa");

        productMapper.fileUpdate(dto);
    
    }

    @Test
    @Transactional
    public void fileUpdateTest() {
        ProductVO vo = new ProductVO();
        vo.setPno(217);
        vo.setCtno(3);
        vo.setPrice("2000");
        vo.setPname("게시판 프로젝트");
        vo.setSummary("게시판 만들어드립니다");
        vo.setSeller("aaa");
        vo.setExplanation("게시판 만들어드립니다.....");
        vo.setState("판매중");
        
        List<AttachDTO> alist = new ArrayList<>();

        IntStream.range(0, 4).forEach(i -> {
            
        });

        AttachDTO dto = new AttachDTO();
        dto.setFiletype(true);
        dto.setFname("image.png");
        dto.setUploadpath("uploadpath");
        dto.setUuid("uuidsdas");
        alist.add(dto);

        vo.setAttachList(alist);

        productMapper.update(vo);
		log.info("update : " + vo);

		if (vo.getAttachList() != null) {
			vo.getAttachList().forEach(attach -> {
				attach.setPno(vo.getPno());
				productMapper.fileUpdate(attach);
			});
		}
    }

    @Test
    public void deleteTest() {
        log.info("" + productMapper.delete(222));
    }
    




 }
 
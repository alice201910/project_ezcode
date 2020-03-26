package org.ezcode.demo.service;

import org.ezcode.demo.domain.PagingDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductServiceTests
 */
@SpringBootTest
@Slf4j
public class ProductServiceTests {

    @Setter(onMethod_ = {@Autowired})
    private ProductService productService;

    @Test
    public void listTest () {
        PagingDTO dto = new PagingDTO(1, 10);
        // productService.list(dto).forEach(vo -> {
        //     log.info("" + vo);
        // });
    }

    @Test
    public void selectAllTest() {
        PagingDTO dto = new PagingDTO(1, 10);
        // productService.list(dto).forEach(vo -> 
        //     log.info("" + vo)
        // );
    }

    @Test
    public void orderTest() {
        PagingDTO dto = new PagingDTO();
        // dto.setOrderType("hit");
        // // dto.setOrderOpt("desc");
        // log.info("" + dto);
        // productService.list(dto).forEach(vo -> {
        //     log.info("\n" + vo);
        // });
    }

    @Test
    public void searchOptionTest() {
        PagingDTO dto = new PagingDTO();
        // dto.setOrderType("pno");
        // dto.setCategory(2);
        // dto.setStartPrice("20000");
        // dto.setEndPrice("60000");
        // dto.setStartDate("2019-12-18");
        // dto.setEndDate("2019-12-18");

        log.info("" + dto);
        // productService.list(dto).forEach(vo -> {
        //     log.info("\n" + vo);
        // });
    }

    @Test
    public void searchKeywordTest() {
        PagingDTO dto = new PagingDTO();
        // dto.setKeyword("sql");
        // dto.setType("S");

        // log.info("" + dto);
        // productService.list(dto).forEach(vo -> {
        //     log.info("\n" + vo);
        // });

    }
    @Test
    public void deleteTest() {
        log.info("" + productService.delete(221));
    }


}
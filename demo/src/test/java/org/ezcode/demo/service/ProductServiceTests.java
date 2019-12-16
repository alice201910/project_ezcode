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
        productService.list(dto).forEach(vo -> {
            log.info("" + vo);
        });
    }
}
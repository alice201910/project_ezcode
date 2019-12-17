package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.ProductVO;
import org.ezcode.demo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductServiceImpl
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    @Setter(onMethod_ = @Autowired)
    private ProductMapper productMapper;

    @Override
    public int register(ProductVO vo) {
        log.info("vo : "+vo);
        return productMapper.insert(vo);
    }

    @Override
    public int modify(ProductVO vo) {
        return 0;
    }

    @Override
    public int delete(ProductVO vo) {
        return 0;
    }

    @Override
    public List<ProductVO> list(ProductVO vo) {
        return null;
    }

    @Override
    public int findByPno(ProductVO vo) {
        return 0;
    }

 
} 
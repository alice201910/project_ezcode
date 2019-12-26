package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductVO;
import org.ezcode.demo.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ProductServiceImpl
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Setter(onMethod_ = {@Autowired})
    private ProductMapper productMapper;

    @Transactional
    @Override
    public void register(ProductVO vo) {
        productMapper.insert(vo);
        log.info("vo : " + vo);
        if(vo.getAttachList()!=null){
            vo.getAttachList().forEach(dto->{
            productMapper.fileInsert(dto);
            });
        }
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
    public List<ProductVO> list(PagingDTO dto) {
        log.info("" + dto);
        return productMapper.selectAll(dto);
    }

    @Override
    public int fileDelete(String uuid) {
        return productMapper.fileDelete(uuid);
    }
    @Override
    public int getCount(PagingDTO dto) {
        return productMapper.getCount(dto);
    }

    @Override
    public ProductVO findByPno(Integer pno) {
        log.info("parameter in service : "+pno);
        return productMapper.findByPno(pno);
    }

    @Override
    public Double ratingGrade(Integer pno) {
        log.info("ratingGrade pno:"+pno);
        return productMapper.ratingGrade(pno);
    }

    @Override
    public Integer cntReview(Integer pno) {
        log.info("cntReview pno : "+pno);
        return productMapper.cntReview(pno);
    }

 
} 
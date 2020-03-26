package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductPagingDTO;
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
        log.info("vo : " + vo);
        productMapper.insert(vo);
        if(vo.getAttachList()!=null){
            vo.getAttachList().forEach(dto->{
            productMapper.fileInsert(dto);
            });
        }
        
    }

    @Override
    @Transactional
    public void modify(ProductVO vo) {
        productMapper.update(vo);
		log.info("update : " + vo);

		if (vo.getAttachList() != null) {

			vo.getAttachList().forEach(attach -> {
				attach.setPno(vo.getPno());
				productMapper.fileUpdate(attach);
			});
		}
    }

    @Override
    public int delete(Integer pno) {
        return productMapper.delete(pno);
    }

    @Override
    public List<ProductVO> list(ProductPagingDTO dto) {
        log.info("" + dto);
        return productMapper.selectAll(dto);
    }

    @Override
    public int fileDelete(String uuid) {
        return productMapper.fileDelete(uuid);
    }
    @Override
    public int getCount(ProductPagingDTO dto) {
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

    @Override
    public List<ProductVO> getListBySeller(String seller, int skip) {
        return productMapper.getListBySeller(seller, skip);
    }

    @Override
    public Integer getCountBySeller(String seller) {
        return productMapper.getCountBySeller(seller);
    }

    @Override
    public List<String> searchAutoKeyword(ProductPagingDTO dto) {
        return productMapper.searchAutoKeyword(dto);
    }

    public ProductVO findById(PagingDTO dto) {
        log.info("findById pno : "+dto);
        return productMapper.findById(dto);
    }

    @Override
    public ProductVO findSelect(PagingDTO dto) {
        log.info("findSelect pno : "+dto);
        return productMapper.findSelect(dto);
    }
 
    @Override
    public List<ProductVO> findBySeller(String seller) {
        return productMapper.findBySeller(seller);
    }
} 
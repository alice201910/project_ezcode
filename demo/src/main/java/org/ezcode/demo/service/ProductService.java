package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductVO;

/**
 * ProductService
 */
public interface ProductService {

    // 상품 등록
    public int register(ProductVO vo);

    // 상품 수정
    public int modify(ProductVO vo);

    // 상품 삭제
    public int delete(ProductVO vo);
    
    // 상품 목록
    public List<ProductVO> list(PagingDTO dto);

    // 상품 상세
    public int findByPno(ProductVO vo);

    // 카운트 (페이징)
    public int getCount();
}
package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductVO;

/**
 * ProductService
 */
public interface ProductService {

    // 상품 등록
    public void register(ProductVO vo);

    // 상품 수정
    public void modify(ProductVO vo);

    // 상품 삭제
    public int delete(Integer pno);
    
    // 상품 목록
    public List<ProductVO> list(PagingDTO dto);

    // 상품 상세
    public ProductVO findByPno(Integer pno);

    // 상품 상세 (판매자 아이디로)
    public List<ProductVO> findBySeller(String seller);

    //파일 삭제
    public int fileDelete(String uuid);
    
    // 카운트 (페이징)
    public int getCount(PagingDTO dto);
}
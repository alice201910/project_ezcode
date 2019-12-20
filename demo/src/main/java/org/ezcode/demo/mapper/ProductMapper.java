package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.AttachDTO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductVO;

/**
 * ProductMapper
 */
public interface ProductMapper {

    // 상품 등록
    public int insert(ProductVO vo);

    // 상품 수정
    public int update(ProductVO vo);

    // 상품 삭제
    public int delete(Integer pno);
    
    // 상품 목록
    public List<ProductVO> selectAll(PagingDTO dto);

    // 상품 상세
    public ProductVO findByPno(Integer pno);

    //파일 등록
    public int fileInsert(AttachDTO dto);

    //파일 삭제
    public int fileDelete(String uuid);

    // 카운트 (페이징)
    public int getCount(PagingDTO dto);

    // 수정 - 파일 등록
    public int fileUpdate(AttachDTO dto);
}
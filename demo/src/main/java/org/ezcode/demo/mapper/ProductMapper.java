package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.AttachDTO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductPagingDTO;
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
    public List<ProductVO> selectAll(ProductPagingDTO dto);

    // 상품 상세
    public ProductVO findByPno(Integer pno);

    //파일 등록
    public int fileInsert(AttachDTO dto);

    //파일 삭제
    public int fileDelete(String uuid);

    // 카운트 (페이징)
    public int getCount(ProductPagingDTO dto);

    // 수정 - 파일 등록
    public int fileUpdate(AttachDTO dto);

    //별점 평균
    public Double ratingGrade(Integer pno);

    //리뷰 개수
    public Integer cntReview(Integer pno);

    public List<ProductVO> getListBySeller(String seller, int skip);

    public Integer getCountBySeller(String seller);
    
    public List<String> searchAutoKeyword(ProductPagingDTO dto);
    //거래내역
    public ProductVO findById(PagingDTO dto);

    //selected 조인
    public ProductVO findSelect(PagingDTO dto);

    // 상품 상세목록 (판매자 아이디로)
    public List<ProductVO> findBySeller(String seller);
}
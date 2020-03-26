package org.ezcode.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ReviewPageDTO;
import org.ezcode.demo.domain.ReviewVO;

/**
 * ReviewService
 */
public interface ReviewService {

     public int register(ReviewVO vo);

     public int remove(Integer rvno);

     public int modify(ReviewVO vo);

     public List<ReviewVO> getList(@Param("pno") Integer pno, @Param("dto") PagingDTO dto);

     public int reviewCnt(Integer pno);

     public ReviewPageDTO getListPage(PagingDTO dto, Integer pno);

     public ReviewPageDTO findReviewBySeller(String seller, int page);
}
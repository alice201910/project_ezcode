package org.ezcode.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ReviewPageDTO;
import org.ezcode.demo.domain.ReviewVO;

/**
 * ReviewMapper
 */
public interface ReviewMapper {

    public int insert(ReviewVO vo);

    public int delete(Integer rvno);

    public int update(ReviewVO vo);

    public List<ReviewVO> listWithPaging(@Param("pno") Integer pno, @Param("dto") PagingDTO dto);

    public int reviewCnt(Integer pno);

    public List<ReviewVO> findReviewBySeller(String seller, int page);

    public ReviewPageDTO getCountAndAvg(String seller);
}
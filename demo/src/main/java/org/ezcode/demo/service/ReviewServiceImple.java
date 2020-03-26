package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ReviewPageDTO;
import org.ezcode.demo.domain.ReviewVO;
import org.ezcode.demo.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ReviewServiceImple
 */
@Service
@Slf4j
public class ReviewServiceImple implements ReviewService {

    @Setter(onMethod_ = @Autowired)
    private ReviewMapper reviewMapper;

    @Override
    public int register(ReviewVO vo) {
        log.info("register : " + vo);
        return reviewMapper.insert(vo);
    }

    @Override
    public int remove(Integer rvno) {
        log.info("rvno : " + rvno);
        return reviewMapper.delete(rvno);
    }

    @Override
    public int modify(ReviewVO vo) {
        log.info("modify : " + vo);
        return reviewMapper.update(vo);
    }

    @Override
    public List<ReviewVO> getList(Integer pno, PagingDTO dto) {
        log.info("List<ReviewVO> pno:" + pno + "List<ReviewVO> dto : " + dto);
        return reviewMapper.listWithPaging(pno, dto);
    }

    @Override
    public int reviewCnt(Integer pno) {
        log.info("ReviewCnt : "+pno);
        return reviewMapper.reviewCnt(pno);
    }

    @Override
    public ReviewPageDTO getListPage(PagingDTO dto, Integer pno) {
        log.info("dto : "+dto +", "+"pno : "+pno);
        return new ReviewPageDTO(reviewMapper.reviewCnt(pno), reviewMapper.listWithPaging(pno, dto));
    }

    @Override
    public ReviewPageDTO findReviewBySeller(String seller, int page) {
        ReviewPageDTO dto = reviewMapper.getCountAndAvg(seller);
        dto.setList(reviewMapper.findReviewBySeller(seller, page));
        log.info("" + dto);
        return dto;
    }
    
    
}
package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.BoardPagingDTO;
import org.ezcode.demo.domain.InquiryVO;

/**
 * BoardMapper
 */
public interface BoardMapper {


    public int insertInquiry(InquiryVO vo);
    public List<InquiryVO> inquiryList(BoardPagingDTO dto);
    public InquiryVO inquiryOne(Integer groupno);
    public int getCount(BoardPagingDTO dto);
    
}
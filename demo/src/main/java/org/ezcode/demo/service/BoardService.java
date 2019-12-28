package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.BoardPagingDTO;
import org.ezcode.demo.domain.InquiryVO;

/**
 * BoardService
 */
public interface BoardService {

    public int registerInquiry(InquiryVO vo);
    public List<InquiryVO> inquiryList(BoardPagingDTO dto);
    public InquiryVO inquiryOne(Integer groupno);
    public int getCount(BoardPagingDTO dto);
}
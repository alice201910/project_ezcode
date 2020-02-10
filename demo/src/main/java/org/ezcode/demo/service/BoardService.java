package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.BoardPagingDTO;
import org.ezcode.demo.domain.InquiryVO;
import org.ezcode.demo.domain.NoticeVO;

/**
 * BoardService
 */
public interface BoardService {

    public void registerInquiry(InquiryVO vo);
    public List<InquiryVO> inquiryList(BoardPagingDTO dto);
    public InquiryVO inquiryOne(Integer groupno);
    public int getCount(BoardPagingDTO dto);

    public List<NoticeVO> noticeList(BoardPagingDTO dto);
    public int getNoticeCount(BoardPagingDTO dto);
    public NoticeVO noticeOne(Integer bno);
}

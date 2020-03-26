package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.BoardPagingDTO;
import org.ezcode.demo.domain.InquiryVO;
import org.ezcode.demo.domain.NoticeVO;
import org.ezcode.demo.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;

/**
 * BoardServiceImpl
 */
@Service
public class BoardServiceImpl implements BoardService {

    @Setter(onMethod_ = { @Autowired })
    private BoardMapper boardMapper;

    @Override
    @Transactional
    public void registerInquiry(InquiryVO vo) {
        boardMapper.insertInquiry(vo);
        boardMapper.updateInquiryGroupNo(vo);
    }

    @Override
    public List<InquiryVO> inquiryList(BoardPagingDTO dto) {
        return boardMapper.inquiryList(dto);
    }

    @Override
    public InquiryVO inquiryOne(Integer groupno) {
        return boardMapper.inquiryOne(groupno);
    }

    @Override
    public int getCount(BoardPagingDTO dto) {
        return boardMapper.getCount(dto);
    }

    @Override
    public List<NoticeVO> noticeList(BoardPagingDTO dto) {
        return boardMapper.noticeList(dto);
    }

    @Override
    public int getNoticeCount(BoardPagingDTO dto) {
        return boardMapper.getNoticeCount(dto);
    }

    @Override
    public NoticeVO noticeOne(Integer bno) {
        return boardMapper.noticeOne(bno);
    }
    
}
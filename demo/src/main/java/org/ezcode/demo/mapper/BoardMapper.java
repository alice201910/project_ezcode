package org.ezcode.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.ezcode.demo.domain.BoardPagingDTO;
import org.ezcode.demo.domain.InquiryVO;
import org.ezcode.demo.domain.NoticeVO;

/**
 * BoardMapper
 */
public interface BoardMapper {

    // 1:1 문의
    public int insertInquiry(InquiryVO vo);
    public List<InquiryVO> inquiryList(BoardPagingDTO dto);
    public InquiryVO inquiryOne(Integer groupno);
    public int getCount(BoardPagingDTO dto);
    public int updateInquiryGroupNo(InquiryVO vo);

    // 공지사항
    public List<NoticeVO> noticeList(BoardPagingDTO dto);
    public int getNoticeCount(BoardPagingDTO dto);
    public NoticeVO noticeOne(Integer bno);


    @Insert("insert into tbl_board (title, content, writer, category) values(#{title}, #{content}, #{writer}, 0)")
    public int testNoticeInsert(InquiryVO vo);
    
}
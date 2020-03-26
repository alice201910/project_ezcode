package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.ParseVO;
import org.ezcode.demo.domain.SearchDTO;

/**
 * ParseMapper
 */
public interface ParseMapper {

    // keyword 여러개 찾음
    public List<ParseVO> selectAll(SearchDTO dto);

    // keyword 한개만 찾음
    public List<ParseVO> select(SearchDTO dto);

    public int insertCode(ParseVO vo);
    
    public ParseVO selectByPno(Integer pno);

    public Integer getCount(SearchDTO dto);
    
}
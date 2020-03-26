package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.ParseVO;
import org.ezcode.demo.domain.SearchDTO;

/**
 * ParseService
 */
public interface ParseService {

    // keyword 여러개 찾음
    public List<ParseVO> findAll(SearchDTO dto);

    // keyword 한개만 찾음
    public List<ParseVO> find(SearchDTO dto);

    public boolean insertCode(ParseVO vo);

    public ParseVO findByPno(Integer pno);

    public Integer getCount(SearchDTO dto);    
}
package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.ParseVO;
import org.ezcode.demo.domain.SearchDTO;
import org.ezcode.demo.mapper.ParseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ParseServiceImpl
 */
@Service
@Slf4j
public class ParseServiceImpl implements ParseService {

    @Setter(onMethod_ = @Autowired)
    private ParseMapper parseMapper;

    @Override
    public List<ParseVO> findAll(SearchDTO dto) {

        log.info("select...........................");
		
		return parseMapper.selectAll(dto);
    }

    @Override
    public boolean insertCode(ParseVO vo) {

        log.info("insert Code........................");
		return parseMapper.insertCode(vo) == 1 ? true : false;
    }

    @Override
    public ParseVO findByPno(Integer pno) {

        log.info("find by pno..........................");

        return parseMapper.selectByPno(pno);
    }

    @Override
    public List<ParseVO> find(SearchDTO dto) {

        log.info("find one....................................");

        return parseMapper.select(dto);
    }

    @Override
    public Integer getCount(SearchDTO dto) {
        
        log.info("get count....................................");

        return parseMapper.getCount(dto);
    }

}
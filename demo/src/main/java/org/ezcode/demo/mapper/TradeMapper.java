package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.ProductVO;

/**
 * TradeMapper
 */
public interface TradeMapper {

    public List<ProductVO> tradelist(String uid);
    public int tradeCnt(String uid);
}
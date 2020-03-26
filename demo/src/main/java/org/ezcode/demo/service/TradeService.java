package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.ProductVO;

/**
 * TradeService
 */
public interface TradeService {

    public List<ProductVO> tradelist(String uid);

    public int tradeCnt(String uid);
    
}
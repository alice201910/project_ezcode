package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.ProductVO;
import org.ezcode.demo.mapper.TradeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * TradeServiceImple
 */
@Service
@Slf4j
public class TradeServiceImple implements TradeService {

    @Setter(onMethod_ = @Autowired)
    private TradeMapper tradeMapper;

    @Override
    public List<ProductVO> tradelist(String uid) {
        log.info("tradelist : "+uid);
        return tradeMapper.tradelist(uid);
    }

    @Override
    public int tradeCnt(String uid) {
        log.info("tradeCnt : "+uid);
        return tradeMapper.tradeCnt(uid);
    }

    
}
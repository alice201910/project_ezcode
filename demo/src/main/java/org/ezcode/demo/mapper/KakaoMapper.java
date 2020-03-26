package org.ezcode.demo.mapper;

import org.ezcode.demo.domain.AmountVO;
import org.ezcode.demo.domain.CardInfoVO;
import org.ezcode.demo.domain.KakaoPayApprovalVO;

public interface KakaoMapper {
    
    public int kakaoApprovalInfo(KakaoPayApprovalVO vo);
    public int amountInsert(AmountVO vo);
    public int cardInfoInsert(CardInfoVO vo);
}

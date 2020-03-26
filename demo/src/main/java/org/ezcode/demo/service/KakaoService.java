package org.ezcode.demo.service;

import org.ezcode.demo.domain.KakaoPayApprovalVO;
import org.ezcode.demo.domain.KakaoPayCancelVO;
import org.ezcode.demo.domain.PaymentDTO;

/**
 * KakaoService
 */

public interface KakaoService {
    
    public String kakaoPayStartUp(PaymentDTO pdto);

    public KakaoPayApprovalVO kakaoPaymemtInfo(String pg_token, PaymentDTO pdto);

    public KakaoPayCancelVO kakaoPaymentCancel(PaymentDTO pdto);
    
}
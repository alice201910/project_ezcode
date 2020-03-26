package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

/**
 * KakaoPayCancleVO
 */

 @Data
public class KakaoPayCancelVO extends KakaoPayApprovalVO{

    private String status;
    private AmountVO canceled_amount;
    private AmountVO cancel_available_amount;
    private Date canceled_at;

    
    
}
package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class KakaoPayApprovalVO {

    private String aid, tid, cid, sid, partner_order_id, partner_user_id, payment_method_type;
    private String item_name, item_code, payload;
    private Integer quantity, tax_free_amount, vat_amount;
    private Date created_at, approved_at;
    private CardInfoVO card_info;
    private AmountVO amount;
    private PaymentDTO pdto;
    
    // 취소했을 경우
    
}

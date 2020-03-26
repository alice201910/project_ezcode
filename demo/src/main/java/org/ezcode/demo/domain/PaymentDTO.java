package org.ezcode.demo.domain;

import lombok.Data;

/**
 * PaymentDTO
 */
@Data
public class PaymentDTO {

    private String pname; 
    private String seller;
    private String price;
    private String uid;
    private Integer pno;

    //취소
    private String cancel_amount;
    
}
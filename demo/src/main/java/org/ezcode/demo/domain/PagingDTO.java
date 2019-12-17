package org.ezcode.demo.domain;

import lombok.Data;

/**
 * PagingDTO
 */
@Data
public class PagingDTO {

    private int page;
    private int amount;
    private String orderType;
    private String orderOpt;


    public PagingDTO(int page, int amount){
        this.page = page;
        this.amount = amount;
    }

    public PagingDTO(){
        this.page = 1;
        this.amount = 12;
        // 정렬 기본값 - 상품번호, 내림차순
        // this.orderType = "pno";
        // this.orderOpt = "desc";
    }

    public int getSkip(){
        
        return (page-1)*amount;
    }


}
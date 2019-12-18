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
    private String category;
    private String startPrice;
    private String endPrice;
    private int grade;
    private String startDate;
    private String endDate;

    public PagingDTO(int page, int amount){
        this.page = page;
        this.amount = amount;
    }

    public PagingDTO(){
        this.page = 1;
        this.amount = 12;
        this.orderType = "pno";
        this.category = "";
        this.startPrice = "";
        this.endPrice = "";
        this.startDate = "";
        this.endDate = "";


        // 정렬 기본값 - 상품번호, 내림차순
        // this.orderType = "pno";
        // this.orderOpt = "desc";
        
    }

    public int getSkip(){
        return (page-1)*amount;
    }

    public String[] getCtarr() {
		if(category==null||category.trim().length()==0) {
			return null;
		}
		return category.split("");
	}


}
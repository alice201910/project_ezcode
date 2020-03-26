package org.ezcode.demo.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * PagingDTO
 */
@Getter
@Setter
public class ProductPagingDTO extends PagingDTO {

    private Integer pno;
    private String uid;
    private String partner_user_id;

    private String orderType; // 정렬 가격순.. 등등
    private String orderOpt; // 오름/내림
    private String category;
    private String startPrice; // 가격정렬
    private String endPrice;
    private int grade; // 등급
    private String startDate; // 날짜
    private String endDate;
    
    

    public ProductPagingDTO(int page, int amount) {
        super(page, amount);
    }

    public ProductPagingDTO() {
        // 정렬, 검색, 페이징 초기화
        this(1, 12);
        this.orderType = "pno";
        this.orderOpt = "desc";
        this.category = "";
        this.startPrice = "";
        this.endPrice = "";
        this.startDate = "";
        this.endDate = "";
        super.setType("");
        super.setKeyword("");
    }

    public String[] getCtarr() {
        if (category == null || category.trim().length() == 0) {
            return null;
        }
        return category.split("");
    }
    public int getAmounts(){
        return super.getAmount()+5;
    }

    @Override
    public String toString() {
        return super.toString() + ", orderType = " + orderType + ", orderOpt = " + orderOpt + ", category = " + category
                + ", startPrice = " + startPrice + ", endPrice = " + endPrice + ", startDate = " + startDate
                + ", endDate = " + endDate + ", uid = " + uid + ", partner_user_id = " + partner_user_id;
    }

    
}
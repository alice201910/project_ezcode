package org.ezcode.demo.domain;

import lombok.Data;

/**
 * PagingDTO
 */
@Data
public class PagingDTO {

    private int page;
    private int amount;
    private String keyword;
    private String type;

    public PagingDTO(int page, int amount){
        this.page = page;
        this.amount = amount;
    }

    public PagingDTO(){
        this.page = 1;
        this.amount = 12;  
    }

    public int getSkip() {
        return (this.page - 1) * this.amount;
    }

    public String[] getTypes() {
        if (type == null || type.trim().length() == 0) {
            return null;
        }
        return type.split("");
    }
}
package org.ezcode.demo.domain;

import lombok.Data;

/**
 * PagingDTO
 */
@Data
public class PagingDTO {

    private int page;
    private int amount;


    public PagingDTO(int page, int amount){
        this.page = page;
        this.amount = amount;
    }


    public int getSkip(){
        
        return (page-1)*amount;
    }


}
package org.ezcode.demo.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * BoardPagingDTO
 */
@Getter
@Setter
public class BoardPagingDTO extends PagingDTO {

    private Integer bno;
    private String writer;
    
    private String content;

    private String title;

    private String startDate;
    private String endDate;

    public BoardPagingDTO() {
        this(1, 7);
        super.setKeyword("");
        super.setType("");
    }

    public BoardPagingDTO(int page, int amount) {
        super(page, amount);
    }

    public BoardPagingDTO(String writer) {
        this.writer = writer;
    }

    

    @Override
    public String toString() {
        return super.toString() 
        + "writer = " + writer + ", startDate = " + startDate
        + ", endDate = " + endDate;
    }

    
}
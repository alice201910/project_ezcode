package org.ezcode.demo.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * BoardPagingDTO
 */
@Getter
@Setter
public class BoardPagingDTO extends PagingDTO{

    private String writer;

    public BoardPagingDTO() {
        this(1, 7);
    }

    public BoardPagingDTO(int page, int amount) {
        super(page, amount);
    }

    public BoardPagingDTO(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return super.toString() + "writer" + writer;
    }

    
}
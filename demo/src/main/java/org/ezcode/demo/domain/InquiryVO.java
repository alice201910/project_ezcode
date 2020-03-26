package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

@Data
public class InquiryVO {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int hit;
    private Date regdate;
    private int depth;
    private int category;
    private Integer groupno;
    private int cnt;
    
}

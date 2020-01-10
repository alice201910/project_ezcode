package org.ezcode.demo.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class NoticeVO {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private int hit;
    private Date regdate;
    private int category;
    private int cnt;

    private List<AttachDTO> attachList;
    
}
package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

/**
 * LikeVO
 */
@Data
public class LikeVO {

    private Integer lno;
    private String uid;
    private Integer pno;
    private boolean selected;
    private Date regdate;
    private ProductVO pvo;
}
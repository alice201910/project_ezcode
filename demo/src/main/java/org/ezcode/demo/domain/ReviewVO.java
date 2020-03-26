package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

/**
 * ReviewVO
 */
@Data
public class ReviewVO {

    private Integer rvno;
	private String uid;
	private Integer pno;
	private String content;
	private Double score;
	private Date regdate;

	private Double ravg; // 평균
	private int rcnt; // 수

	private ProductVO product;
}
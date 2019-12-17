package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

/**
 * ProductVO
 */
@Data
public class ProductVO {

    private Integer pno;
	private Integer ctno;
	private String seller;
	private String pname;
	private String price;
	private String summary;
	private String explanation;
	private int hit;
	private Date regdate;
	private String state;

	private ReviewVO review;
}
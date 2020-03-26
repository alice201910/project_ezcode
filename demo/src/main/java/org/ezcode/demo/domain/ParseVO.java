package org.ezcode.demo.domain;

import lombok.Data;

/**
 * ParseVO
 */
@Data
public class ParseVO {

    private Integer pno;
	private String keyword;
	private String code;
	private String lang;
	private String siteLink;
	private String path;
	private String fname;
	
	private Integer comment;
	private Integer start;

}
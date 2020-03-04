package org.ezcode.demo.domain;

import lombok.Data;

/**
 * AttachVO
 */
@Data
public class AttachDTO {

	private Integer pno;
	private String fname;
	private boolean filetype;
	private String uuid;
	private String uploadpath;
	private Integer bno;
	private String splitDates;
	
}
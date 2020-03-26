package org.ezcode.demo.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * MemberVO
 */
@Data
public class MemberVO {

	
    private String userid;
	private String userpw;
	@NotNull
	private String username;
	private boolean enabled;
	private Date regDate;
	private Date updateDate;
	private String email;
	private String tel;
	private String mlang;
	private String grade;
	
	private List<AuthVO> authList;
}
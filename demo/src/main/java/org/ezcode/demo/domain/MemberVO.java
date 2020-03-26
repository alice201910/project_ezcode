package org.ezcode.demo.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * MemberVO
 */
@Data
public class MemberVO {

	@NotNull
	@Size(min = 5, max = 20, message = "5~20글자로 입력해주세요.")
	// @Pattern(regexp = "/^[A-Za-z0-9_\\-]{5,12}$/", message = "영문과 숫자, _ ,  - 만 입력해주세요.")
	private String userid;
	
	@NotNull
	@Size(min = 6, message = "6글자 이상 입력해주세요.")
	// @Pattern(regexp = "/^[0-9a-z]+$/", message = "영문과 숫자만 입력해주세요.")
	private String userpw;

	@NotNull
	private String username;
	
	@NotNull
	@Email(message = "@를 포함해주세요.")
	private String email;
	
	@NotNull
	// @Pattern(regexp = "/^\\d{3}-\\d{3,4}-\\d{4}$/", message = "ex) 010-0000-0000 와 같이 입력해주세요.")
	private String tel;
	
	@NotNull
	private String mlang;
	
	private boolean enabled;
	private Date regDate;
	private Date updateDate;
	
	private List<AuthVO> authList;
}
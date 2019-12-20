package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

/**
 * MemberVO
 */
@Data
public class MemberVO {

    private String uid;
    private Integer gno;
    private String password;
    private String name;
    private String birth;
    private String email;
    private String tel;
    private Date joindate;
    private int point;
    private String mlang;
    
}
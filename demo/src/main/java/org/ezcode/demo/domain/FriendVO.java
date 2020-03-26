package org.ezcode.demo.domain;

import java.util.Date;

import lombok.Data;

/**
 * FriendVO
 */
@Data
public class FriendVO {

    private Integer mateno;
    private String sender;
    private String receiver;
    private Integer friendcheck;
    private Date regDate;
    
}
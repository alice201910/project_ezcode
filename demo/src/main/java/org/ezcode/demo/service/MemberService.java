package org.ezcode.demo.service;

import org.ezcode.demo.domain.MemberVO;

/**
 * MemberService
 */
public interface MemberService {

    public void join(MemberVO vo);
    public MemberVO read(String userid);
    public int modify(MemberVO vo);
    public int changePass(MemberVO vo);
    public int ckeckID(String uerid);

}
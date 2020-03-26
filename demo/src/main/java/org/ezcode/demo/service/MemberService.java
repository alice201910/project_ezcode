package org.ezcode.demo.service;

import org.ezcode.demo.domain.MemberVO;

/**
 * MemberService
 */
public interface MemberService {

    public void join(MemberVO vo);
    public MemberVO read(String userid);
    public MemberVO readProfile(String userid);

}
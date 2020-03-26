package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.MemberVO;

/**
 * MemberService
 */
public interface MemberService {

    public boolean join(MemberVO vo);

    public MemberVO read(String userid);

    public MemberVO findById(String userid);

    public List<MemberVO> findListById(String userid);

    public List<MemberVO> findAllMember();

    public boolean checkByIdAndPw(String userid, String userpw);

    public boolean ModifyMember(MemberVO vo);

    public boolean ModifyPw(MemberVO vo);

    public boolean quitMember(String userid);
    
    public MemberVO readProfile(String userid);

}
package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.AuthVO;
import org.ezcode.demo.domain.MemberVO;

/**
 * MemberMapper
 */
public interface MemberMapper {

    public int insertMember(MemberVO vo);

    public int insertAuth(AuthVO vo);

    public MemberVO read(String userid);

    public MemberVO selectOneMemberById(String userid);

    public List<MemberVO> selectMemberListById(String userid);

    public List<MemberVO> selectAllMember();

    public int updateMember(MemberVO vo);

    public int updatePw(MemberVO vo);

    public int deleteMember(String userid);

    public int deleteMemberAuth(String userid);

    public MemberVO readProfile(String userid);
}
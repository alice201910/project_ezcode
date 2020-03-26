package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.AuthVO;
import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * MemberServiceImpl
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    @Override
    public boolean join(MemberVO vo) {

        log.info("join service ------------------");

        AuthVO authVO = new AuthVO();

        authVO.setUserid(vo.getUserid());
        authVO.setAuth("ROLE_MEMBER");

        vo.setUserpw(encoder.encode(vo.getUserpw()));

        int im = memberMapper.insertMember(vo);
        int ia = memberMapper.insertAuth(authVO);

        return (im + ia) == 2 ? true : false;
    }

    @Override
    public MemberVO read(String userid) {

        return memberMapper.read(userid);
    }

    @Override
    public MemberVO findById(String userid) {

        return memberMapper.selectOneMemberById(userid);
    }

    @Override
    public boolean checkByIdAndPw(String userid, String userpw) {

        MemberVO getMem = memberMapper.selectOneMemberById(userid);

        log.info("get member by id........." + getMem);

        return encoder.matches(userpw, getMem.getUserpw());
    }

    @Override
    public boolean ModifyMember(MemberVO vo) {

        return memberMapper.updateMember(vo) == 1 ? true : false;
    }

    @Override
    public boolean ModifyPw(MemberVO vo) {

        log.info("modify password......................");
        log.info("" + vo);

        vo.setUserpw(encoder.encode(vo.getUserpw()));

        return memberMapper.updatePw(vo) == 1 ? true : false;
    }

    @Transactional
    @Override
    public boolean quitMember(String userid) {

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        log.info(userid);

        int dma = memberMapper.deleteMemberAuth(userid);
        log.info("" + dma);
        int dm = memberMapper.deleteMember(userid);
        log.info("" + dm);

        return (dma + dm) == 2 ? true : false;
    }

    @Override
    public List<MemberVO> findAllMember() {

        return memberMapper.selectAllMember();
    }

    @Override
    public List<MemberVO> findListById(String userid) {

        return memberMapper.selectMemberListById(userid);
    }

    @Override
    public MemberVO readProfile(String userid) {
        return memberMapper.readProfile(userid);
    }
}
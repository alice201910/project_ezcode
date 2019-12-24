package org.ezcode.demo.service;

import org.ezcode.demo.domain.AuthVO;
import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * MemberServiceImpl
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    @Setter(onMethod_ = { @Autowired })
    private MemberMapper memberMapper;
    @Autowired
    private PasswordEncoder encoder;

    @Transactional
    @Override
    public void join(MemberVO vo) {

        log.info("join service ------------------");
        
        AuthVO authVO = new AuthVO();
        authVO.setUserid(vo.getUserid());
        authVO.setAuth("ROLE_MEMBER");

        vo.setUserpw(encoder.encode(vo.getUserpw()));

        memberMapper.insertMember(vo);
        memberMapper.insertAuth(authVO);
    }

    @Override
    public MemberVO read(String userid) {
        return memberMapper.read(userid);
    }

    @Override
    public int modify(MemberVO vo) {
        return memberMapper.update(vo);
    }

    @Override
    public int changePass(MemberVO vo) {
        vo.setUserpw(encoder.encode(vo.getUserpw()));
        return memberMapper.updatePass(vo);
    }

    @Override
    public int ckeckID(String uerid) {
        
        return memberMapper.ckeckID(uerid);
    }
}
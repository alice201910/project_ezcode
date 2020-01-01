package org.ezcode.demo.service;

import org.ezcode.demo.domain.AuthVO;
import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder encoder;

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
    public MemberVO readProfile(String userid) {
        return memberMapper.readProfile(userid);
    }
}
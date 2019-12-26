package org.ezcode.demo.service;

import org.ezcode.demo.domain.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * MemberServiceTests
 */
@SpringBootTest
@Slf4j
public class MemberServiceTests {

    @Autowired
    private MemberService memberService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void testInertMember() {

        MemberVO vo = new MemberVO();
        vo.setUserid("gogo1");
        vo.setUserpw(encoder.encode("gogo1"));
        vo.setUsername("회원");
        vo.setEmail("email");
        vo.setTel("tel");
        vo.setMlang("java");
        // AuthVO authVO = new AuthVO();
        // authVO.setUserid("gogo");
        // auth.setAuth("ROLE_MEMBER");

        memberService.join(vo);
    }
}
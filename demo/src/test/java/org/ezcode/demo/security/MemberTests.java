package org.ezcode.demo.security;

import java.util.stream.IntStream;

import org.ezcode.demo.domain.AuthVO;
import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * MemberTests
 */
@SpringBootTest
@Slf4j
public class MemberTests {

    @Autowired
    private BCryptPasswordEncoder encoder;
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void testDummyMember() {

        IntStream.range(1, 101).forEach(i -> {
            MemberVO vo = new MemberVO();
            if (i < 90) {
                vo.setUserid("member" + i);
                vo.setUserpw(encoder.encode("member" + i));
                vo.setUsername("회원" + i);

            } else {
                vo.setUserid("admin" + i);
                vo.setUserpw(encoder.encode("admin" + i));
                vo.setUsername("관리자" + i);
            }
            memberMapper.insertMember(vo);
        });
    }

    @Test
    public void testInsertMemberRole() {

        IntStream.range(1, 101).forEach(i -> {
            AuthVO vo = new AuthVO();
            if (i < 90) {
                // vo.setUserid("member" + i);
                // vo.setAuth("ROLE_MEMBER");
            } else {
                vo.setUserid("admin" + i);
                vo.setAuth("ROLE_ADMIN");
                // vo.setAuth("ROLE_MEMBER");
                memberMapper.insertAuth(vo);
            }
            // memberMapper.insertAuth(vo);
        });
    }

    @Test
    public void TestRead() {

        MemberVO vo = memberMapper.read("admin95");

        log.info("" + vo);

        vo.getAuthList().forEach(authVO -> log.info("" + authVO));
    }

    @Test
    public void testInertMember() {

        MemberVO vo = new MemberVO();
        vo.setUserid("gogo");
        vo.setUserpw(encoder.encode("gogo"));
        vo.setUsername("회원");
        vo.setEmail("email");
        vo.setTel("tel");
        vo.setMlang("java");
        // AuthVO authVO = new AuthVO();
        // authVO.setUserid("gogo");
        // auth.setAuth("ROLE_MEMBER");

        memberMapper.insertMember(vo);
    }
}
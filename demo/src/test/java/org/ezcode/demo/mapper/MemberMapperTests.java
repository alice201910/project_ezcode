package org.ezcode.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.ezcode.demo.domain.FriendVO;
import org.ezcode.demo.domain.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * MemberMapperTests
 */
@Slf4j
@SpringBootTest
public class MemberMapperTests {

    @Autowired
    private MemberMapper mapper;

    @Autowired
    private FriendMapper friendMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void selectFriends() {

        String userid = "123123";

        List<FriendVO> voList = new ArrayList<>();

        // voList = friendMapper.selectRequestFriends(userid);

        voList.forEach(vo -> {
            log.info("" + vo);
        });
        
    }

    @Test
    public void updatepw() {

        MemberVO vo = mapper.selectOneMemberById("7");

        log.info("" + encoder.matches("7", vo.getUserpw()));

        vo.setUserpw("77");

        log.info("" + encoder.matches("77", vo.getUserpw()));

    }

    @Test
    public void insertMemberTest() {
        
        MemberVO vo = new MemberVO();

        vo.setUserid("manLong");
        vo.setUserpw("12345");
        vo.setUsername("김승원");
        vo.setEmail("dltdlt1995@naver.com");
        vo.setTel("010-9022-4101");
        vo.setMlang("java");

        log.info("" + mapper.insertMember(vo));

    }

    @Test
    public void selectOneMemTest() {

        // log.info("" + mapper.selectOneMember("dltdlt", "1234"));
    }

}
package org.ezcode.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.ezcode.demo.domain.FriendVO;
import org.ezcode.demo.domain.ParseVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

/**
 * FriendMapper
 */
@Slf4j
@SpringBootTest
public class FriendMapperTests {

    @Autowired
    private FriendMapper mapper;

    @Test
    public void insertFriendTest() {

        int r = mapper.insertFriend("909090", "789789");

        log.info("" + r);
    }

    @Test
    public void checkFriendTest() {

        log.info("" + mapper.checkFriend("789789", "123123"));
    }

    @Test
    public void gitFriendTest() {

        // List<ParseVO> result = new ArrayList<ParseVO>();

        // List<String> gitFriendList = mapper.selectGithubFriends("aaa123");
        // List<String> gitFriendList2 = mapper.selectGithubFriends("aaa123");

        // log.info("" + gitFriendList);
        // log.info("" + gitFriendList2);

        // gitFriendList.addAll(gitFriendList2);

        // log.info("" + gitFriendList);

        List<String> result = mapper.selectGithubFriends("aaa123");

        log.info("" + result);

        String str = result.toString();

        log.info(str);

        String[] strArr = str.split(",");


    }

}
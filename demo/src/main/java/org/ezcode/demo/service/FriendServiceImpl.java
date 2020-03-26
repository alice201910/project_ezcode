package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.FriendVO;
import org.ezcode.demo.mapper.FriendMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * MemberServiceImpl
 */
@Service
@Slf4j
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<FriendVO> findFriends(String userid) {

        log.info("find friends..........................");

        return friendMapper.selectFriends(userid);
    }

    @Override
    public List<FriendVO> findRequestFriends(String userid) {

        log.info("find request friends...........................");

        return friendMapper.selectRequestFriends(userid);
    }

    @Override
    public boolean deleteFriend(int mateno) {
        
        log.info("delete friend......................");
        log.info("" + mateno);

        return friendMapper.deleteFriend(mateno) == 1 ? true : false;
    }

    @Override
    public boolean ModifyFriend(int mateno) {

        log.info("modify friend......................");
        log.info("" + mateno);

        return friendMapper.updateFriend(mateno) == 1 ? true : false;
    }

    @Override
    public FriendVO checkFriend(String userid, String fid) {

        return friendMapper.checkFriend(userid, fid);
    }

    @Override
    public boolean makeFriend(String sender, String receiver) {
        
        return friendMapper.insertFriend(sender, receiver) == 1 ? true : false;
    }

    @Override
    public List<String> findGithubFriends(String userid) {

        return friendMapper.selectGithubFriends(userid);
    }

}
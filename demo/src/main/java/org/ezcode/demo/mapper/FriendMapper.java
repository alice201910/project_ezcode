package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.FriendVO;

/**
 * MemberMapper
 */
public interface FriendMapper {

    public int insertFriend(String sender, String receiver);

    public List<FriendVO> selectFriends(String userid);

    public List<FriendVO> selectRequestFriends(String userid);

    public int deleteFriend(int mateno);

    public int updateFriend(int mateno);

    public FriendVO checkFriend(String userid, String fid);

    public List<String> selectGithubFriends(String userid);
}
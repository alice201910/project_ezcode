package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.LikePagingDTO;
import org.ezcode.demo.domain.LikeVO;
import org.ezcode.demo.domain.PagingDTO;

/**
 * LikeService
 */
public interface LikeService {

    public int ChooseInsert(LikeVO vo);

    public int chooseDelete(LikeVO vo);

    public List<LikeVO> likeList(PagingDTO dto);

    public int likeDelete(Integer lno);

    public int likeCnt(String uid);

    public LikePagingDTO getLikeList(PagingDTO dto);
}
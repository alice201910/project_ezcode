package org.ezcode.demo.mapper;

import java.util.List;

import org.ezcode.demo.domain.LikeVO;
import org.ezcode.demo.domain.PagingDTO;

/**
 * LikeMapper
 */
public interface LikeMapper {

    public int ChooseInsert(LikeVO vo);

    public int chooseDelete(LikeVO vo);

    public List<LikeVO> likeList(PagingDTO dto);

    public int likeDelete(Integer lno);

    public int likeCnt(String uid);

}
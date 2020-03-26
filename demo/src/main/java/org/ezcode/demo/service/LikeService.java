package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.LikePagingDTO;
import org.ezcode.demo.domain.LikeVO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductPagingDTO;

/**
 * LikeService
 */
public interface LikeService {

    public int ChooseInsert(LikeVO vo);

    public int chooseDelete(LikeVO vo);

    public List<LikeVO> likeList(ProductPagingDTO dto);

    public int likeDelete(Integer lno);

    public int likeCnt(String uid);

    public LikePagingDTO getLikeList(ProductPagingDTO dto);
}
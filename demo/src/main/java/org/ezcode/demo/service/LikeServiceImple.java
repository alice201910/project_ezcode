package org.ezcode.demo.service;

import java.util.List;

import org.ezcode.demo.domain.LikePagingDTO;
import org.ezcode.demo.domain.LikeVO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductPagingDTO;
import org.ezcode.demo.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * LikeServiceImple
 */
@Service
@Slf4j
public class LikeServiceImple implements LikeService {

    @Setter(onMethod_ = @Autowired)
    private LikeMapper likeMapper;

    @Override
    public int ChooseInsert(LikeVO vo) {
        log.info("ChooseInsert vo : " + vo);
        return likeMapper.ChooseInsert(vo);
    }

    @Override
    public int chooseDelete(LikeVO vo) {
        log.info("chooseDelete vo!!! : " + vo);
        return likeMapper.chooseDelete(vo);
    }

    @Override
    public List<LikeVO> likeList(ProductPagingDTO dto) {
        log.info("likeList uid : " + dto.getUid());
        return likeMapper.likeList(dto);
    }

    @Override
    public int likeDelete(Integer lno) {
        log.info("likeDelete : " + lno);
        return likeMapper.likeDelete(lno);
    }

    @Override
    public int likeCnt(String uid) {
        log.info("likeCnt");
        return likeMapper.likeCnt(uid);
    }

    @Override
    public LikePagingDTO getLikeList(ProductPagingDTO dto) {
        log.info("getLikeList dto : "+ dto);
        LikePagingDTO lpdto = new LikePagingDTO(likeCnt(dto.getUid()), likeList(dto));
        return lpdto;
    }

    
}
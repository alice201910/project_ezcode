package org.ezcode.demo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * LikePagingDTO
 */
@Data
@AllArgsConstructor
public class LikePagingDTO {

    private int likeCnt;
    private List<LikeVO> list;
}
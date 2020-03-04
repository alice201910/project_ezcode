package org.ezcode.demo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * ReviewPageDTO
 */
@Data
@AllArgsConstructor
@Getter
public class ReviewPageDTO {

    private int reviewCnt;
    private List<ReviewVO> list;

}
package org.ezcode.demo.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ReviewPageDTO
 */
@Data
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ReviewPageDTO {

    private int reviewCnt;
    private double avg;
    private List<ReviewVO> list;

    public ReviewPageDTO(int cnt, List<ReviewVO> list) {
        this.reviewCnt = cnt;
        this.list = list;
    }

}
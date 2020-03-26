package org.ezcode.demo.domain;

import lombok.Data;

/**
 * ProductVO
 */
@Data
public class KakaoPayVO {

    //response 시 오는 정보

    private String tid;
    // private String status;
    // private String resPname;
    private String next_redirect_pc_url;

}
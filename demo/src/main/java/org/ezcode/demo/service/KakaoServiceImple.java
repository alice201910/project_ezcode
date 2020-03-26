package org.ezcode.demo.service;

import java.net.URI;

import org.ezcode.demo.domain.KakaoPayApprovalVO;
import org.ezcode.demo.domain.KakaoPayCancelVO;
import org.ezcode.demo.domain.KakaoPayVO;
import org.ezcode.demo.domain.PaymentDTO;
import org.ezcode.demo.mapper.KakaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * KakaoServiceImple
 */
@Service
@Slf4j
public class KakaoServiceImple implements KakaoService {

    private static final String HOST = "https://kapi.kakao.com";

    // REST 서비스를 연동하기 위해 스프링에서 제공하는 객체
    RestTemplate restTemplate = new RestTemplate();
    private KakaoPayVO kakaoPayVO;
    private KakaoPayApprovalVO kakaoPayApprovalVO;
    private KakaoPayCancelVO KakaoPayCancelVO;

    @Setter(onMethod_ = @Autowired)
    private KakaoMapper kakaoMapper;

    @Override
    public String kakaoPayStartUp(PaymentDTO pdto) {
        log.info("kakaoPayStartUp.... : kakaoPayStartUp");
        // Header 정보 넣기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK" + " 239b58a3c1367a74b7379c6349b94ff3");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 요청 바디
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", pdto.getUid());
        params.add("item_name", pdto.getPname());
        params.add("quantity", "1");
        params.add("total_amount", pdto.getPrice());
        params.add("tax_free_amount", "10");
        params.add("approval_url", "http://localhost:8080/cshop/kakaoPaySuccess");
        params.add("fail_url", "http://localhost:8080/cshop/kakaoPayFail");
        params.add("cancel_url", "http://localhost:8080/cshop/kakaoPayCancel");

        // body, header순
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            // post 요청을 보내고 ResponseEntity로 반환받음
            // restTemplate.getForObject("요청할 URI 주소", "응답내용과 자동으로 매핑시킬 java object");

            kakaoPayVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayVO.class);
            log.info("KakaoPayVO : " + kakaoPayVO);
            log.info("getNext_redirect_pc_url : " + kakaoPayVO.getNext_redirect_pc_url());
            return kakaoPayVO.getNext_redirect_pc_url();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/cshop/kakaoPaySuccess";
    }


    @Override
    public KakaoPayApprovalVO kakaoPaymemtInfo(String pg_token, PaymentDTO pdto) {
        log.info("-----------------------------");
        log.info("KakaoPayInfoVO : KakaoPayInfoVO..........");

        // Header 정보 넣기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK" + " 239b58a3c1367a74b7379c6349b94ff3");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 서버로 요청할 Body
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayVO.getTid());
        params.add("partner_order_id", "1001");
        params.add("partner_user_id", pdto.getUid());
        params.add("pg_token", pg_token);
        params.add("total_amount", pdto.getPrice());

        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);

        try {
            kakaoPayApprovalVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
                    KakaoPayApprovalVO.class);
            log.info("kakaoPayApprovalVO : " + kakaoPayApprovalVO); 
            if(kakaoPayApprovalVO.getAid()!=null){
                log.info("pdto : "+pdto);
                kakaoPayApprovalVO.setPdto(pdto);
                kakaoMapper.kakaoApprovalInfo(kakaoPayApprovalVO);
            }
              if(kakaoPayApprovalVO.getCard_info()!=null){
                log.info("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                 kakaoMapper.cardInfoInsert(kakaoPayApprovalVO.getCard_info());
                 kakaoMapper.amountInsert(kakaoPayApprovalVO.getAmount());
              }
            return kakaoPayApprovalVO;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 취소
    @Override
    public KakaoPayCancelVO kakaoPaymentCancel(PaymentDTO pdto) {
        log.info("kakaoPaymentCancle.... : kakaoPaymentCancle");
        // Header 정보 넣기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "KakaoAK" + " 239b58a3c1367a74b7379c6349b94ff3");
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

        // 요청 바디
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("cid", "TC0ONETIME");
        params.add("tid", kakaoPayVO.getTid());
        params.add("cancel_amount", pdto.getCancel_amount()); 
        params.add("cancel_tax_free_amount", "10");

        // body, header순
        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, headers);

        try {
            KakaoPayCancelVO = restTemplate.postForObject(new URI(HOST + "/v1/payment/cancel"), body, KakaoPayCancelVO.class);
            return KakaoPayCancelVO;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
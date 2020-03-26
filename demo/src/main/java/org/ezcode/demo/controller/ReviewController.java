package org.ezcode.demo.controller;

import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ReviewPageDTO;
import org.ezcode.demo.domain.ReviewVO;
import org.ezcode.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * ReviewController
 */
@RestController
@Slf4j
@RequestMapping("/review")
public class ReviewController {

    @Setter(onMethod_ = @Autowired)
    private ReviewService reviewService;

    @GetMapping("/reviewList")
    public void reviewList(){
        
    }
    // 등록 
    @PreAuthorize("isAuthenticated()")
    @PostMapping(value="/register", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> create(@RequestBody ReviewVO vo){//json 데이터를 원하는 타입으로 바인딩

        log.info("Register ReviewVO : "+vo);

        int insertCnt = reviewService.register(vo);
        return insertCnt ==1? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    // 리스트 
    @GetMapping(value="/pages/{pno}/{page}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReviewPageDTO> getList(@PathVariable("pno") Integer pno, @PathVariable("page") int page){
        log.info("getList pno : "+pno+" getList page : "+page);

        PagingDTO dto = new PagingDTO(page, 10);

        log.info("dto : "+dto);
        return new ResponseEntity<>(reviewService.getListPage(dto, pno), HttpStatus.OK);
    }   

    // 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping(value="/{rvno}", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> remove(@PathVariable("rvno") Integer rvno) {
        log.info("rvno : "+rvno);
        
        int deleteCnt = reviewService.remove(rvno);

        log.info("delete : ");
        return  deleteCnt == 1? new ResponseEntity<>("success",HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    // 수정
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.PATCH}, value="/{rvno}"
    , consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<String> modify(@RequestBody ReviewVO vo, @PathVariable("rvno") Integer rvno){

        vo.setRvno(rvno);

        log.info("rvno : "+rvno);

        log.info("modify : "+vo);

        return reviewService.modify(vo) == 1? new ResponseEntity<>("success",HttpStatus.OK): new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping(value="/profile/{seller}/{skip}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ReviewPageDTO> getListBySeller(@PathVariable("seller") String seller, @PathVariable("skip") int page) {
        log.info("getList seller : "+seller+" getList page : " + page);

        return new ResponseEntity<>(reviewService.findReviewBySeller(seller, page), HttpStatus.OK);
    }

    
}
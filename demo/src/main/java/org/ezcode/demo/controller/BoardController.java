package org.ezcode.demo.controller;

import org.ezcode.demo.domain.BoardPagingDTO;
import org.ezcode.demo.domain.InquiryVO;
import org.ezcode.demo.domain.PageMaker;
import org.ezcode.demo.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


/**
 * BoardController
 */
@Controller
@Slf4j
public class BoardController {

    @Setter(onMethod_ = {@Autowired})
    private BoardService boardService;

    @GetMapping("/inquiry")
    public String inquiryGET(){
        log.info("inquiry GET ------------------------");

        return "/mypage/inquiry";
    }

    @PostMapping("/inquiry")
    public String inquiryPOST(InquiryVO vo){
        log.info("inquiry POST ------------------------" + vo);

        boardService.registerInquiry(vo);

        return "redirect:/mypage/mypage";
    }

    @GetMapping(value="/inquiryList")
    public String inquiryListGET(@ModelAttribute("bdto") BoardPagingDTO dto, Model model) {
        log.info("inquiry list - " + dto);

        log.info("inquiry paging dto------------------------------" 
        + new PageMaker(boardService.getCount(dto), dto));
        model.addAttribute("pm", new PageMaker(boardService.getCount(dto), dto));
        model.addAttribute("list", boardService.inquiryList(dto));
        return "/mypage/inquiryList";
    }

    @ResponseBody
    @GetMapping(value="/inquiryReply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<InquiryVO> inquiryReplyGET(Integer groupno) {
        // log.info("" + groupno);
        
        return new ResponseEntity<>(boardService.inquiryOne(groupno), HttpStatus.OK);
    }

    @GetMapping("/mypage-sample")
    public String sample() {
        return "/mypage/mypage-sample";
    }

    // [NOTICE]==========================================================================
    @GetMapping("/notice/list")
    public String noticeGET(BoardPagingDTO dto, Model model){
        log.info("inquiry GET ------------------------");

        model.addAttribute("list", boardService.noticeList(dto));
        model.addAttribute("pm", new PageMaker(boardService.getNoticeCount(dto), dto));

        return "/notice/list";
    }

    @GetMapping("/notice/read")
    public void read(@ModelAttribute("dto") BoardPagingDTO dto, Model model){

        log.info("notice read get.......................");

        model.addAttribute("notice", boardService.noticeOne(dto.getBno()));

        // return "/notice/read";
    }

    

    
}
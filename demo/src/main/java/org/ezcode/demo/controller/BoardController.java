package org.ezcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


/**
 * BoardController
 */
@Controller
@Slf4j
public class BoardController {


    @GetMapping("/mypage-sample")
    public String sample() {
        return "/mypage/mypage-sample";
    }
    

    
}
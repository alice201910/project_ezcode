package org.ezcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * SampleController
 */
@Controller
@Slf4j
@RequestMapping("/sample/*")
public class SampleController {
    
    @GetMapping("/sample")
    public void ex() {
        log.info("start.........");
    }
}
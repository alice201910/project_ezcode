package org.ezcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/myPage")
@Slf4j
public class MyPageController {
	@GetMapping("/myPage")
	public void myPage() {
		log.info("MyPage....");
	}
}

package org.ezcode.demo.controller;

import org.ezcode.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage")
@Slf4j
public class MyPageController {

	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;

	@GetMapping("/mypage")
	public void myPage() {
		log.info("MyPage....");
	}

}

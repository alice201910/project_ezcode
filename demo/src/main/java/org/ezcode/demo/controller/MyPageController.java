package org.ezcode.demo.controller;

import javax.validation.Valid;

import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/modify")
	public void modifyGET(String userid, Model model) {
		log.info("modify....");
		model.addAttribute("member", memberService.read(userid));
	}

	@PostMapping("/modify")
	public String modifyPOST(@ModelAttribute("member") @Valid MemberVO vo) {
		log.info("modify post...." + vo);
		memberService.modify(vo);
		return "redirect:/mypage/mypage";
	}

	@GetMapping("/password")
	public void passwordChangeGET(String userid) {
		log.info("password change get....");
	}

	@PostMapping("/password")
	public String passwordChangePOST( MemberVO vo) {
		log.info("password change post...." + vo);
		log.info("" + memberService.changePass(vo));
		return "redirect:/mypage/mypage";
	}

	@GetMapping("/inquiryList")
    public void inquiryListGET(){
        log.info("inquiry GET ------------------------");
    }
}

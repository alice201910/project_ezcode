package org.ezcode.demo.controller;

import org.ezcode.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * MemberController
 */
@Slf4j
@Controller
public class MemberController {

    @Setter(onMethod_ = {@Autowired})
    private MemberService memberService;

    @GetMapping("/profile")
    public String profileGET(String userid, Model model) {
        log.info("profile GET--------------------------");
        model.addAttribute("info", memberService.readProfile(userid));
        return "mypage/profile";
    } 
}
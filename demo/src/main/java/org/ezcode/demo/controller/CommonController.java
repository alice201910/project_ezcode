package org.ezcode.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * CommonController
 */
@Controller
@Slf4j
public class CommonController {

    @GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {

		log.info("access Denied:" + auth);

		model.addAttribute("msg", "AccessDenied");

    }
    

    @GetMapping("/customLogin")
	public void loginInput(String error, String logout, Model model) {
		
		log.info("error:" + error);
		log.info("logout:" +logout);
		
		if (error != null) {
			
			model.addAttribute("error" , "Login Error Check User Account");			
		}
		if (logout != null) {
			model.addAttribute("logout","Logout");
		}	
	}

	@GetMapping("/customLogout")
	public void logoutGET() {
		log.info("custom logout--------------------------");
	}
}
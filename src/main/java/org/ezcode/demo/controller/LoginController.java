package org.ezcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/memlogin")
public class LoginController {
	
	@GetMapping("/login")
	
	public void loginPage() {
		
		
	}

}

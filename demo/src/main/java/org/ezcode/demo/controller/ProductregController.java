package org.ezcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productreg")
public class ProductregController {
	
	@GetMapping("/productreg")
	public void productreg() {
	}

}

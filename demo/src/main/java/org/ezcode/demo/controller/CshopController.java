package org.ezcode.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cshop")
@Slf4j
public class CshopController {

	@GetMapping("/list")
	public void cshop() {
		log.info("get index....");
	}
	@GetMapping("/read")
	public void read() {
		log.info("read...");
	}
}

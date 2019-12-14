package org.ezcode.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cshop")
@Slf4j
public class CshopController {

	@GetMapping("/cshop")
	public void cshop() {
		log.info("get index....");
	}
	@GetMapping("/read")
	public void read() {
		log.info("read...");
	}
}

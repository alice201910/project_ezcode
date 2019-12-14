package org.ezcode.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/search")
@Slf4j
public class SearchController {

	@GetMapping("/index")
	public void search() {
		log.info("get index....");
	}

	@GetMapping("/expage")
	public void expage() {
		log.info("get index....");
	}

	@GetMapping("/list")
	public void searchList() {
		log.info("get index....");
	}
	@GetMapping("/detail")
	public void searchDetail() {
		log.info("get index....");
	}
}

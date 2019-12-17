package org.ezcode.demo.controller;

import org.ezcode.demo.domain.ProductVO;
import org.ezcode.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cshop")
@Slf4j
public class CshopController {

	@Setter(onMethod_ = @Autowired )
	private ProductService productService;

	@GetMapping("/list")
	public void cshop() {
		log.info("get index....");
	}
	@GetMapping("/read")
	public void read() {
		log.info("read...");
	}
	@GetMapping("/register")
	public void registerGET(ProductVO vo){
		log.info("register...."+vo);
	}

	@PostMapping("/register")
	public String registerPOST(ProductVO vo){
		log.info("register........"+vo);
		vo.setSeller("yangAchi");
		productService.register(vo);
		return "redirect:/cshop/list";
	}
}

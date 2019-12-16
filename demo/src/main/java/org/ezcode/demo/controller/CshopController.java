package org.ezcode.demo.controller;

import org.ezcode.demo.domain.PageMaker;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cshop")
@Slf4j
public class CshopController {

	@Setter(onMethod_ = {@Autowired})
	private ProductService productService;

	@GetMapping("/list")
	public void listGet(PagingDTO dto, Model model) {
		log.info("get index....");
		model.addAttribute("pm", new PageMaker(productService.getCount(), dto));
		model.addAttribute("list", productService.list(dto));
	}
	@GetMapping("/read")
	public void read() {
		log.info("read...");
	}
}

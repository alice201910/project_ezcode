package org.ezcode.demo.controller;

import java.util.List;

import org.ezcode.demo.domain.PageMaker;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductVO;
import org.ezcode.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cshop")
@Slf4j
public class CshopController {

	@Setter(onMethod_ = {@Autowired})
	private ProductService productService;

	@GetMapping(value = "/listData", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<ProductVO>> listDataGet(PagingDTO dto, Model model) {
		log.info("get index....");
		model.addAttribute("pm", new PageMaker(productService.getCount(dto), dto));
		model.addAttribute("list", productService.list(dto));

		return new ResponseEntity<>(productService.list(dto), HttpStatus.OK);
	}

	@GetMapping("/list")
	public void listGet(PagingDTO dto, Model model) {
		model.addAttribute("pm", new PageMaker(productService.getCount(dto), dto));
		model.addAttribute("list", productService.list(dto));
	}

	@GetMapping("/read")
	public void read() {
		log.info("read...");
	}
}

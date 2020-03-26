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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cshop")
@Slf4j
public class CshopController {

	@Setter(onMethod_ = @Autowired )
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
	
	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void registerGET(ProductVO vo){
		log.info("register...."+vo);
	}

	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String registerPOST(ProductVO vo){
		log.info("register........"+vo);
		vo.setSeller("yangAchi");
		
		if(vo.getAttachList()!=null){
			log.info("-------------------------------");
			vo.getAttachList().forEach(attach->{
				log.info("attach : "+attach);
			});
			log.info("-------------------------------");
		}
		
		productService.register(vo);
		return "redirect:/cshop/list";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify")
	public void modifyGET(Integer pno, Model model) {
		log.info("modify get: " + pno);
		log.info("modify get: " + productService.findByPno(pno));
		model.addAttribute("product", productService.findByPno(pno));
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify")
	public String modifyPOST(ProductVO vo, String[] uuids) {
		log.info("modify post: " + vo);

		// 삭제할 파일
		if (uuids != null) {
			for (int i = 0; i < uuids.length; i++) {
				log.info("uuids ====================================" + uuids[i]);
				productService.fileDelete(uuids[i]);
			}
		}

		productService.modify(vo);

		// read 로 변경해야함
		return "redirect:/cshop/list";
	}

	@PostMapping("/delete")
	public void deltePost(Integer pno) {
		productService.delete(pno);
	}
	
}

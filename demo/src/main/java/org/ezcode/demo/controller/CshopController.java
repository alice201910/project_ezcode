package org.ezcode.demo.controller;

import java.util.HashMap;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.ezcode.demo.domain.LikeVO;
import org.ezcode.demo.domain.PageMaker;
import org.ezcode.demo.domain.ProductPagingDTO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.PaymentDTO;
import org.ezcode.demo.domain.ProductVO;
import org.ezcode.demo.service.KakaoService;
import org.ezcode.demo.service.LikeService;
import org.ezcode.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cshop")
@Slf4j
public class CshopController {

	@Setter(onMethod_ = @Autowired)
	private ProductService productService;

	@Setter(onMethod_ = @Autowired)
	private KakaoService KakaoService;

	@Setter(onMethod_ = @Autowired)
	private LikeService LikeService;

	@GetMapping(value = "/listData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<String>> listDataGet(String keyword) {
		log.info("get index....");
		log.info("keyword: " + keyword);
		ProductPagingDTO dto = new ProductPagingDTO();
		dto.setType("N");
		dto.setKeyword(keyword);
		log.info("controller dto" + dto);

		log.info(productService.searchAutoKeyword(dto) + "");

		return new ResponseEntity<>(productService.searchAutoKeyword(dto), HttpStatus.OK);
	}


	@GetMapping("/list")
	public void listGet(ProductPagingDTO dto, Model model) {
		model.addAttribute("pm", new PageMaker(productService.getCount(dto), dto));
		model.addAttribute("list", productService.list(dto));
	}

	@GetMapping("/read")
	public void read(@ModelAttribute("dto") ProductPagingDTO dto, Model model) { // read에서 필요한 거 -> page, amount, keyword, type
																			// 등 => 이거 다 PagingDTO에 있으므로 dto를 파라미터로 받는다.
		log.info("read... : "+dto);
		// 검색한 결과를 화면에 뿌려야 함.
		model.addAttribute("goods", productService.findByPno(dto.getPno()));
		model.addAttribute("ratingavg", productService.ratingGrade(dto.getPno()));
		model.addAttribute("CntReview", productService.cntReview(dto.getPno()));

		model.addAttribute("trade", productService.findById(dto));
		model.addAttribute("pselect", productService.findSelect(dto));
		// kakaoPay
	}

	@GetMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public void registerGET(ProductVO vo) {
		log.info("register...." + vo);
	}

	@PostMapping("/register")
	@PreAuthorize("isAuthenticated()")
	public String registerPOST(ProductVO vo) {
		log.info("register........" + vo);

		if (vo.getAttachList() != null) {
			log.info("-------------------------------");
			vo.getAttachList().forEach(attach -> {
				log.info("attach : " + attach);
			});
			log.info("-------------------------------");
		}

		productService.register(vo);
		return "redirect:/cshop/list";
	}

	@GetMapping("/modify")
	@PreAuthorize("isAuthenticated()")
	public void modifyGET(Integer pno, Model model) {
		log.info("modify get: " + pno);
		log.info("modify get: " + productService.findByPno(pno));
		model.addAttribute("product", productService.findByPno(pno));
	}

	@PreAuthorize("principal.member.userid == #vo.seller")
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
	public String deltePost(Integer pno) {
		productService.delete(pno);
		return "redirect:/cshop/list";
	}

	@GetMapping(value = "/profile/{seller}/{skip}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> getListBySeller(@PathVariable("seller") String seller,
			@PathVariable("skip") int skip) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		resultMap.put("cnt", productService.getCountBySeller(seller));
		resultMap.put("product", productService.getListBySeller(seller, skip));

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}
		// return "redirect:/cshop/read?pno="+vo.getPno();

	// ********************************** kakaoPay ********************************** 

	@GetMapping("/kakaoPay")
	@PreAuthorize("isAuthenticated()")
	public void kakaoPayGet() {
		log.info("kakaoPayGet............");
	}

	@PostMapping("/kakaoPay")
	@PreAuthorize("isAuthenticated()")
	public String kakaoPayPOST(PaymentDTO pdto, HttpSession session) {
		log.info("kakaoPay post... pdto :" + pdto);
		// String pname = URLEncoder.encode(pdto.getPname(), "UTF-8");
		// String price = URLEncoder.encode(pdto.getPrice(), "UTF-8");
		// String seller = URLEncoder.encode(pdto.getSeller(), "UTF-8");
		if(session!=null){
			session.setAttribute("pdto", pdto);
			session.setMaxInactiveInterval(60 * 3);
		}

		/* +"?pname="+pname+"&price="+price+"&seller="+seller */

		return "redirect:" + KakaoService.kakaoPayStartUp(pdto);
	}

	@GetMapping("/kakaoPaySuccess")
	@PreAuthorize("isAuthenticated()")
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpSession session)
			throws UnsupportedEncodingException {
		log.info("kakaoPaySuccess get..............");
		log.info("kakaoPaySuccess pg_token : " + pg_token);
		
		if(session!=null){
			PaymentDTO pdto = (PaymentDTO) session.getAttribute("pdto");
			log.info("getPno : "+pdto.getPno());
			// pname = URLEncoder.encode(pname, "UTF-8");
			// price = URLEncoder.encode(price, "UTF-8");
			// seller = URLEncoder.encode(seller, "UTF-8");
			pdto.setPno(pdto.getPno());
			model.addAttribute("paymentInfo", KakaoService.kakaoPaymemtInfo(pg_token, pdto));
		}

	}

	// 취소

	@PostMapping("/kakaoPayCancel")
	@PreAuthorize("isAuthenticated()")
	public void kakaoPayCancel(PaymentDTO pdto, Model model) {
		log.info("kakaoPayCancel pdto : " + pdto);

		model.addAttribute("paymentCancleInfo", KakaoService.kakaoPaymentCancel(pdto));
	}

	// 찜하기
	@PostMapping("/likeInsert")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	public ResponseEntity<String> likeInsert(LikeVO vo) {
		log.info("LikeVO : " + vo);

		int result = LikeService.ChooseInsert(vo);

		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/likeDelete")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	public ResponseEntity<String> likeDelete(LikeVO vo) {
		log.info("LikeVO : " + vo);
		int result = LikeService.chooseDelete(vo);
		return result >= 1 ? new ResponseEntity<>("success", HttpStatus.OK) : new ResponseEntity<>("error", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

package org.ezcode.demo.controller;

import org.ezcode.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

import org.ezcode.demo.domain.LikePagingDTO;
import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.domain.PagingDTO;
import org.ezcode.demo.domain.ProductPagingDTO;
import org.ezcode.demo.service.LikeService;
import org.ezcode.demo.service.MemberService;
import org.ezcode.demo.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/mypage")
@Slf4j
public class MyPageController {

	@Setter(onMethod_ = {@Autowired})
	private MemberService memberService;

	@Setter(onMethod_ = @Autowired)
	private LikeService likeService;

	@Setter(onMethod_ = @Autowired)
	private TradeService tradeService;

	@GetMapping("/mypage")
	public void myPage() {
		log.info("MyPage....");
	}

	//찜목록
	@GetMapping("/likelist")
	public void likeListGET(){

	}

	@GetMapping(value = "/likelist/{amounts}/{uid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<LikePagingDTO> likelist(Model model, @PathVariable("amounts") int amounts,  @PathVariable("uid") String uid ){
		log.info("likelist uid : "+ uid);
		log.info("likelist amounts :" + amounts);
		
		// log.info("likeService.likeList(uid) : "+likeService.likeList(uid));
		//model.addAttribute("likeList", likeService.likeList(dto));
		//model.addAttribute("pm", new PageMaker(likeService.likeCnt(), dto));

		ProductPagingDTO dto = new ProductPagingDTO();
		dto.setAmount(amounts);
		dto.setUid(uid);
		return new ResponseEntity<>(likeService.getLikeList(dto), HttpStatus.OK);
	}
	
	@PostMapping(value="/likeDelete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> likeDelPOST(Integer lno){
		log.info("lno : "+lno);

		return likeService.likeDelete(lno)==1? new ResponseEntity<>("success", HttpStatus.OK):new ResponseEntity<>("Fail", HttpStatus.INTERNAL_SERVER_ERROR); 		
	}

	@GetMapping("/tradelist")
	public void tradelist(ProductPagingDTO dto, Model model){
		model.addAttribute("tlist", tradeService.tradelist(dto.getUid()));
		model.addAttribute("tradeCnt", tradeService.tradeCnt(dto.getUid()));
	}
 
}

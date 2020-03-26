package org.ezcode.demo.controller;

import java.security.Principal;
import java.util.List;

import org.ezcode.demo.domain.FriendVO;
import org.ezcode.demo.domain.LikePagingDTO;
import org.ezcode.demo.domain.MemberVO;
import org.ezcode.demo.domain.ProductPagingDTO;
import org.ezcode.demo.security.CustomOAuth2User;
import org.ezcode.demo.service.FriendService;
import org.ezcode.demo.service.LikeService;
import org.ezcode.demo.service.MemberService;
import org.ezcode.demo.service.ProductService;
import org.ezcode.demo.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private MemberService service;

	@Setter(onMethod_ = @Autowired)
	private LikeService likeService;

	@Setter(onMethod_ = @Autowired)
	private TradeService tradeService;

	@Autowired
	private FriendService friendService;

	@Autowired
	private ProductService productService;

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
 

	// 회원 관련 메소드
	@GetMapping({ "/myinfo", "/mypw", "/quit" })
	public void myInfoGET(Principal principal, @AuthenticationPrincipal CustomOAuth2User customUser, Model model) {

		// 소셜으로 로그인했는지, 그냥 회원으로 로그인했는지 구별해줘야 함
		String username = "";

		if (customUser != null) { // 소셜 로그인

			log.info("sns login!");

			username = customUser.getMember().getUserid();

		} else { // 그냥 회원으로 로그인

			username = principal.getName();
		}

		log.info("username............." + username);

		log.info("" + service.findById(username));

		if (service.findById(username) != null) {
			model.addAttribute("memberInfo", service.findById(username));
		}
	}

	@PostMapping("/myinfo")
	public String myInfoPOST(MemberVO vo) {

		log.info("myinfo post...........................");
		log.info("" + vo);

		log.info("" + service.ModifyMember(vo));

		return "redirect:/mypage/myinfo";
	}

	@PostMapping("/mypw")
	public String mypwPOST(MemberVO vo) {

		log.info("my pw modify post.................");

		service.ModifyPw(vo);

		return "redirect:/mypage/mypw";
	}

	@GetMapping("/mypartner")
	public void mypartnerGET(Principal principal, @AuthenticationPrincipal CustomOAuth2User customUser, Model model) {

		log.info("my partner page get....................");

		// 소셜으로 로그인했는지, 그냥 회원으로 로그인했는지 구별해줘야 함
		String username = "";

		if (customUser != null) { // 소셜 로그인

			log.info("sns login!");

			username = customUser.getMember().getUserid();

		} else { // 그냥 회원으로 로그인

			username = principal.getName();
		}

		log.info("username............." + username);

		log.info("" + friendService.findRequestFriends(username));

		model.addAttribute("memberInfo", service.findById(username));
		model.addAttribute("requestList", friendService.findRequestFriends(username));
		model.addAttribute("friendList", friendService.findFriends(username));
		
	}

	@GetMapping("/findid")
	public void mypartnerPOST(String userid, Model model) {

		log.info(userid);

		log.info("" + service.findListById(userid));

		model.addAttribute("idList", service.findListById(userid));
	}

	@PostMapping("/delmate")
	public String delMatePOST(int mateno) {

		log.info("" + mateno);

		boolean result = friendService.deleteFriend(mateno);

		log.info("" + result);

		return "redirect:/mypage/mypartner";
	}

	@PostMapping("/updatemate")
	public String updateMatePOST(int mateno) {

		log.info("" + mateno);

		boolean result = friendService.ModifyFriend(mateno);

		log.info("" + result);

		return "redirect:/mypage/mypartner";
	}

	@GetMapping("/pdetail")
	public void partnerDetailGET(String userid, Principal principal, @AuthenticationPrincipal CustomOAuth2User customUser, Model model) {

		// 소셜으로 로그인했는지, 그냥 회원으로 로그인했는지 구별해줘야 함
		String myid = "";

		if (customUser != null) { // 소셜 로그인

			log.info("sns login!");

			myid = customUser.getMember().getUserid();

		} else { // 그냥 회원으로 로그인

			myid = principal.getName();
		}

		log.info("my id.........." + myid);

		model.addAttribute("myid", myid);

		log.info("userid.........." + userid);

		// userid 로 정보 가져옴
		MemberVO mem = service.findById(userid);
		log.info("" + mem);

		model.addAttribute("detail", mem);

		// userid 로 친구 목록 가져옴
		List<FriendVO> fvo = friendService.findFriends(userid);
		log.info("" + fvo);

		model.addAttribute("flist", fvo);

		// userid 로 판매글 목록 가져옴
		model.addAttribute("plist", productService.findBySeller(userid));

		log.info("" + friendService.checkFriend(myid, userid));

		// 나랑 친구인지 아닌지?
		model.addAttribute("fstate", friendService.checkFriend(myid, userid));
		
	}

	@PostMapping("/insertMate")
	public String insertMate(String sender, String receiver) {

		log.info("insert friend ...................");
		log.info("" + friendService.makeFriend(sender, receiver));
		
		return "redirect:/mypage/pdetail?userid=" + receiver;
	}

// -------------------------------------------------------------------------------------

	@PostMapping("/quit")
	public ResponseEntity<String> checkPwPOST(String userid) {

		log.info(userid);

		boolean result = service.quitMember(userid);

		log.info("" + result);

		return result ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/checkpw")
	public ResponseEntity<String> checkPwPOST(String userid, String userpw) {

		log.info(userid);
		log.info(userpw);

		// id와 비밀번호를 받아서 db에 있는 정보와 일치하는지 확인한다.
		boolean result = service.checkByIdAndPw(userid, userpw);

		log.info("" + result);

		return result ? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

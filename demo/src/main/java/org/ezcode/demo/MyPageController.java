package org.ezcode.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
import lombok.extern.log4j.Log4j;
=======
>>>>>>> heejin
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/myPage")
@Slf4j
public class MyPageController {
	@GetMapping("/myPage")
	public void myPage() {
		log.info("MyPage....");
	}
}

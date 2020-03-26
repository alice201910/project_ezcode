package org.ezcode.demo.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import org.ezcode.demo.domain.PageMaker;
import org.ezcode.demo.domain.ParseVO;
import org.ezcode.demo.domain.SearchDTO;
import org.ezcode.demo.security.CustomOAuth2User;
import org.ezcode.demo.service.FriendService;
import org.ezcode.demo.service.ParseService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/search")
@Slf4j
public class SearchController {

	@Autowired
	private ParseService parseService;

	@Autowired
	private FriendService friendService;

	private List<ParseVO> result = new ArrayList<ParseVO>();

	//private List<ParseVO> list = new ArrayList<>();

	@GetMapping("/index")
	public void search() {
		log.info("get index....");
	}

	@GetMapping("/expage")
	public void expage() {
		log.info("get index....");
	}

	@GetMapping("/list")
	public void searchList(@ModelAttribute("dto") SearchDTO dto, Model model
		, Principal principal, @AuthenticationPrincipal CustomOAuth2User customUser) {

		String[] keywordArr = dto.getKeywords();
		
		// 1. 키워드 없을 때
		if(dto.getKeyword().equals("")) {

			List<ParseVO> nullList = new ArrayList<>();

			model.addAttribute("list", nullList);
			model.addAttribute("pm", new PageMaker(0, dto));

		} else { // 2. 키워드 있을 때

			log.info("parse get..........................");
			log.info("searchDTO: " + dto);
			
			String kk = dto.getKeyword();

			log.info("kk??????????????????....." + kk);

			Stream.of(keywordArr).forEach(k -> {

				// 키워드 한개로 지정해놓는다.
				dto.setKeyword(k);

				// 파싱한 결과 저장하는 리스트
				result = new ArrayList<ParseVO>();

				String curSiteLink = dto.getSiteLink();

				log.info("입력받은 사이트 링크....." + curSiteLink);

				String githubId = "";

				List<String> gitFriendList = new ArrayList<>();

				// 2-1. 검색사이트 github일때
				if(curSiteLink.contains("Github")) {

					githubId = (String) customUser.getAttributes().get("login");

					if(dto.getSiteLink().equals("myGithub")) {
						
						dto.setSiteLink("github");
						dto.setPath(githubId);

						// 키워드 하나 지정한 걸로 검색했는데 null 뜨면
						if (parseService.find(dto).isEmpty()) {

							log.info("널임");
							log.info("" + dto);

							log.info("원래 사이트 링크...." + curSiteLink);

							dto.setSiteLink(curSiteLink);

							log.info("원래대로 다시 돌린 사이트 링크...." + dto.getSiteLink());

							// 파일 저장된 경로에서 해당키워드로 파싱한 다음 DB에 저장시킴
							log.info("keyword..........................." + k);

							// 저장 경로 변수
							String saveDir = "C:\\ezcode\\" + githubId;
							log.info("저장경로.........................." + saveDir);
							
							dto.setPath(githubId);

							log.info("dto path set......." + dto);

							dto.setSiteLink("github");

							log.info("" + dto);
		
							result = playParsing(saveDir, k, dto.getComment(), dto.getLangs());
						}

					} else if(dto.getSiteLink().equals("partnerGithub")) {

						gitFriendList = friendService.findGithubFriends(githubId + "_github");
						
						log.info("깃허브 친구 목록......." + gitFriendList);

						dto.setSiteLink("github");

						gitFriendList.forEach(friend -> {

							friend = friend.substring(0, friend.lastIndexOf("_"));

							dto.setPath(friend);

							// 키워드 하나 지정한 걸로 검색했는데 null 뜨면
							if (parseService.find(dto).isEmpty()) {

								log.info("널임");
								log.info("" + dto);

								log.info("원래 사이트 링크...." + curSiteLink);

								dto.setSiteLink(curSiteLink);

								log.info("원래대로 다시 돌린 사이트 링크...." + dto.getSiteLink());

								log.info("friend id..........." + friend);

								String saveDir = "C:\\ezcode\\" + friend;

								log.info("저장경로.........................." + saveDir);
								
								dto.setPath(friend);

								log.info("dto path set......." + dto);

								dto.setSiteLink("github");
	
								log.info("" + dto);

								result.addAll(playParsing(saveDir, k, dto.getComment(), dto.getLangs()));
							}
						});
					}

				} else if(dto.getSiteLink().equals("google")) { // 2-2. 검색사이트 google일 때

					if (parseService.find(dto).isEmpty()) {
						result = ( crawling(dto.getKeyword(), dto.getLang()) );
					}
				}

				log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
				log.info("" + result);
				log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

				result.forEach(r -> {
					log.info("" + r);
					parseService.insertCode(r);
				});

			});

			// 총 결과 개수
			dto.setKeyword(kk);
			
			model.addAttribute("list", parseService.findAll(dto));
			model.addAttribute("pm", new PageMaker(parseService.getCount(dto), dto));

		}
	}

	@GetMapping("/detail")
	public void searchDetail(@ModelAttribute("dto") SearchDTO dto,  Model model) {

		log.info("get index...............................");

		log.info("" + dto);
		
		model.addAttribute("result", parseService.findByPno(dto.getPno()));

	}

	// ----------------------------------------------------------------------------
	// 지정 폴더에서 해당 키워드 찾아 파싱하는 메서드
	public List<ParseVO> playParsing(String path, String keyword, String comment, String[] searchLang) {

		ParseVO vo = new ParseVO();

		int maxLine = 0;
		boolean keywordOn = false;
		boolean allKeyword = false; // 모든 키워드 찾는 변수

		if(comment.equals("c")) {
			keywordOn = true;
		} else {
			allKeyword = true;
		}

		String[] keywords = keyword.split(",");

		File file = new File(path);

		File[] fileList = file.listFiles();

		try {
			for (File f : fileList) {
				if (f.isFile()) { // 파일일때
					/*
					 * 예외처리
					 */
					try {
						FileReader fr = new FileReader(f.getCanonicalPath());
						BufferedReader br = new BufferedReader(fr);

						String line = "";

						String fpath = f.getPath();

						// 확장자 얻어오기
						int langIdx = f.getName().lastIndexOf('.');
						String lang = f.getName().substring(langIdx + 1);

						// 파일 저장된 경로와 파일명 얻어오기
						int idx = fpath.lastIndexOf("\\");
						String fname = fpath.substring(idx + 1);
						String fnamePath = fpath.substring(0, idx);
						
						for(int l=0; l<searchLang.length; l++) {

							/*
							* if 조건문
							*/
							if (searchLang[l].equals(lang)) { // 언어 조건

								boolean isComment = false; // 여러줄 주석
								boolean isCode = false; // 검색에 해당되는 코드
								boolean isBrace = false; // 브레이스
								boolean blank = false;

								boolean tenline = false; // 열줄만 출력하는 변수
								boolean flag = false; // 한줄에 브레이스 짝이 맞을 때

								Stack<String> checkBrace = new Stack<String>(); // 브레이스 개수 체크

								String code = "";

								int tbrace = 0; // { } 한쌍 일때 +1 해주려는 변수

								int k = 0;

								// 한줄씩 읽어온다
								for (int i = 1; (line = br.readLine()) != null; i++) {

									if (!line.trim().startsWith("import ")) {

										int open = 0;
										int close = 0;

										if (line.contains("{")) {
											open++;
										}
										if (line.contains("}")) {
											close++;
										}

										// { 이 한개 이상이고, { 와 } 쌍이 맞을 때
										if (open > 0 && open == close) {

											flag = true;
											tbrace++;

										} else {

											flag = false;
											tbrace = 0;

										}

										if (allKeyword && !keywordOn) { // 키워드 전체검색
											for (int n = 0; n < keywords.length; n++) {
												if ((line.toLowerCase().contains(keywords[n].toLowerCase()) || line.toUpperCase().contains(keywords[n].toUpperCase()))) {

													vo = new ParseVO();

													keywordOn = true;

													code = "";

													maxLine = 0;

													// vo = ParseVO.builder().siteLink("github").comment(0).keyword(keywords[n]).path(fnamePath).fname(fname).start(i).build();

													vo.setSiteLink("github");
													vo.setComment(0);
													vo.setKeyword(keywords[n]);
													vo.setPath(fnamePath);
													vo.setFname(fname);
													vo.setStart(i);

													isCode = true; // 코드 시작을 알려줌

													k = i + 11; // 혹시나 열줄만 출력할거면 아래 10줄만 출력하기 위한 변수
												}
											}
										} // if(allKeyword)

										if (!allKeyword) {

											// 1. 여러줄 주석
											if (line.trim().startsWith("/*")) {
												isComment = true;
											}

											// 주석일때
											if (isComment) {
												for (int n = 0; n < keywords.length; n++) {
													if ((line.toLowerCase().contains(keywords[n].toLowerCase()) || line.toUpperCase().contains(keywords[n].toUpperCase()))) {
														vo = new ParseVO();

														code = "";

														maxLine = 0;

														//vo = ParseVO.builder().siteLink("github").comment(1).keyword(keywords[n]).path(fnamePath).fname(fname).start(i).build();

														vo.setSiteLink("github");
														vo.setComment(1);
														vo.setKeyword(keywords[n]);
														vo.setPath(fnamePath);
														vo.setFname(fname);
														vo.setStart(i);

														isCode = true;

													}
												}

												// 주석 끝 (여러줄 주석)
												if (line.trim().endsWith("*/")) {
													isComment = false;
													continue;
												}

											} // if(iscomment)

										} // if(!allKeyword)

										// -----------------------------------------------------------------------------------------------------------

										// 2. 한줄 주석
										if (line.contains("//") && !allKeyword) { // 라인에 //가 포함되어있다.

											// 주석인데 해당 키워드 존재할때
											if (line.trim().startsWith("//")) {

												for (int n = 0; n < keywords.length; n++) {
													if ((line.toLowerCase().contains(keywords[n].toLowerCase()) || line.toUpperCase().contains(keywords[n].toUpperCase()))) {
														vo = new ParseVO();

														code = "";

														maxLine = 0;

														//vo = ParseVO.builder().siteLink("github").comment(1).keyword(keywords[n]).path(fnamePath).fname(fname).start(i).build();

														vo.setSiteLink("github");
														vo.setComment(1);
														vo.setKeyword(keywords[n]);
														vo.setPath(fnamePath);
														vo.setFname(fname);
														vo.setStart(i);

														isCode = true; // 코드 시작을 알려줌

														k = i + 10; // 아래 10줄만 출력하기 위한 변수

													}

												} // end of for
											}

										} // 한줄 주석 끝
										// -----------------------------------------------------------------------------------------------------------

										maxLine++;

											if (isCode) {
												isBrace = (line.contains("{") || line.contains("{")) ? true : isBrace;
											}

											if (isCode && tenline) { // 열 줄만 나오게 하는 코드이다.
												if (i <= k) {
													code += line + "\r\n";
													isBrace = false;
												}
												if (i == k + 1 || maxLine >= 30) {
													
													//vo.builder().code(code).lang(lang).build();

													vo.setCode(code);
													vo.setLang(lang);
													result.add(vo);

													// System.out.println(code);
													flag = false;
													isBrace = false;
													isCode = false;
													tenline = false;
													keywordOn = false;

													code = "";

												}
											}

											// 브레이스 있을 때
											else if (isCode && isBrace) {
												code += line + "\r\n";

												// 코드는 시작했다.
												// 브레이스가 열려있다.
												// flag = 한줄에 { } 둘 다 있을때 true.
												if (flag && tbrace >= 2) { // 두 줄 연속으로 {} 있으면?
													tenline = true; // 열줄만 출력하게 tenline이 트루가 됩니다.
													tbrace = 0; // 어차피 열 줄만 출력하도록 마음먹었으니 tbrace는 0으로.
												}

												else if (isCode && !flag) {
													// 브레이스 개수 체크

													// 라인에 {가 있으면 스택에 하나 추가
													if (line.contains("{")) {
														checkBrace.push("{");
														blank = false;
													}

													// 라인에 }가 있으면 스택 하나 팝팝
													if (line.contains("}")) {
														checkBrace.pop();
														if (checkBrace.empty())
															blank = true;
													}

													// 브레이스 체크 스택 비었으면 코드 출력안함
													if ((checkBrace.empty() && blank) || maxLine >= 30) {

														//vo.builder().code(code).lang(lang).build();

														vo.setCode(code);
														vo.setLang(lang);
														result.add(vo);

														code = "";
														isBrace = false;
														isCode = false;
														keywordOn = false;
														continue;
													}

												} // end if(!flag)

											} // end if(isCode && isBrace)

											else if (isCode && !isBrace) {
												code += line + "\r\n";

												if (i == k + 1 || maxLine >= 30) {
													
													//vo.builder().code(code).lang(lang).build();

													vo.setCode(code);
													vo.setLang(lang);
													result.add(vo);

													code = "";

													tenline = false;
													isCode = false;
													keywordOn = false;
												}
											}

										// -----------------------------------------------------------------------------------------------------------
									}
								} // line출력 for문 끝
								isComment = false; // 여러줄 주석
								isCode = false; // 검색에 해당되는 코드
								isBrace = false; // 브레이스

								tenline = false; // 열줄만 출력하는 변수
								flag = false; // 한줄에 브레이스 짝이 맞을 때.
								keywordOn = false;
							}
						}

						br.close();

					} catch (Exception e) {
						// TODO: handle exception
					}

				} else if (f.isDirectory()) {
					// 하위 디렉토리 존재 시 다시 탐색
					playParsing(f.getCanonicalPath().toString(), keyword, comment, searchLang);
				}

			}

		} catch (IOException e) {

		}

		return result;

	} // subDirList2()

	public List<ParseVO> crawling(String keyword, String langs) {
		ParseVO vo = new ParseVO();

		int cnt = 0;
		String[] ran = {"반복", "포", "조건", "이프"};

		int page = 1;

		//String langs = lang; // 여기도

		for(int i = 0; i < ran.length; i++) {
			if(keyword.contains(ran[i])) {
				cut(ran[i]);
			}
		}
		
		if(!keyword.replaceAll("[ㄱ-힣]", "").equals("")) {
			keyword = keyword.replaceAll("[ㄱ-힣]", "");
			
		}
		

		
		String[] grammer = {}; // 파싱 메소드에 보내줄 배열값.

		grammer = keyword.split(" ");


		String grammerurl = keyword.replaceAll(" ", "+"); // url에 보내질 문자열
		
		
		String langurl = langs.replaceAll(" ", "+");

		Document doc = null;
		Document doc2 = null;

		String str = "div.info>span.f_l>a:nth-child(1)"; // 페이지 돌면서 사이트들 링크 따오는거

		long start = System.currentTimeMillis();
		long middle = 0L;			// 중간점검 하는 변수
		
		do {
			String urlurl1 = "https://search.daum.net/search?w=blog&f=section&SA=tistory&lpp=10&nil_profile=vsearch&nil_src=tistory&q=" + langurl + "+" + grammerurl + "&page=" + page;

			log.info("유알엘 확인!!!!!!!!!!!!   " + urlurl1);

			try {
				doc = Jsoup.connect(urlurl1).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36").get();

			} catch (IOException e) {
				e.printStackTrace();
			}

			org.jsoup.select.Elements element = doc.select(str);
			for (Element el : element) {

				String url2 = el.attr("abs:href");

				String[] str2 = { "pre", ".colorscripter-code td:nth-child(2) div:nth-child(1)" };
				try {
					doc2 = Jsoup.connect(url2).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.79 Safari/537.36").get();
				
				} catch (IOException e) {
					e.printStackTrace();
				}

				for (int i = 0; i < str2.length; i++) {
					org.jsoup.select.Elements element2 = doc2.select(str2[i]);
					if (!str2[i].equals("pre")) {
						for (Element el2 : element2) { // 하위 뉴스 기사들을 for문 돌면서 출력
							for (Element al2 : el2.select("div div")) {
								
								vo = new ParseVO();
				
								crawlingParse(al2.text(), grammer, vo, langs, url2, el.nextElementSibling().text());
								middle = System.currentTimeMillis();
								if(middle - start >= 1000) {
									break;
								}
							}
						}
					} else {
						for (Element el2 : element2) { // 하위 뉴스 기사들을 for문 돌면서 출력
							
							vo = new ParseVO();
							
							crawlingParse(el2.text(), grammer, vo, langs, url2, el.nextElementSibling().text());
							middle = System.currentTimeMillis();
							if(middle - start >= 1000) {
								break;
							}
						}
					}
				}

			}
			page += 1;

		} while (page <= 5);

		long end = System.currentTimeMillis() - start;
		
		log.info("걸린 시간 : " + end);

		// result.add(vo);

		return result;
	}

	public void crawlingParse(String all, String[] grammer, ParseVO vo, String lang, String url2, String fname) {

		String str = "";

		boolean allKeyword = true; // 모든 키워드 찾는 변수
		boolean keywordOn = false;
		
		InputStream is = new ByteArrayInputStream(all.getBytes());

		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		

		try {

			String line = "";

			boolean isCode = false; // 검색에 해당되는 코드
			boolean isBrace = false; // 브레이스
			boolean blank = false;
			
			boolean tenline = false; // 열줄만 출력하는 변수
			boolean flag = false; // 한줄에 브레이스 짝이 맞을 때.

			Stack<String> checkBrace = new Stack<String>(); // 브레이스 개수 체크

			String code = "";

			int tbrace = 0;
			int k = 0;


			// 한줄씩 읽어온다
			for (int i = 1; (line = br.readLine()) != null; i++) {

				vo = new ParseVO();

				//vo.builder().siteLink("google").comment(0).path(fname).fname(fname).lang(lang).build();
				
				vo.setSiteLink("google");
				vo.setComment(0);
				vo.setPath(url2);
				vo.setFname(fname);
				vo.setLang(lang);
				
				if (!line.trim().startsWith("import ") && !line.trim().startsWith("public") && !line.trim().startsWith("package")) {

					int open = 0; // 한 줄에 { 갯수
					int close = 0; // 한 줄에 } 갯수

					if (line.contains("{")) {
						open++;
					}
					if (line.contains("}")) {
						close++;
					}

					if (open > 0 && open == close) { // 한줄에 브레이스 열고닫는게 둘다 있다?
						flag = true; // 플래그 on
						tbrace++; // 티 브레이스 +1
					} else {
						flag = false; // 누적되지 않는다면 플래그와 tbrace 원래대로.
						tbrace = 0;
					}

					// if (allKeyword) { // 키워드 전체검색
						for (int n = 0; n < grammer.length; n++) {
							if ((line.toLowerCase().contains(grammer[n].trim().toLowerCase()) 
									|| line.toUpperCase().contains(grammer[n].trim().toUpperCase())) 
										&& keywordOn == false) {
								keywordOn = true;
								str = grammer[n];

								code = "";

								isCode = true; // 코드 시작을 알려줌

								k = i + 11; // 혹시나 열줄만 출력할거면 아래 10줄만 출력하기 위한 변수
							}
						}
					// }


					// 브레이스 유무
					if (isCode) {
						isBrace = ((line.indexOf("{")) != -1 || (line.contains("}"))) ? true : isBrace;
					}


					if (isCode && tenline) { // 열 줄만 나오게 하는 코드이다.
						if (i <= k) {
							code += line + "\n";
							isBrace = false;
						}
						if (i == k + 1) {

							//vo.builder().start(i).code(code).keyword(str).build();

							vo.setStart(i);			
							vo.setCode(code);
							vo.setKeyword(str);
							result.add(vo);

							code = "";
							flag = false;
							isBrace = false;
							tenline = false;
							isCode = false;
							keywordOn = false;

						}
					}

					else if (isCode && isBrace) {

						code += line + "\n";

						// 코드는 시작했다.
						// 브레이스가 열려있다.
						// flag = 한줄에 { } 둘 다 있을때 true.
						if (flag && tbrace >= 2) { // 두 줄 연속으로 {} 있으면?
							tenline = true; // 열줄만 출력하게 tenline이 트루가 됩니다.
							tbrace = 0; // 어차피 열 줄만 출력하도록 마음먹었으니 tbrace는 0으로.
						}

						else if (isCode && !flag) { // 그 라인에 { } 둘다 있지 않으면.

							if (line.indexOf("{") != -1) {
								checkBrace.push("{"); // 라인에 {가 있으면 스택에 하나 추가
								blank = false;
								
							}
							if (line.indexOf("}") != -1) {
								checkBrace.pop(); // 라인에 }가 있으면 스택 하나 팝팝
								if(checkBrace.empty()) {
									blank = true;
								}
							}

							if (checkBrace.empty() && blank) {

								//vo.builder().start(i).code(code).keyword(str).build();
								
								vo.setStart(i);			
								vo.setCode(code);
								vo.setKeyword(str);
								result.add(vo);
								
								code = "";
								isBrace = false;
								isCode = false;
								keywordOn = false;
								continue;
							}
						} // end if(!flag)

//									isBrace = false;

					} // end if(isCode && isBrace)

					else if (isCode && !isBrace) {
						code += line + "\n";

						if (i == k + 1) {

							vo.setStart(i);			
							vo.setCode(code);
							vo.setKeyword(str);
							result.add(vo);
							
							code = "";
							tenline = false;
							isCode = false;
							keywordOn = false;
						}
					}
					continue;
				} // 임포트 방지
			} // line출력 for문 끝

			isCode = false; // 검색에 해당되는 코드
			isBrace = false; // 브레이스

			tenline = false; // 열줄만 출력하는 변수
			flag = false; // 한줄에 브레이스 짝이 맞을 때.
			keywordOn = false;

			br.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	} // subDirList()

	public static String cut(String kw) {

		if(kw.equals("반복") || kw.equals("포")) {
			return kw = kw.replace(kw, "for");
		}
		
		if(kw.equals("조건") || kw.equals("이프")) {
			return kw = kw.replace(kw, "if");
		}

		return kw;	
	}

}

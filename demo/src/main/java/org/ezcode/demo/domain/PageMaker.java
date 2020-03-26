package org.ezcode.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

// @Getter
// @Setter
// @ToString
// @Slf4j
// public class PageMaker {
	
// 	private int start;
// 	private int end;
// 	private boolean prev, next;
// 	private int total;
// 	private PagingDTO paging;
	
// 	//객체를 만드는 제약조건 생성자
// 	public PageMaker(int total, PagingDTO paging) {
// 		super();
// 		this.total = total;
// 		this.paging = paging;
		
// 		int tempEnd = (int)(Math.ceil(paging.getPage()/10.0))*paging.getAmount();
// 		this.start = tempEnd - (paging.getAmount() - 1);
// 		this.prev = this.start != 1; //1이 아닐 때만 true
		
// 		int realEnd = (int)Math.ceil(total/(double)paging.getAmount());

// 		log.info("tempEnd: " + tempEnd + "realEnd: " + realEnd);
	
// 		this.end  = tempEnd > realEnd ? realEnd : tempEnd;
		
// 		this.next = this.end * paging.getAmount() < total;
// 	}

// }


@Getter
@ToString
public class PageMaker {
	
	private int start;
	private int end;
	private boolean prev, next;
	private int total;
	
	private PagingDTO paging;
	private SearchDTO searchDTO;
	
	//객체를 만드는 제약조건 생성자
	public PageMaker(int total, PagingDTO paging) {
		super();
		this.total = total;
		this.paging = paging;
		
		int tempEnd = (int)(Math.ceil(paging.getPage()/10.0))*10; //10
		this.start = tempEnd - 9;
		this.prev = this.start != 1; //1이 아닐 때만 true
		
		int realEnd = (total/paging.getAmount()) + 1; // 
		
		this.end  = tempEnd > realEnd ? realEnd : tempEnd;
		
		this.next = this.end * paging.getAmount() < total;
	}

	// 코드 검색 페이지 메이커
	public PageMaker(int total, SearchDTO searchDTO) {

		super();

		this.total = total;
		this.searchDTO = searchDTO;
		
		int tempEnd = (int)(Math.ceil(searchDTO.getPage()/10.0))*10; //10

		this.start = tempEnd - 9;

		this.prev = this.start != 1; //1이 아닐 때만 true
		
		int realEnd = (int)(Math.ceil(total/(double)searchDTO.getAmount()));
		
		this.end = Math.min(realEnd, tempEnd);
		
		this.next = this.end * searchDTO.getAmount() < total;
	}

}
package org.ezcode.demo.domain;

import lombok.Data;

/**
 * SearchDTO
 */
@Data
public class SearchDTO {

	private int page;
    private int amount;

	private Integer pno;
    private String keyword;
	private String lang;
    private String siteLink;
	private String comment;
	private String path;

	public SearchDTO(int page, int amount){
        this.page = page;
        this.amount = amount;
	}
	
	public SearchDTO() {
        this.page = 1;
		this.amount = 10;
	}
	
	public int getSkip(){
        return (page - 1) * amount;
    }
	
	public String[] getKeywords() {
		if(keyword == null || keyword.trim().length() == 0) {
			return null;
		}
		
		return keyword.split(" ");
	}

	public String[] getLangs() {
		if(lang == null || lang.trim().length() == 0) {
			return null;
		}
		
		return lang.split(" ");
    }
}
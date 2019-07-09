package com.model2.mvc.service.domain;

public class Tour {
	
	private String tourId;
	private String tourName;
	private String tourThumb;
	private String tourShortDesc;
	private String tourLoc;
	private String viewCount;
	
	private String keyword;
	
	
	//Ãß°¡ÇÔ 
	private String pageNum;
	
	
	private boolean nextBtn;
	
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
	
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getTourName() {
		return tourName;
	}
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}
	public boolean isNextBtn() {
		return nextBtn;
	}
	public void setNextBtn(boolean nextBtn) {
		this.nextBtn = nextBtn;
	}
	public String getViewCount() {
		return viewCount;
	}
	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}
	public String getTourId() {
		return tourId;
	}
	public void setTourId(String tourId) {
		this.tourId = tourId;
	}
	public String getTourThumb() {
		return tourThumb;
	}
	public void setTourThumb(String tourThumb) {
		this.tourThumb = tourThumb;
	}
	public String getTourShortDesc() {
		return tourShortDesc;
	}
	public void setTourShortDesc(String tourShortDesc) {
		this.tourShortDesc = tourShortDesc;
	}
	public String getTourLoc() {
		return tourLoc;
	}
	public void setTourLoc(String tourLoc) {
		this.tourLoc = tourLoc;
	}
	
	

}

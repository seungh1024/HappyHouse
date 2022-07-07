package com.ssafy.happyhouse.model.dto;

public class GugunCode {
	private String gugunCode;
	private String gugunName;
	
	public GugunCode() {};
	
	public GugunCode(String gugunCode, String gugunName) {
		super();
		this.gugunCode = gugunCode;
		this.gugunName = gugunName;
	}
	
	public String getGugunCode() {
		return gugunCode;
	}
	public void setGugunCode(String gugunCode) {
		this.gugunCode = gugunCode;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	@Override
	public String toString() {
		return "guguncode [gugunCode=" + gugunCode + ", gugunName=" + gugunName + "]";
	}
	
	
}

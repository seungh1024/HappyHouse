package com.ssafy.happyhouse.model.dto;

public class SidoCode {
	private String sidoCode;
	private String sidoName;
	
	public SidoCode() {};
	
	public SidoCode(String sidoCode, String sidoName) {
		super();
		this.sidoCode = sidoCode;
		this.sidoName = sidoName;
	}
	
	public String getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(String sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	
	@Override
	public String toString() {
		return "sidocode [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}
}

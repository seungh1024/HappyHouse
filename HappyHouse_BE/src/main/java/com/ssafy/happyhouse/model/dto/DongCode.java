package com.ssafy.happyhouse.model.dto;

public class DongCode {
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public DongCode() {};
	
	public DongCode(String dongCode, String dongName, String sidoName, String gugunName) {
		super();
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
	}
	
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}

	@Override
	public String toString() {
		return "Dongcode [dongCode=" + dongCode + ", dongName=" + dongName + ", sidoName=" + sidoName + ", gugunName="
				+ gugunName + "]";
	}
	
	
	
}

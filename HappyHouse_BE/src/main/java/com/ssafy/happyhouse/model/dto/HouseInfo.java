package com.ssafy.happyhouse.model.dto;

public class HouseInfo {
	private long aptCode;
	private String apartmentName;
	private String dongCode;
	private String dong;
	private int buildYear;
	private String jibun;
	private String lat;
	private String lng;
	private String img;
	private String sidoName;
	
	public HouseInfo() {};
	
	
	
	



	public HouseInfo(long aptCode, String apartmentName, String dongCode, String dong, int buildYear, String jibun,
			String lat, String lng, String img, String sidoName) {
		super();
		this.aptCode = aptCode;
		this.apartmentName = apartmentName;
		this.dongCode = dongCode;
		this.dong = dong;
		this.buildYear = buildYear;
		this.jibun = jibun;
		this.lat = lat;
		this.lng = lng;
		this.img = img;
		this.sidoName = sidoName;
	}

	
	
	public String getSidoName() {
		return sidoName;
	}

	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}

	public long getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	
	






	public String getApartmentName() {
		return apartmentName;
	}







	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}







	public String getDong() {
		return dong;
	}







	public void setDong(String dong) {
		this.dong = dong;
	}







	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}







	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	
	public int getBuildYear() {
		return buildYear;
	}
	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}
	public String getJibun() {
		return jibun;
	}
	public void setJibun(String jibun) {
		this.jibun = jibun;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}







	@Override
	public String toString() {
		return "HouseInfo [aptCode=" + aptCode + ", APARTMENTNAME=" + apartmentName + ", dongCode=" + dongCode
				+ ", dong=" + dong + ", buildYear=" + buildYear + ", jibun=" + jibun + ", lat=" + lat + ", lng="
				+ lng + ", img=" + img + ", sidoName=" + sidoName + "]";
	}

	
	
	
	
}

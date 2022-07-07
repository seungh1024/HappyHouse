package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.HouseDeal;
import com.ssafy.happyhouse.model.dto.HouseInfo;

public interface HouseMapper {
	public List<HouseInfo> houseInfo(String dongCode);//아파트 정보 -> 중복없는 데이터
	public List<HouseInfo> searchHouse(@Param("aptName")String aptName, @Param("dongCode") String dongCode); //아파트 이름으로 검색
	public List<HouseInfo> allInfo(String aptName);
	
	public List<HouseDeal> dealList(long aptCode); //아파트 거래정보(상세정보)
}

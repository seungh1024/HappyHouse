package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.GugunCode;
import com.ssafy.happyhouse.model.dto.SidoCode;

public interface CodeMapper {
	public List<SidoCode> sidoList();
	public List<GugunCode> gugunList(String code);
	public List<DongCode> dongList(String gugunCode);
}

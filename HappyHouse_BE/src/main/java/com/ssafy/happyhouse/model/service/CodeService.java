package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.GugunCode;
import com.ssafy.happyhouse.model.dto.SidoCode;
import com.ssafy.happyhouse.model.mapper.CodeMapper;

@Service
public class CodeService {
	
	@Autowired
	private CodeMapper scdao;
	
	public List<SidoCode> sidoList(){
		return scdao.sidoList();
	}
	
	public List<GugunCode> gugunList(String code){
		code = code+"%";
		return scdao.gugunList(code);
	}
	
	public List<DongCode> dongList(String gugunCode){
		gugunCode = "%"+gugunCode+"%";
		return scdao.dongList(gugunCode);
	}
}

package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.DongCode;
import com.ssafy.happyhouse.model.dto.GugunCode;
import com.ssafy.happyhouse.model.dto.SidoCode;
import com.ssafy.happyhouse.model.service.CodeService;

@CrossOrigin("*")
@RestController
@RequestMapping("/code")
public class CodeController {
	
	@Autowired
	private CodeService cservice;
	
	@GetMapping("/sido")
	public ResponseEntity<?> sidoList(){
//		System.out.println(cservice.sidoList());
		return new ResponseEntity<List<SidoCode>>(cservice.sidoList(),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/gugun/{code}")
	public ResponseEntity<?> gugunList(@PathVariable("code") String code){
		return new ResponseEntity<List<GugunCode>>(cservice.gugunList(code),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/dong/{gugunCode}")
	public ResponseEntity<?> dongList(@PathVariable("gugunCode") String gugunCode){
		return new ResponseEntity<List<DongCode>>(cservice.dongList(gugunCode),HttpStatus.ACCEPTED);
	}
}

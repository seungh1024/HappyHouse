//package com.ssafy.happyhouse.controller;
//
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.ssafy.happyhouse.config.security.JwtTokenProvider;
//import com.ssafy.happyhouse.model.dto.UserInfo;
//import com.ssafy.happyhouse.model.service.UserService;
//
//@RestController
//@RequestMapping("/jwt")
//public class JwtUserController {
//	private static final String SUCCESS = "success";
//	private static final String FAIL = "fail";
//	
////	@Autowired
////	private PasswordEncoder passwordEncoder;
////	@Autowired
////	private JwtTokenProvider jwtTokenProvider;
//	@Autowired
//	private UserService uService;
//	@Autowired
//	private JwtTokenProvider jwt;
//	
//	// 회원가입
//    @PostMapping("/login")
//    public ResponseEntity<?> join(@RequestBody UserInfo user) {
//        Map<String,Object> resultMap = new HashMap<>();
//        HttpStatus status = null;
//        try {
//        	UserInfo loginUser = uService.login(user.getId(), user.getPw());
//        	if(loginUser != null) {
//        		String token = jwt.createToken(user.getId(), user.getName());
//        		System.out.println(token);
//        		resultMap.put("token",token);
//        		resultMap.put("message", SUCCESS);
//        		status = HttpStatus.ACCEPTED;
//        	}else {
//        		resultMap.put("message", FAIL);
//        		status = HttpStatus.ACCEPTED;
//        	}
//        }catch (Exception e) {
//			resultMap.put("message", e.getMessage());
//			status = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//        return new ResponseEntity<Map<String,Object>>(resultMap,status);
//    }
//	
//}

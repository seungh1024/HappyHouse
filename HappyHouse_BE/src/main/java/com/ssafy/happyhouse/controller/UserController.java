package com.ssafy.happyhouse.controller;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AuthDto;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.service.AuthService;
import com.ssafy.happyhouse.model.service.EmailService;
import com.ssafy.happyhouse.model.service.UserService;
import com.ssafy.happyhouse.response.ApiResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

//@RestController
@Api(tags = "Auth / 로그인")
@CrossOrigin("*")
@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private UserService uservice;

	private final AuthService authService;

	@Autowired
	private EmailService eservice;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);





	@PostMapping("/mail")
	    @ResponseBody
	    public void emailConfirm(@RequestBody String email) throws Exception {
	        logger.info("post emailConfirm");
	
	    System.out.println("email : " + email);
	    eservice.sendSimpleMessage(email);
	}
	
	@PostMapping("/verifyCode")
	@ResponseBody
	public int verifyCode(@RequestBody String code) {
	    logger.info("Post verifyCode");
	
	    int result = 0;
	    code = code.substring(1, code.length() - 1);
	    System.out.println("code : " + code);
	    System.out.println("정답 code : " + EmailService.ePw);
	    System.out.println("code match : " + EmailService.ePw.equals(code));
	    if (EmailService.ePw.equals(code)) {
	        result = 1;
	    }
	    System.out.println("Result: " + result);
	    return result;
	}

	@GetMapping("/login")
	public String login() {
		return "html/login_form";
	}

	// rest - login
	@PostMapping("/login")
	@ResponseBody
	@ApiOperation(value = "로그인")
	public ApiResponse login(@RequestBody @Valid AuthDto.LoginDto loginDto) throws Exception {
		System.out.println("======login======");
		return authService.login(loginDto);
	}

	@PostMapping("/refreshToken")
	@ApiOperation(value = "새로운 토큰 발급")
	public ApiResponse newAccessToken(@RequestBody @Valid AuthDto.GetNewAccessTokenDto getNewAccessTokenDTO,
			HttpServletRequest request) {
		System.out.println("refreshToken start!");
		System.out.println(getNewAccessTokenDTO);
		return authService.newAccessToken(getNewAccessTokenDTO, request);
	}

	@PostMapping("/info")
	@ResponseBody
	public ResponseEntity<?> getInfo(@RequestBody int idx) throws Exception {
		User user = uservice.getInfo(idx);
		System.out.println(user);
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			System.out.println(user + " fail");
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/register")
	public String register() {
		return "html/register_form";
	}

	// rest - regist
	@PostMapping("/register")
	@ResponseBody
	public ResponseEntity<?> register(@RequestBody User user) throws Exception {
		System.out.println("=====================" + user);
		if (uservice.emailCheck(user.getEmail()) == 0 && uservice.register(user)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/findpw")
	public String findpw() {
		return "html/find_pw";
	}

	// rest - find info
	@PostMapping("/findpw")
	@ResponseBody
	public ResponseEntity<?> findpw(@RequestBody User user) throws Exception {
		System.out.println("find email: " + user.getEmail());
		User info = uservice.findPw(user.getEmail(), user.getName());
		if (info != null) {
			return new ResponseEntity<User>(info, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/info")
	public String info() {
		return "html/userInfo";
	}

	@GetMapping("/modify")
	public String modify() {
		return "html/modify_info";
	}

	// rest - modify user
	@PutMapping("/modify")
	@ResponseBody
	public ResponseEntity<?> modify(@RequestBody User user) throws Exception {
		System.out.println("modify" + user);
		if (uservice.update(user.getPassword(), user.getName(), user.getEmail())) {
			User updateUser = uservice.info(user.getEmail());
			return new ResponseEntity<User>(updateUser, HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/delete")
	public String delete() {
		return "html/delete_user";
	}

	// rest delete user
	@DeleteMapping("/delete/{email}")
	@ResponseBody
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception {
		System.out.println("delete start" + email);
		if (uservice.delete(email)) {
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
		return new ResponseEntity<String>("fail", HttpStatus.OK);
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

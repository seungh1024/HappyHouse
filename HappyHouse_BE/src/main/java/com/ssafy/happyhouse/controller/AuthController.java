package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AuthDto;
import com.ssafy.happyhouse.model.service.AuthService;
import com.ssafy.happyhouse.response.ApiResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Auth / 로그인")
@RequestMapping("/app/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @ApiOperation(value="로그인")
    public ApiResponse login(@RequestBody @Valid AuthDto.LoginDto loginDto){
    	System.out.println(hashPassword("ssafy"));
        
        
    	System.out.println("login start!!");
        return authService.login(loginDto);
    }
    @PostMapping("/refreshToken")
    @ApiOperation(value="새로운 토큰 발급")
    public ApiResponse newAccessToken(@RequestBody @Valid AuthDto.GetNewAccessTokenDto getNewAccessTokenDTO, HttpServletRequest request) {
        return authService.newAccessToken(getNewAccessTokenDTO, request);
    }
    
    private String hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}

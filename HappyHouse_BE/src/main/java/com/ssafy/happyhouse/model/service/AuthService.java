package com.ssafy.happyhouse.model.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.config.security.JwtProvider;
import com.ssafy.happyhouse.exception.AuthenticationException;
import com.ssafy.happyhouse.exception.ErrorCode;
import com.ssafy.happyhouse.model.dto.AuthDto;
import com.ssafy.happyhouse.model.dto.RefreshToken;
import com.ssafy.happyhouse.model.mapper.AuthMapper;
import com.ssafy.happyhouse.response.ApiResponse;
import com.ssafy.happyhouse.response.ResponseMap;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtProvider jwtProvider;
	private final AuthenticationManager authenticationManager;
	private final AuthMapper authMapper;

	@Autowired
	private UserService uservice;

	public ApiResponse login(AuthDto.LoginDto loginDto) {
		ResponseMap result = new ResponseMap();

		try {
			System.out.println(loginDto.getEmail() + " " + loginDto.getPassword());
			System.out.println(uservice.emailCheck(loginDto.getEmail()));

			if (uservice.emailCheck(loginDto.getEmail()) != 1) {
				System.out.println("here");
				throw new AuthenticationException(ErrorCode.UsernameOrPasswordNotFoundException);
			}

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

			Map createToken = createTokenReturn(loginDto);
			result.setResponseData("accessToken", createToken.get("accessToken"));
			result.setResponseData("refreshIdx", createToken.get("refreshIdx"));
		} catch (Exception e) {
			e.printStackTrace();
			if (authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()))
					.getCredentials() == null) {
			}
			throw new AuthenticationException(ErrorCode.UsernameOrPasswordNotFoundException);
		}
		return result;
	}

	public ApiResponse newAccessToken(AuthDto.GetNewAccessTokenDto getNewAccessTokenDTO, HttpServletRequest request) {
		ResponseMap result = new ResponseMap();
		String refreshToken = authMapper.findRefreshTokenByIdx(getNewAccessTokenDTO.getRefreshIdx());
		// AccessToken은 만료되었지만 RefreshToken은 만료되지 않은 경우
		if (jwtProvider.validateJwtToken(request, refreshToken)) {
			System.out.println("not yet!!");
			String email = jwtProvider.getUserInfo(refreshToken);
			AuthDto.LoginDto loginDTO = new AuthDto.LoginDto();
			loginDTO.setEmail(email);
			Map createToken = createTokenReturn(loginDTO);
			result.setResponseData("accessToken", createToken.get("accessToken"));
			result.setResponseData("refreshIdx", createToken.get("refreshIdx"));
		} else {
			System.out.println("herere");
			// RefreshToken 또한 만료된 경우는 로그인을 다시 진행해야 한다.
			result.setResponseData("code", ErrorCode.ReLogin.getCode());
			result.setResponseData("message", ErrorCode.ReLogin.getMessage());
			result.setResponseData("HttpStatus", ErrorCode.ReLogin.getStatus());

//			445, "모든 토큰이 만료되었습니다. 다시 로그인해주세요.", HttpStatus.UNAUTHORIZED
//			result.setResponseData("code", 445);
//			result.setResponseData("message", "모든 토큰이 만료되었습니다. 다시 로그인해주세요.");
//			result.setResponseData("HttpStatus", HttpStatus.UNAUTHORIZED);
		}
		System.out.println("rrrr: " + result);
		return result;
	}

	// 토큰 생성해서 반환
	private Map createTokenReturn(AuthDto.LoginDto loginDto) {
		System.out.println("create token start!!");
		Map result = new HashMap();

		String accessToken = jwtProvider.createAccessToken(loginDto);
		String refreshToken = jwtProvider.createRefreshToken(loginDto).get("refreshToken");
		String refreshTokenExpirationAt = jwtProvider.createRefreshToken(loginDto).get("refreshTokenExpirationAt");

		RefreshToken insertRefreshToken = RefreshToken.builder().user_email(loginDto.getEmail())
				.access_token(accessToken).refresh_token(refreshToken)
				.refresh_token_expiration_at(refreshTokenExpirationAt).build();

		authMapper.insertOrUpdateRefreshToken(insertRefreshToken);

		result.put("accessToken", accessToken);
		result.put("refreshIdx", insertRefreshToken.getIdx());

		System.out.println("result: " + result);

		return result;
	}
}

package com.ssafy.happyhouse.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.exception.ErrorCode;

@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
	@SuppressWarnings("unused")
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		String exception = (String) request.getAttribute("exception");
		ErrorCode errorCode;

		String e = authException.getMessage();
//		System.out.println((String) ((ServletRequest) response).getAttribute("exception"));
		System.out.println("exception " + exception);
		System.out.println("hihi " + authException.toString());
		System.out.println("!!!!!!!!!!!   " + e + " " + authException.getMessage());
		System.out.println(request);
		// 아이디와 비밀번호가 일치하지 않을 경우
		if (e == null) {
			System.out.println("why??????!");
			errorCode = ErrorCode.UsernameOrPasswordNotFoundException;
			setResponse(response, errorCode);
			return;
		}

//		if (e.equals("Bad credentials")) {
//			errorCode = ErrorCode.UsernameOrPasswordNotFoundException;
//			setResponse(response, errorCode);
//			return;
//		}

		if (authException instanceof BadCredentialsException) {
			System.out.println("Bad??????!");
			errorCode = ErrorCode.UsernameOrPasswordNotFoundException;
			setResponse(response, errorCode);
			return;
		}
		
		if (authException instanceof InsufficientAuthenticationException) {
			
			System.out.println("Auth");
			errorCode = ErrorCode.UNAUTHORIZEDException;
			setResponse(response, errorCode);
			return;
		}

		// 토큰이 없는 경우 예외처리
		if (exception == null) {
			System.out.println("not token");
			errorCode = ErrorCode.UNAUTHORIZEDException;
			setResponse(response, errorCode);
			return;
		}
		// 토큰이 만료된 경우
		if (exception.equals("ExpiredJwtException")) {
			System.out.println("expiredJ");
			errorCode = ErrorCode.ExpiredJwtException;
			setResponse(response, errorCode);
			return;
		}

	}

	private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
		JSONObject json = new JSONObject();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		json.put("code", errorCode.getCode());
		json.put("message", errorCode.getMessage());
		response.getWriter().print(json);

	}
}

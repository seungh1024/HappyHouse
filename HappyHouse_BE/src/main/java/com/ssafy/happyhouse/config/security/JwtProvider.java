package com.ssafy.happyhouse.config.security;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;

import com.ssafy.happyhouse.model.dto.AuthDto;
import com.ssafy.happyhouse.model.service.CustomUserDetailService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class JwtProvider {
	private final String secretKey = "fa9b8ec6-ca24-455b-be5d-de8000c2c4bd";
	private final long accessExpireTime = 1 * 60 * 5000L; // 1분
	private final long refreshExpireTime = 1 * 60 * 5000L; // 2분
	private final CustomUserDetailService customUserDetailService;

	public String createAccessToken(AuthDto.LoginDto loginDto) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("type", "token");

		Map<String, Object> payloads = new HashMap<>();
		payloads.put("email", loginDto.getEmail());

		Date expiration = new Date();
		expiration.setTime(expiration.getTime() + accessExpireTime);

		String jwt = Jwts.builder().setHeader(headers).setClaims(payloads).setSubject("user").setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();

		return jwt;
	}

	public Map<String, String> createRefreshToken(AuthDto.LoginDto loginDto) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("type", "token");

		Map<String, Object> payloads = new HashMap<>();
		payloads.put("email", loginDto.getEmail());

		Date expiration = new Date();
		expiration.setTime(expiration.getTime() + refreshExpireTime);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		String refreshTokenExpirationAt = simpleDateFormat.format(expiration);

		String jwt = Jwts.builder().setHeader(headers).setClaims(payloads).setSubject("user").setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS256, secretKey).compact();

		Map<String, String> result = new HashMap<>();
		result.put("refreshToken", jwt);
		result.put("refreshTokenExpirationAt", refreshTokenExpirationAt);
		return result;
	}

	public Authentication getAuthentication(String token) {
		UserDetails userDetails = customUserDetailService.loadUserByUsername(this.getUserInfo(token));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUserInfo(String token) {
		return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("email");
	}

	public String resolveToken(HttpServletRequest request) {
		System.out.println("resolve token: " + request.getHeader("token"));
		return request.getHeader("token");
	}

	public boolean validateJwtToken(ServletRequest request, String authToken) {
		try {
			Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			request.setAttribute("exception", "MalformedJwtException");
		} catch (ExpiredJwtException e) {
			request.setAttribute("exception", "ExpiredJwtException");
		} catch (UnsupportedJwtException e) {
			request.setAttribute("exception", "UnsupportedJwtException");
		} catch (IllegalArgumentException e) {
			request.setAttribute("exception", "IllegalArgumentException");
		}
		return false;
	}

}

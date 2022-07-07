package com.ssafy.happyhouse.exception;

public class AuthenticationException extends RuntimeException {

	private final ErrorCode errorCode;

	public AuthenticationException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}
}

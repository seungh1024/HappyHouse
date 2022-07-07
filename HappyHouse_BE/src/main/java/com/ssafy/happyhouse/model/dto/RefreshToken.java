package com.ssafy.happyhouse.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RefreshToken {
	private long idx;
	private String user_email;
	private String access_token;
	private String refresh_token;
	private String refresh_token_expiration_at;
}
package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.exception.AuthenticationException;
import com.ssafy.happyhouse.exception.ErrorCode;
import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.mapper.AuthMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
	@Autowired
	private final AuthMapper authMapper;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = authMapper.findByEmail(username);

		if (user == null) {
			throw new AuthenticationException(ErrorCode.UsernameOrPasswordNotFoundException);
		}

		return user;
	}
}

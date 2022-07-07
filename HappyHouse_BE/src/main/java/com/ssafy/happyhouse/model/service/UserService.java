package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper udao;

	public UserInfo login(String id, String pw) throws Exception {
		System.out.println(id);
		return udao.login(id, pw);
	}

	public boolean register(User user) throws Exception {
		user.setPassword(hashPassword(user.getPassword()));
		if (udao.regist(user) > 0)
			return true;
		return false;
	}

	public boolean update(String password, String name, String email) throws Exception {
		if (udao.update(hashPassword(password), name, email) > 0)
			return true;
		return false;
	}

	public boolean delete(String email) throws Exception {
		if (udao.delete(email) > 0)
			return true;
		return false;
	}

	public int emailCheck(String email) throws Exception {
		return udao.emailCheck(email);
	}

	public User info(String email) throws Exception {
		return udao.info(email);
	}
	
	public User findPw(String email, String name) throws Exception {
		return udao.findPw(email, name);
	}

	private String hashPassword(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	public User getInfo(int idx) {
		return udao.getInfo(idx);
	}
}

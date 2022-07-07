package com.ssafy.happyhouse.model.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.User;
import com.ssafy.happyhouse.model.dto.UserInfo;

@Mapper
public interface UserMapper {
	public int regist(User user) throws Exception; // 회원가입

	public UserInfo login(@Param("id") String id, @Param("pw") String pw) throws Exception; // 로그인

	public int update(@Param("password") String password, @Param("name") String name, @Param("email") String email)
			throws Exception;

	public int delete(String email) throws Exception;// 회원탈퇴

	public int emailCheck(String email) throws Exception;

	public User info(@Param("email") String email) throws Exception;

	public User findPw(@Param("email") String email, @Param("name") String name) throws Exception;

	public User getInfo(int idx);
}

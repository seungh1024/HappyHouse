package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.Comment;

public interface CommentMapper {

	List<Comment> list(int articleno);

	int create(Comment comment);

	int modify(Comment comment);

	int delete(int commentNo);
	
}

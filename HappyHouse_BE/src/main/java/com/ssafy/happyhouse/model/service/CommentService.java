package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.Comment;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentService {

	@Autowired
	private CommentMapper commentMapper;
	
	public List<Comment> list(int articleno) {
		return commentMapper.list(articleno);
	}

	public boolean create(Comment comment) {
		return commentMapper.create(comment) == 1;
	}

	public boolean modify(Comment comment) {
		return commentMapper.modify(comment) == 1;
	}

	public boolean delete(int commentNo) {
		return commentMapper.delete(commentNo) == 1;
	}

}

package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.Board;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	public List<Board> retrieveBoard(int start, int end) {
		return boardMapper.selectBoard(start, end);
	}

	public boolean writeBoard(Board board) {
		return boardMapper.insertBoard(board) == 1;
	}

	public Board detailBoard(int articleno) {
		return boardMapper.selectBoardByNo(articleno);
	}

	@Transactional
	public boolean updateBoard(Board board) {
		return boardMapper.updateBoard(board) == 1;
	}

	@Transactional
	public boolean deleteBoard(int articleno) {
		return boardMapper.deleteBoard(articleno) == 1;
	}

	public int getNumberofBoard() {
		return boardMapper.getNumberofBoard();
	}
}
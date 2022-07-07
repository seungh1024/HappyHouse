package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.Board;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoard(@Param("start") int start, @Param("end") int end);

	public Board selectBoardByNo(int articleno);

	public int insertBoard(Board board);

	public int updateBoard(Board board);

	public int deleteBoard(int articleno);

	public int getNumberofBoard();
}
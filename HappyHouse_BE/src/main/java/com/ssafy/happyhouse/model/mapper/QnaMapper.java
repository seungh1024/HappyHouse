package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.happyhouse.model.dto.Qna;

@Mapper
public interface QnaMapper {
	public List<Qna> selectQna(@Param("start") int start, @Param("end") int end);

	public Qna selectQnaByNo(int articleno);

	public int insertQna(Qna qna);

	public int updateQna(Qna qna);

	public int deleteComment(int articleno);
	public int deleteQna(int articleno);

	public int getNumberofQna();
}
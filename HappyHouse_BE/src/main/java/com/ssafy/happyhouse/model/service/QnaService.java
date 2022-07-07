package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.Qna;
import com.ssafy.happyhouse.model.mapper.QnaMapper;

@Service
public class QnaService {

	@Autowired
	private QnaMapper qnaMapper;

	public List<Qna> retrieveQna(int start, int end) {
		return qnaMapper.selectQna(start, end);
	}

	public boolean writeQna(Qna qna) {
		return qnaMapper.insertQna(qna) == 1;
	}

	public Qna detailQna(int articleno) {
		return qnaMapper.selectQnaByNo(articleno);
	}

	@Transactional
	public boolean updateQna(Qna qna) {
		return qnaMapper.updateQna(qna) == 1;
	}

	@Transactional
	public boolean deleteQna(int articleno) {
		qnaMapper.deleteComment(articleno);
		return qnaMapper.deleteQna(articleno) == 1;
	}

	public int getNumberofQna() {
		return qnaMapper.getNumberofQna();
	}
}
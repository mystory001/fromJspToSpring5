package com.mystory001.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.mystory001.dao.ReboardDAO;
import com.mystory001.domain.BoardDTO;
import com.mystory001.domain.PageDTO;

@Service
public class ReboardService {
	
	@Inject
	private ReboardDAO reboardDAO;
	
	public List<BoardDTO> getBoardList(PageDTO pageDTO){
		System.out.println("ReboardService getBoardList()");
		//currentPage, pageSize를 이용해서 시작 행(startRow) 번호, 끝나는 행(endRow) 구하기
		int currentPage = pageDTO.getCurrentPage();
		int pageSize = pageDTO.getPageSize();
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = startRow + pageSize - 1;
		
		pageDTO.setStartRow(startRow - 1); //limit 시작행 - 1, 개수
		pageDTO.setEndRow(endRow);
		
		return reboardDAO.getBoardList(pageDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("ReboardService getBoardCount()");
		return reboardDAO.getBoardCount(pageDTO);
	}

	public void insertBoard(Map<String, Object> param) {
		System.out.println("ReboardService insertBoard()");
		
		if(reboardDAO.getMaxNum()==null) {//글 없는 경우
			param.put("num", 1);
			param.put("re_ref", 1);
		} else {
			param.put("num", reboardDAO.getMaxNum()+1);
			param.put("re_ref", reboardDAO.getMaxNum()+1); //답글 관련 기준 글 번호 → 그룹 번호 일치
		}
		
		param.put("readCount", 0);
		param.put("date", new Timestamp(System.currentTimeMillis()));
		param.put("re_lev", 0); //들여쓰기 0
		param.put("re_seq", 0); //순서 값
		
		
		reboardDAO.insertBoard(param);
	}

}

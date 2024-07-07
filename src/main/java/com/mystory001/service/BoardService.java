package com.mystory001.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mystory001.dao.BoardDAO;
import com.mystory001.domain.BoardDTO;
import com.mystory001.domain.PageDTO;

@Service
public class BoardService {
	
	@Inject
	private BoardDAO boardDAO;

	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardService insertBoard()");
		boardDTO.setNum(0);
		boardDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		boardDAO.insertBoard(boardDTO);
	}

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardService getBoardList()");
		//시작하는 행 번호 구하기(currentPage,pageSize를 이용)
		int currentPage = pageDTO.getCurrentPage();
		int pageSize = pageDTO.getPageSize();
		int startRow = (currentPage - 1) * pageSize + 1;
		//끝나는 행 번호 구하기
		int endRow = startRow + pageSize - 1;
		
		//pageDTO에 저장
		//boardMapper → limit(한계까지 자르기) 시작행 - 1, 개수
		pageDTO.setStartRow(startRow - 1);
		pageDTO.setEndRow(endRow);
		
		return boardDAO.getBoardList(pageDTO);
	}

	public BoardDTO getBoard(BoardDTO boardDTO) {
		System.out.println("BoardService getBoard()");
		
		return boardDAO.getBoard(boardDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("BoardService getBoardCount()");
		return boardDAO.getBoardCount(pageDTO);
	}
	
	public void updateReadCount(BoardDTO boardDTO) {
		System.out.println("BoardService updateReadCount()");
		int readCount = boardDTO.getReadCount() + 1;
		boardDTO.setReadCount(readCount);
		boardDAO.updateReadCount(boardDTO);
	}

	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService updateBoard()");
		boardDAO.updateBoard(boardDTO);
		
	}

	public void deleteBoard(BoardDTO boardDTO) {
		System.out.println("BoardService deleteBoard()");
		boardDAO.deleteBoard(boardDTO);
	}

	public void fupdateBoard(BoardDTO boardDTO) {
		System.out.println("BoardService fupdateBoard()");
		boardDAO.fupdateBoard(boardDTO);
	}

}

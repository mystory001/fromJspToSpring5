package com.mystory001.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mystory001.domain.BoardDTO;
import com.mystory001.domain.PageDTO;

@Repository
public class BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.mystory001.mappers.boardMapper";

	public void insertBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO insertBoard()");
		
		sqlSession.insert(namespace+".insertBoard", boardDTO);
	}

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("BoardDAO getBoardList()");
		return sqlSession.selectList(namespace+".getBoardList", pageDTO);
	}
	
	public int getMaxNum() {
		System.out.println("BoardDAO getMaxNum()");
		return sqlSession.selectOne(namespace+".getMaxNum");
	}

	public BoardDTO getBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO getBoard()");
		return sqlSession.selectOne(namespace+".getBoard", boardDTO);
	}

	public void updateReadCount(BoardDTO boardDTO) {
		System.out.println("BoardDAO updateReadCount()");
		sqlSession.update(namespace+".updateReadCount", boardDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("BoardDAO getBoardCount()");
		return sqlSession.selectOne(namespace+".getBoardCount", pageDTO);
	}

	public void updateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO updateBoard()");
		sqlSession.update(namespace+".updateBoard", boardDTO);
	}

	public void deleteBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO deleteBoard()");
		sqlSession.delete(namespace+".deleteBoard", boardDTO);
	}

	public void fupdateBoard(BoardDTO boardDTO) {
		System.out.println("BoardDAO fupdateBoard()");
		sqlSession.update(namespace+".fupdateBoard", boardDTO);
	}


}

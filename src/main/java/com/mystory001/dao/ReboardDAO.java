package com.mystory001.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mystory001.domain.BoardDTO;
import com.mystory001.domain.PageDTO;

@Repository
public class ReboardDAO {
	
	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.mystory001.mappers.reboardMapper";

	public List<BoardDTO> getBoardList(PageDTO pageDTO) {
		System.out.println("ReboardDAO getBoardList()");
		return sqlSession.selectList(namespace+".getBoardList", pageDTO);
	}

	public int getBoardCount(PageDTO pageDTO) {
		System.out.println("ReboardDAO getBoardCount()");
		return sqlSession.selectOne(namespace+".getBoardCount", pageDTO);
	}

	public Integer getMaxNum() {
		System.out.println("ReboardDAO getMaxNum()");
		return sqlSession.selectOne(namespace+".getMaxNum");
	}

	public void insertBoard(Map<String, Object> param) {
		System.out.println("ReboardDAO insertBoard");
		System.out.println(param);
		sqlSession.insert(namespace+".insertBoard", param);
	}

}

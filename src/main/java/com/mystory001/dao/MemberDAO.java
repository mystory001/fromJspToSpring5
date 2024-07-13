package com.mystory001.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mystory001.domain.MemberDTO;

@Repository
public class MemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "com.mystory001.mappers.memberMapper";
	
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO insertMember");
		
		sqlSession.insert(namespace+".insertMember", memberDTO);
	}

	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberDAO userCheck()");
		
		return sqlSession.selectOne(namespace+".userCheck", memberDTO);
	}

	public MemberDTO getMember(String id) {
		System.out.println("MemberDTO getMember()");
		return sqlSession.selectOne(namespace+".getMember", id);
	}

	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberDAO updateMember()");
		sqlSession.update(namespace+".updateMember", memberDTO);
		
	}

	public List<MemberDTO> getMemberList() {
		System.out.println("MemberDAO getMemberList()");
		return sqlSession.selectList(namespace+".getMemberList");
	}

}

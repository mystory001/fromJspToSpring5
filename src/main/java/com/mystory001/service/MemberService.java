package com.mystory001.service;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mystory001.dao.MemberDAO;
import com.mystory001.domain.MemberDTO;

@Service
public class MemberService {
	
	@Inject
	private MemberDAO memberDAO;

	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberService insertMember()");
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		memberDAO.insertMember(memberDTO);
	}

	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberService userCheck()");
		return memberDAO.userCheck(memberDTO);
	}

	public MemberDTO getMember(String id) {
		System.out.println("MemberService getMember()");
		return memberDAO.getMember(id);
	}

	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberService updateMember()");
		memberDAO.updateMember(memberDTO);
	}

	public List<MemberDTO> getMemberList() {
		System.out.println("MemberService getMemberList()");
		return memberDAO.getMemberList();
	}
	

}

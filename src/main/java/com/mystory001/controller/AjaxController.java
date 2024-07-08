package com.mystory001.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mystory001.domain.BoardDTO;
import com.mystory001.domain.MemberDTO;
import com.mystory001.domain.PageDTO;
import com.mystory001.service.BoardService;
import com.mystory001.service.MemberService;

@ResponseBody 
/*
스프링 비동기처리 하는 경우 @ResponseBody, @RequestBody를 사용
클라이언트에서 서버로 통신하는 메시지를 요청(request)메시지, 서버에서 클라이언트로 통신하는 메시지를 응답(response)메시지라고 함.
웹에서 화면 전환 없이 이루어지는 동작들은 대부분 비동기 통신으로 이루어짐
비동기통신을 하기 위해서는 클라이언트 ↔ 서버로 요청/응답 메시지를 보낼 때 본문(body)에 데이터를 담아서 보내야함.
요청은 requestBody, 응답은 responseBody 
@ResponseBody 자바객체를 HTTP 요청의 바디내용으로 매핑하여 클라이언트로 전송
*/
public class AjaxController {
	
	@Inject
	private MemberService memberService;
	@Inject
	private BoardService boardService;
	
	@GetMapping("/member/idCheck")
	public ResponseEntity<String> idCheck(MemberDTO memberDTO, HttpServletResponse response) { //ResponseEntity httpEntity를 상속받는, 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스
		System.out.println("MemberController idCheck()");
		
		MemberDTO memberDTO2 = memberService.getMember(memberDTO.getId());
		String result = "";
		if(memberDTO2!=null) {
			result="iddup";
		} else {
			result="idok";
		}
		
		//return 이동할 주소 → AJAX 처리 return 출력 결과 반환 → 출력 결과 응답
		//출력 결과를 가지고 join.jsp로 이동(안정적) → ResponseEntity
		ResponseEntity<String> entity = new ResponseEntity<String>(result,HttpStatus.OK);
		return entity;
	}
	
	@GetMapping("/board/listjson")
	public ResponseEntity<List<BoardDTO>> listjson(){
		System.out.println("AjaxController listjson()");
		//한 화면에 보여줄 최근 글의 개수 5개
		int pageSize = 5;
		//현 페이이지 1페이지 설정
		int currentPage=1;
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPageSize(pageSize);
		pageDTO.setCurrentPage(currentPage);
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
		ResponseEntity<List<BoardDTO>> entity = new ResponseEntity<List<BoardDTO>>(boardList,HttpStatus.OK);
		return entity;
	}

}

package com.mystory001.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mystory001.domain.MemberDTO;
import com.mystory001.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@Inject
	private MemberService memberService;
	
	@GetMapping("/insert")
	public String insert() {
		System.out.println("MemberController insert()");
		
		return "member/join";
	}
	
	//post방식 → @PostMapping
	@PostMapping("/insertPro")
	public String insertPro(MemberDTO memberDTO) {
		System.out.println("MemberController insertPro()");
		
		//join.jsp에서 입력한 데이터 → request → MemberDTO memberDTO 변수에 전달 → DB 작업
		memberService.insertMember(memberDTO);
		
		//주소가 변경되며 login페이지로 이동
		return "redirect:/member/login";
	}
	
	@GetMapping("/login")
	public String login() {
		System.out.println("MemberController login()");
		
		return "member/login";
	}
	
	@PostMapping("/loginPro")
	public String loginPro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController loginPro()");
		
		//폼에서 입력받은 데이터가 존재하는지 확인
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		//폼에서 입력받은 데이터가 DB에 존재하면 메인페이지로, 존재하지 않으면 로그인페이지로 이동
		if(memberDTO2 != null) {
			session.setAttribute("id", memberDTO.getId());
			return "redirect:/member/main";
		} else {
			return "member/msg";
		}
	}
	
	@GetMapping("/main")
	public String main(MemberDTO memberDTO) {
		System.out.println("MemberController main()");
		
		return "main/main";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("MemberController logout()");
		
		session.invalidate();
		return "redirect:/member/main";
	}
	
	@GetMapping("/update")
	public String update(HttpSession session, Model model) {
		System.out.println("MemberController update()");
		//세션 "id"값을 가져와서 변수 String id에 저장
		String id = (String)session.getAttribute("id");
		MemberDTO memberDTO = memberService.getMember(id);
		model.addAttribute(memberDTO);
		return "member/update";
	}
	
	@PostMapping("/updatePro")
	public String updatePro(MemberDTO memberDTO) {
		System.out.println("MemberController updatePro()");
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		if(memberDTO2!=null) {
			memberService.updateMember(memberDTO);
			return "redirect:/member/main";
		} else {
			return "redirect:/member/update";
		}
	}
	
	@GetMapping("/idCheck")
	@ResponseBody
	public String idCheck(MemberDTO memberDTO, HttpServletResponse response) {
		System.out.println("MemberController idCheck()");
		
		MemberDTO memberDTO2 = memberService.getMember(memberDTO.getId());
		String result = "";
		if(memberDTO2!=null) {
			result="iddup";
		} else {
			result="idok";
		}
		return result;
	}
	
	
}

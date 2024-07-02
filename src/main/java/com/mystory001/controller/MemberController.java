package com.mystory001.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	@GetMapping("/insert")
	public String insert() {
		System.out.println("MemberController insert()");
		
		return "member/join";
	}
	
}

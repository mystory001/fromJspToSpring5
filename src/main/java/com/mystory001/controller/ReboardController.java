package com.mystory001.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mystory001.domain.BoardDTO;
import com.mystory001.domain.PageDTO;
import com.mystory001.service.ReboardService;

@Controller
@RequestMapping("/reboard/*")
public class ReboardController {
	
	@Inject
	private ReboardService reboardService;
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, PageDTO pageDTO, Model model) {
		System.out.println("ReboardController list()");
		String search = request.getParameter("search"); //검색어
		
		int pageSize = 10; //한 화면에 보여줄 글의 개수 설정
		String pageNum = request.getParameter("pageNum"); //pageNum에 파라미터 값 가져오기
		if(pageNum == null) { //pageNum값이 없으면 1로 설정
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum); //pageNum을 정수형으로 변경 후 현재 페이지 변수에 대입
		
		pageDTO.setSearch(search);
		pageDTO.setPageSize(pageSize);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList = reboardService.getBoardList(pageDTO);
		
		int count = reboardService.getBoardCount(pageDTO); //전체 글의 개수
		int pageBlock = 10; //한 화면에 보여줄 페이지 수 설정
		int startPage = (currentPage-1)/pageBlock*pageBlock+1; //한 화면에 보여줄 시작 페이지
		int endPage = startPage + pageBlock - 1; //한 화면에 보여줄 끝 페이지
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		if(endPage > pageCount) { //끝 페이지, 전체 페이지 수를 비교해서 끝 페이지가 클 경우 전체 페이지 수로 끝 페이지 설정
			endPage = pageCount;
		}
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		model.addAttribute("boardList",boardList);
		model.addAttribute("pageDTO", pageDTO);
		
		return "recenter/notice";
	}
	
	@GetMapping("/write")
	public String write() {
		System.out.println("ReboardController write()");
		return "recenter/write";
	}
	
	@PostMapping("/writePro")
	public String writePro(@RequestParam Map<String, Object> param) { //@RequestParam Servlet 요청 파라미터를 컨트롤러 메소드의 인자로 바인딩. HttpServletRequest 객체와 같은 역할. @RequestParam <K,V> valName
		System.out.println("ReboardController writePro()");
		System.out.println(param);
		
		reboardService.insertBoard(param);
		return "redirect:/reboard/list";
	}

}

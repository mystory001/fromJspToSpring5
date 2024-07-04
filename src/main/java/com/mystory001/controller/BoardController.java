package com.mystory001.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mystory001.domain.BoardDTO;
import com.mystory001.domain.PageDTO;
import com.mystory001.service.BoardService;

@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	@Inject
	private BoardService boardService;
	
	@GetMapping("/list")
	public String list(HttpServletRequest request, PageDTO pageDTO, Model model) {
		System.out.println("BoardController list()");
		
		//한 화면에 보여줄 글의 개수 설정
		int pageSize = 15;
		//pageNum에 파라미터 값을 가져오기
		String pageNum = request.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		
		//currentPage에 pageNum 정수형으로 변경
		int currentPage = Integer.parseInt(pageNum);
		
		//pageDTO에 저장
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);

		
		//페이징 작업
		//전체 글의 개수 구하기 int count = getBoardCount()
		int count = boardService.getBoardCount(pageDTO);
		//한 화면에 보여줄 페이지 개수 설정
		int pageBlock = 10;
		//한 화면에 보여줄 시작 페이지 구하기(1~10 → 1, 11~20 → 11,...)
		int startPage = (currentPage-1)/pageBlock*pageBlock+1;
		//한 화면에 보여줄 끝 페이지 구하기
		int endPage = startPage+pageBlock-1;
		//전체 페이지 개수 구하기
		int pageCount = count/pageSize+(count%pageSize==0?0:1);
		if(endPage>pageCount) {
			endPage=pageCount; //끝 페이지, 전체 페이지 수를 비교해서 → 끝 페이지가 크면 전체 페이지 수를 끝 페이지 변경
		}
		
		//pageDTO 저장
		pageDTO.setCount(count); //전체 글의 개수 ${pageDTO.count}
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);

		model.addAttribute("boardList",boardList);
		model.addAttribute("boardList",boardList);
		
		return "center/notice";
	}
	
	@GetMapping("/write")
	public String write() {
		System.out.println("BoardController write()");
		
		return "center/write";
	}
	
	@PostMapping("/writePro")
	public String writePro(BoardDTO boardDTO) {
		System.out.println("BoardController writePro()");
		
		boardService.insertBoard(boardDTO);
		return "redirect:/board/list";
	}
	
	@GetMapping("/content")
	public String context(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController context()");
		boardDTO = boardService.getBoard(boardDTO);
		//조회수 증가
		boardService.updateReadCount(boardDTO);
		
		model.addAttribute("boardDTO",boardDTO);
		return "center/content";
	}

}

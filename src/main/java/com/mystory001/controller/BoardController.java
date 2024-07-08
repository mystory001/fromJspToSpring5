package com.mystory001.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		
		//검색어 가져오기
		String search = request.getParameter("search");
		
		//한 화면에 보여줄 글의 개수 설정
		int pageSize = 5;
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
		pageDTO.setSearch(search);
		
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

		model.addAttribute("pageDTO",pageDTO);
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
		System.out.println("BoardController content()");
		boardDTO = boardService.getBoard(boardDTO);
		//조회수 증가
		boardService.updateReadCount(boardDTO);
		model.addAttribute("boardDTO",boardDTO);
		return "center/content";
	}
	
	@GetMapping("/update")
	public String update(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController update()");
		
		//board/update?num=
		//BoardDTO boardDTO 전달받으면 request에 담기 파라미터 값이 저장, 메소드 호출 model 저장
		boardDTO = boardService.getBoard(boardDTO);
		model.addAttribute("boardDTO",boardDTO);
		
		return "center/update";
	}
	
	@PostMapping("/updatePro")
	public String updatePro(BoardDTO boardDTO) {
		System.out.println("BoardController updatePro()");
		boardService.updateBoard(boardDTO);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete")
	public String delete(BoardDTO boardDTO) {
		System.out.println("BoardController delete()");
		boardService.deleteBoard(boardDTO);
		return "redirect:/board/list";
	}

	//servlet-context.xml id="uploadPath"에서 정의한 파일 경로 이름
	@javax.annotation.Resource(name="uploadPath")
	private String uploadPath;
	
	@GetMapping("/flist")
	public String flist(HttpServletRequest request, PageDTO pageDTO, Model model) {
		System.out.println("BoardController flist()");
		
		//한 화면에 보여줄 글의 개수 설정
		int pageSize = 5;
		String pageNum = request.getParameter("pageNum"); //request에서 값을 받아옴
		if(pageNum == null) {
			pageNum = "1";
		}
		
		int currentPage = Integer.parseInt(pageNum);
		
		pageDTO.setPageSize(pageSize);
		pageDTO.setPageNum(pageNum);
		pageDTO.setCurrentPage(currentPage);
		
		List<BoardDTO> boardList = boardService.getBoardList(pageDTO);
		
		int count = boardService.getBoardCount(pageDTO); //전체 글의 개수
		int pageBlock = 10; //한 화면에 보여줄 페이지 개수
		int startPage = (currentPage - 1)/pageBlock*pageBlock+1; //한 화면에 보여줄 시작 페이지
		int endPage = startPage + pageBlock -1; //한 화면에 보여줄 끝 페이지 구하기
		int pageCount = count/pageSize + (count%pageSize==0?0:1);
		
		pageDTO.setCount(count);
		pageDTO.setPageBlock(pageBlock);
		pageDTO.setStartPage(startPage);
		pageDTO.setEndPage(endPage);
		pageDTO.setPageCount(pageCount);
		
		model.addAttribute("pageDTO", pageDTO);
		model.addAttribute("boardList",boardList);
		
		return "center/fnotice";
	}
	
	@GetMapping("/fwrite")
	public String fwrite() {
		System.out.println("BoardController fwrite()");
		return "center/fwrite";
	}
	
	@PostMapping("/fwritePro")
	public String fwritePro(HttpServletRequest request, MultipartFile file) throws Exception {
		System.out.println("BoardController fwritePro()");
		
		//파일 업로드 게시판 → 프로그램 설치 → pom.xml(commons-fileupload, commons-io, javax-annotation 설치) → servlet-context.xml에서 프로그램 설정 1) 대용량 데이터베이스(오라클) 컬럼에 저장, 2) 저용량 데이터베이스(MySql) 서버 폴더에 파일 업로드하고 데이터베이스에 파일 이름이 저장
		//name = "file" → MultipartFile → file 이름이 동일. 즉 name="이름"이름과 MultipartFile 이름이 동일해야함
		//업로드 파일 이름이 동일할 경우 → 랜덤문자_파일이름 으로 이름 변경. 즉 파일이름이 중복되지 않게 업로드 시 랜덤문자를 추가 UUID
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString()+"_"+file.getOriginalFilename();
		//업로드 원본 파일 file.getByte() → upload폴더에 복사(파일 업로드). FileCopyUtils.copy(원본파일, 복사파일);
		FileCopyUtils.copy(fileName.getBytes(), new File(uploadPath,fileName));
		
		//boardDTO 파라미터값 저장
		//name,subject, content, file
		BoardDTO boardDTO = new BoardDTO(); 
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		boardDTO.setFile(fileName);
		boardService.insertBoard(boardDTO);
		return "redirect:/board/flist";
	}
	
	@GetMapping("/fcontent")
	public String fcontent(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController fcontent()");
		boardService.updateReadCount(boardDTO); //조회수 증가
		
		boardDTO = boardService.getBoard(boardDTO);
		
		model.addAttribute("boardDTO", boardDTO);
		
		return "/center/fcontent";
	}
	
	@GetMapping("/fupdate")
	public String fupdate(BoardDTO boardDTO, Model model) {
		System.out.println("BoardController fupdate()");
		boardDTO = boardService.getBoard(boardDTO);
		model.addAttribute("boardDTO",boardDTO);
		return "/center/fupdate";
	}
	
	
	@PostMapping("/fupatePro")
	public String fupdatePro(HttpServletRequest request, MultipartFile file) throws Exception{
		System.out.println("BoardController fupdatePro()");
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(Integer.parseInt(request.getParameter("num")));
		boardDTO.setName(request.getParameter("name"));
		boardDTO.setSubject(request.getParameter("subject"));
		boardDTO.setContent(request.getParameter("content"));
		
		if(file.isEmpty()) {
			System.out.println("첨부파일이 존재하지 않음");
			boardDTO.setFile(request.getParameter("file"));
		} else {
			System.out.println("첨부파일이 존재함");
			UUID uuid = UUID.randomUUID();
			String filename = uuid.toString()+"_"+file.getOriginalFilename();
			
			FileCopyUtils.copy(filename.getBytes(), new File(uploadPath,filename));
			boardDTO.setFile(filename);
		}
		
		boardService.fupdateBoard(boardDTO);
		
		return "redirect:/board/flist";
	}
	
	@GetMapping("/fdelete")
	public String fdelete(BoardDTO boardDTO) {
		System.out.println("BoardController fdelete()");
		boardService.deleteBoard(boardDTO);
		return "redirect:/board/flist";
	}
	
}

package site.metacoding.red.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.boards.mapper.MainView;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.DetailDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsDao boardsDao;
	// @PostMapping("/boards/{id}/delete")
	// @PostMapping("/boards/{id}/update")
	

	@PostMapping("/boards")
	public String writeBoard(WriteDto writeDto) {
		// 1번. 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅 하고 키값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");
	
		// 2번. principal이 null이면 로그인폼을 redirect해주는 인증방식을 만들어라
		if (principal == null) {
			return"redirect:/loginForm";
		}
		
		// 3. BoardsDao에 접근해서 insert메서드를 호출한다.
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// entity에는 세션의 principal에 getId가 필요하다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));
		
		return"redirect:/";
	}
	
	// http://localhost:8000/?page = 1
	@GetMapping({"/", "/boards"})
	public String getBoardList(Model model, Integer page) {
		if(page == null) page = 0;
		
		int startNum= page * 3;
		
		List<MainDto> boardsList = boardsDao.findAll(startNum);
		//paging.set머시기로 dto완성
		PagingDto paging = boardsDao.paging(page);
		
		final int blockCount =5;
		
		int currentBlock = page/5;
		int startPageNum = 1+(blockCount*currentBlock);
		int lastPageNum = startPageNum+blockCount-1;
		
		if(paging.getTotalCount() < lastPageNum) {
			lastPageNum = paging.getTotalPage();
		}
		
		paging.setBlockCount(blockCount);
		paging.setCurrentBlock(currentBlock);
		paging.setStartPageNum(startPageNum);
		paging.setLastPageNum(lastPageNum);
		
		model.addAttribute("boardsList", boardsList);
		model.addAttribute("paging",paging);
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id,Model model) {
		DetailDto detail =boardsDao.findById(id);
		model.addAttribute("detail",detail);
		return "boards/detail";
	}
	
	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users)session.getAttribute("principal");
		if(principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
}

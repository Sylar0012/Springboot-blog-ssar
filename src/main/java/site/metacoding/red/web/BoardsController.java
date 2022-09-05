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
import site.metacoding.red.web.dto.request.boards.WriteDto;

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
	
	@GetMapping({"/", "/boards"})
	public String getBoardList(Model model) {
		List<MainView> boardsList = boardsDao.findAll();
		model.addAttribute("boardsList", boardsList);
		return "boards/main";
	}
	
	@GetMapping("/boards/{id}")
	public String getBoardList(@PathVariable Integer id) {
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

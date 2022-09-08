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
import oracle.jdbc.proxy.annotation.Post;
import site.metacoding.red.domain.boards.Boards;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.boards.mapper.MainView;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.DetailDto;
import site.metacoding.red.web.dto.request.boards.UpdateDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

@RequiredArgsConstructor
@Controller
public class BoardsController {

	private final HttpSession session;
	private final BoardsDao boardsDao;

	@PostMapping("/boards/{id}/update")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		// 1.영속화
		Users principal = (Users) session.getAttribute("principal");
		Boards boardsPs = boardsDao.findById(id);
		// 비정상 요청 체크
		if (boardsPs == null) {
			return "errors/badPage";
		}
		// 인증체크
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 권한체크
		if (principal.getId() != boardsPs.getUsersId()) {
			return "errors/badPage";
		}
		
		// 2. 변경
		boardsPs.글수정(updateDto);
		
		// 3.수행
		boardsDao.update(boardsPs);

		return "redirect:/boards/" + id;
	}

	@GetMapping("boards/{id}/updateForm")
	public String updateForm(@PathVariable Integer id, Model model) {
		Users principal = (Users) session.getAttribute("principal");
		Boards boardsPs = boardsDao.findById(id);
		// 비정상 요청 체크
		if (boardsPs == null) {
			return "errors/badPage";
		}
		// 인증체크
		if (principal == null) {
			return "redirect:/loginForm";
		}
		// 권한체크
		if (principal.getId() != boardsPs.getUsersId()) {
			return "errors/badPage";
		}

		model.addAttribute("boards", boardsPs);
		return "boards/updateForm";
	}

	@PostMapping("/boards/{id}/delete")
	public String deleteBoards(@PathVariable Integer id) {
		Users principal = (Users) session.getAttribute("principal");
		Boards boardsPs = boardsDao.findById(id);
		if (boardsPs == null) { // if는 비정상 로직을 타게 해서 걸러내는 필터 역할을 하는게 좋다.
			return "errors/badPage";
		}

		// 로그인 확인
		if (principal == null) {
			return "redirect:/loginForm";
		}

		// 권한체크 (principal.getId() usersId 비교.
		if (principal.getId() != boardsPs.getUsersId()) {
			return "redirect:/boards/" + id;
		}

		boardsDao.delete(id); // 핵심 로직
		return "redirect:/";

	}

	@PostMapping("/boards")
	public String writeBoard(WriteDto writeDto) {
		// 1번. 세션에 접근해서 세션값을 확인한다. 그때 Users로 다운캐스팅 하고 키값은 principal로 한다.
		Users principal = (Users) session.getAttribute("principal");

		// 2번. principal이 null이면 로그인폼을 redirect해주는 인증방식을 만들어라
		if (principal == null) {
			return "redirect:/loginForm";
		}

		// 3. BoardsDao에 접근해서 insert메서드를 호출한다.
		// 조건 : dto를 entity로 변환해서 인수로 담아준다.
		// entity에는 세션의 principal에 getId가 필요하다.
		boardsDao.insert(writeDto.toEntity(principal.getId()));

		return "redirect:/";
	}

	// http://localhost:8000/?page = 1
	@GetMapping({ "/", "/boards" })
	public String getBoardList(Model model, Integer page) {
		if (page == null)
			page = 0;

		int startNum = page * 3;

		List<MainDto> boardsList = boardsDao.findAll(startNum);
		// paging.set머시기로 dto완성
		PagingDto paging = boardsDao.paging(page);

		paging.makeBlockInfo();

		model.addAttribute("boardsList", boardsList);
		model.addAttribute("paging", paging);
		return "boards/main";
	}

	@GetMapping("/boards/{id}")
	public String getBoardDetail(@PathVariable Integer id, Model model) {
		model.addAttribute("boards", boardsDao.findById(id));
		return "boards/detail";
	}

	@GetMapping("/boards/writeForm")
	public String writeForm() {
		Users principal = (Users) session.getAttribute("principal");
		if (principal == null) {
			return "redirect:/loginForm";
		}
		return "boards/writeForm";
	}
}

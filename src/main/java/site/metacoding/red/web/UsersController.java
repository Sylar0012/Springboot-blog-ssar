package site.metacoding.red.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.domain.users.UsersDao;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.LoginDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@RequiredArgsConstructor
@Controller
public class UsersController {
	
	private final HttpSession session; // 스프링이 서버시작시에 IoC 컨테이너에 보관함.
	private final UsersDao usersDao;

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}
	
	@PostMapping("/login") // 로그인만 예외로 select인데 post로 함.
	public String login(LoginDto loginDto) {
		Users usersPS = usersDao.login(loginDto);
		if(usersPS != null) { // 인증됨.
			session.setAttribute("principal", usersPS);
			return "redirect:/";
		}else { // 인증안됨.
			
			return "redirect:/loginForm";
		}
		
	}
	
	@PostMapping("/join")
	public String join(JoinDto joinDto) {
		usersDao.insert(joinDto);
		return "redirect:/loginForm";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "users/loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "users/joinForm";
	}
	
	@PostMapping("/users/{id}/delete")
	public String delete(@PathVariable Integer id) {
		Users principal = (Users) session.getAttribute("principal");
		Users usersPs = usersDao.findById(id);
		
		if(usersPs == null) {
			return"errors/badPage";
		}
		
		if(principal == null) {
			return "redirect:/loginForm";
		} 
		
		if(principal.getId() != usersPs.getId()) {
			return"errors/badPage";
		}
		 
		usersDao.delete(id);		
		return"redirect:/";
	}
	
	@GetMapping("/users/{id}/updateForm")
	public String updateBoards(@PathVariable Integer id, Model model) {
		Users principal =(Users) session.getAttribute("principal");
		Users usersPs = usersDao.findById(id);
		
		if(usersPs == null) {
			return"error/badPage";
		}
		
		if(principal == null) {
			return"error/badPage";
		}
		
		if(principal.getId() != usersPs.getId()) {
			return"error/badPage";
		}
		
		model.addAttribute("users", usersPs);
		return"users/updateForm";
	}

	@PostMapping("/users/{id}/update")
	public String update(@PathVariable Integer id, UpdateDto updateDto) {
		Users principal =(Users) session.getAttribute("principal");
		Users usersPs = usersDao.findById(id);
		
		if(usersPs == null) {
			return"error/badPage";
		}
		
		if(principal == null) {
			return"error/badPage";
		}
		
		if(principal.getId() != usersPs.getId()) {
			return"error/badPage";
		}
		
		usersPs.회원수정(updateDto);
		
		usersDao.update(usersPs);
		
		return"redirect:/";
	}

}

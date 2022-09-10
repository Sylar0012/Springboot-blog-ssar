package site.metacoding.red.domain.users;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.web.dto.request.users.UpdateDto;

@Setter
@Getter
public class Users {
	private Integer id;
	private String username;
	private String password;
	private String email;
	private Timestamp createdAt;
	
	public void 회원수정(UpdateDto updateDto) {
		this.password = updateDto.getPassword();
		this.email = updateDto.getEmail();
	}
	
	
}

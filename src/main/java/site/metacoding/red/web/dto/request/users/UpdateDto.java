package site.metacoding.red.web.dto.request.users;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.red.domain.boards.Boards;

@Getter
@Setter
public class UpdateDto {
	private String password;
	private String email;
	

}

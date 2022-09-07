package site.metacoding.red.domain.boards;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Boards {
	private Integer id;
	private String title;
	private String content;
	private Integer usersId;
	private Timestamp createdAt;
		
	public Boards(String title, String content, Integer usersId) {
		this.title = title;
		this.content = content;
		this.usersId = usersId;
	}
	
	
}

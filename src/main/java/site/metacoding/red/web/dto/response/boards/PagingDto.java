package site.metacoding.red.web.dto.response.boards;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagingDto {
	private Integer startNum;     
	private Integer totalCount;     // DB에서 전체 
	private Integer totalPage;       // boards 데이터 갯수 / 10 에 남은값이 있나 없나 파악하고 +1 할지 말지 결정하는 함수 
	private Integer currentPage;   // 	현재 페이지 위치
	private boolean isLast;         // getter가 만들어지면 isLast() 이름으로 만들어짐 -> el에서는 last로 찾음
	private boolean isFirst;         // 
	private Integer startPageNum;          // 1 -> 6 -> 11 pageBlock을 기준으로 잡고 넘어가야함.
	private Integer lastPageNum;           // 5 -> 10 -> 15
	private Integer blockCount;      // 한페이지에 페이지 넘 갯수(5) 1 ~ 5, 6 ~ 10
	private Integer currentBlock;              // 변수
	
	// ?page=0 기준 blockPage = 1, startPageNum = 1, lastPageNum = 5, blockPageCount = 1, 2, 3, 4, 5 (5개)
	// ?page=5 기준 blockPage = 2, startPageNum = 6, lastPageNum = 10, blockPageCount = 6, 7, 8, 9, 10 (5개)

}

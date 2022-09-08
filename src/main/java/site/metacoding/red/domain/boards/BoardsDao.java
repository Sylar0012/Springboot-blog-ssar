package site.metacoding.red.domain.boards;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.metacoding.red.domain.boards.mapper.MainView;
import site.metacoding.red.web.dto.request.boards.DetailDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.response.boards.MainDto;
import site.metacoding.red.web.dto.response.boards.PagingDto;

public interface BoardsDao {
	public PagingDto paging(@Param("page") Integer page, @Param("keyword")String keyword);
	public void insert(Boards boards); // DTO 생각해보기
	public Boards findById(Integer id);
	public List<MainDto> findAll(int startNum);
	public void update(Boards boards); // DTO 생각해보기
	public void delete(Integer id);
	public List<MainDto> findSearch(@Param("startNum") int startNum, @Param("keyword")String keyword);
}

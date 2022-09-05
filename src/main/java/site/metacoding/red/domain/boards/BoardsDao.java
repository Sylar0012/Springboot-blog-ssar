package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.boards.mapper.MainView;
import site.metacoding.red.web.dto.request.boards.DetailDto;
import site.metacoding.red.web.dto.request.boards.WriteDto;

public interface BoardsDao {
	public void insert(Boards boards); // DTO 생각해보기
	public DetailDto findById(Integer id);
	public List<MainView> findAll();
	public void update(Boards boards); // DTO 생각해보기
	public void delete(Integer id);
}

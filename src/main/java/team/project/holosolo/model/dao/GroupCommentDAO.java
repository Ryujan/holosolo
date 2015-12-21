package team.project.holosolo.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.GroupCommentDTO;

public interface GroupCommentDAO {
	public abstract int groupCommentWrite(GroupCommentDTO g_commentDTO) throws SQLException;
	public abstract int groupCommentDelete(String id) throws SQLException;
	public abstract List<GroupCommentDTO> groupCommentList(HashMap<String, String> map) throws SQLException;
	//
	public abstract int totalCount(String gno) throws SQLException;
}

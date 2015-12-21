package team.project.holosolo.model.service;

import java.sql.SQLException;
import java.util.HashMap;

import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.GroupCommentDTO;

public interface GroupCommentService {
	public abstract int groupCommentWrite(GroupCommentDTO g_commentDTO) throws SQLException;
	public abstract int groupCommentDelete(String id) throws SQLException;
	public abstract CommentListByPageDTO groupCommentList(HashMap<String, String> map) throws SQLException;
}

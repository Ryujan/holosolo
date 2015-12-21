package team.project.holosolo.model.service;

import java.sql.SQLException;
import java.util.HashMap;

import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.ContentCommentDTO;

public interface ContentCommentService {
	public abstract int contentsCommentWrite(ContentCommentDTO c_commentDTO) throws SQLException;
	public abstract CommentListByPageDTO contentsCommentList(HashMap<String, String> map) throws SQLException;
	public abstract int contentsCommentDelete(String writer) throws SQLException;
	public abstract int decreaseHits(String cno) throws SQLException;
}

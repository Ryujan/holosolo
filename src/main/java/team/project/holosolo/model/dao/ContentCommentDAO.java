package team.project.holosolo.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.ContentCommentDTO;

public interface ContentCommentDAO {
	// ver 1,2,3
	public abstract int contentsCommentWrite(ContentCommentDTO c_commentDTO) throws SQLException;
	public abstract List<ContentCommentDTO> contentsCommentList(HashMap<String, String> map) throws SQLException;
	public abstract int contentsCommentDelete(String writer) throws SQLException;
	// ver 4
	public abstract int decreaseHits(String cno) throws SQLException;
	//
	public abstract int totalCount(String cno) throws SQLException;
}

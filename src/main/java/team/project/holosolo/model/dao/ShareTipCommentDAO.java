package team.project.holosolo.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.ShareTipCommentDTO;

public interface ShareTipCommentDAO {
	// ver 1,2,3
	public abstract int shareCommentWrite(ShareTipCommentDTO s_commentDTO) throws SQLException; 
	public abstract List<ShareTipCommentDTO> shareCommentList(HashMap<String, String> map)throws SQLException;
	public abstract int shareCommentDelete(String rno)throws SQLException;
	// ver 4
	public abstract int decreaseHits(String sno) throws SQLException;
	//
	public abstract int totalCount(String sno) throws SQLException;
}

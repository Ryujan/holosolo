package team.project.holosolo.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.ShareTipCommentDTO;

public interface ShareTipCommentService {
	public abstract int shareCommentWrite(ShareTipCommentDTO s_commentDTO) throws SQLException; 
	public abstract CommentListByPageDTO shareCommentList(HashMap<String, String> map)throws SQLException;
	public abstract int shareCommentDelete(String rno)throws SQLException;
	public abstract int decreaseHits(String sno) throws SQLException;
}

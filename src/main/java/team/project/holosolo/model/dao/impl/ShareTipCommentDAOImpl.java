package team.project.holosolo.model.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.project.holosolo.model.dao.ShareTipCommentDAO;
import team.project.holosolo.model.dto.ShareTipCommentDTO;

public class ShareTipCommentDAOImpl implements ShareTipCommentDAO{
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int shareCommentWrite(ShareTipCommentDTO s_commentDTO) throws SQLException {
		return sqlSession.insert("ShareTipComment.shareCommentWrite", s_commentDTO);
	}
	
	//12.10 수정
	@Override
	public List<ShareTipCommentDTO> shareCommentList(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("ShareTipComment.shareCommentList", map);
	}

	@Override
	public int shareCommentDelete(String rno) throws SQLException {
		return sqlSession.insert("ShareTipComment.shareCommentDelete", rno);
	}
	
	@Override
	public int decreaseHits(String sno) throws SQLException {
		return sqlSession.update("ShareTipComment.decreaseHits", sno);
	}
	
	//12.10 추가
	@Override
	public int totalCount(String sno) throws SQLException {
		return sqlSession.selectOne("ShareTipComment.totalCount", sno);
	}
}

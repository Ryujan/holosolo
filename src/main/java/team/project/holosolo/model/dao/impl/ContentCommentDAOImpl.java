package team.project.holosolo.model.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.project.holosolo.model.dao.ContentCommentDAO;
import team.project.holosolo.model.dto.ContentCommentDTO;

public class ContentCommentDAOImpl implements ContentCommentDAO{
	
	private SqlSession sqlSession;	
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int contentsCommentWrite(ContentCommentDTO c_commentDTO) throws SQLException {
		return sqlSession.insert("ContentComment.contentsCommentWrite", c_commentDTO);
	}

	//12.10 수정
	@Override
	public List<ContentCommentDTO> contentsCommentList(HashMap<String, String> map)
			throws SQLException {
		return sqlSession.selectList("ContentComment.contentsCommentList", map);
	}

	@Override
	public int contentsCommentDelete(String writer) throws SQLException {
		return sqlSession.delete("ContentComment.contentsCommentDelete",writer);	
	}
	
	@Override
	public int decreaseHits(String cno) throws SQLException {
		return sqlSession.update("ContentComment.decreaseHits", cno);
	}
	
	//12.10 추가
	@Override
	public int totalCount(String cno) throws SQLException {
		return sqlSession.selectOne("ContentComment.totalCount", cno);
	}
}

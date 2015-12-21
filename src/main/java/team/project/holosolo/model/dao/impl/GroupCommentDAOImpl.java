package team.project.holosolo.model.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import team.project.holosolo.model.dao.GroupCommentDAO;
import team.project.holosolo.model.dto.GroupCommentDTO;

public class GroupCommentDAOImpl implements GroupCommentDAO{
	private SqlSessionTemplate sqlSession;
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int groupCommentWrite(GroupCommentDTO g_commentDTO) throws SQLException {
		return sqlSession.insert("GroupComment.groupCommentWrite", g_commentDTO);
	}

	@Override
	public int groupCommentDelete(String id) throws SQLException {
		return sqlSession.delete("GroupComment.groupCommentDelete", id);
	}
	
	//12.10 수정
	@Override
	public List<GroupCommentDTO> groupCommentList(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("GroupComment.groupCommentList", map);
	}
	
	//12.10 추가
	@Override
	public int totalCount(String gno) throws SQLException {
		return sqlSession.selectOne("GroupComment.totalCount", gno);
	}
}

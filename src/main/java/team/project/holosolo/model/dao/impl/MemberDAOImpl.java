package team.project.holosolo.model.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.project.holosolo.model.dao.MemberDAO;
import team.project.holosolo.model.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int memberRegister(MemberDTO mdto) throws SQLException {
		return sqlSession.insert("Member.memberRegister", mdto);
	}

	@Override
	public MemberDTO login(MemberDTO mdto) throws SQLException {
		return sqlSession.selectOne("Member.login", mdto);
	}
	
	@Override
	public int memberUpdate(MemberDTO mdto) throws SQLException {
		return sqlSession.update("Member.memberUpdate", mdto);
	}

	@Override
	public int memberDelete(MemberDTO mdto) throws SQLException {
		return sqlSession.delete("Member.memberDelete", mdto);
	}

	@Override
	public List<MemberDTO> memberList() throws SQLException {
		return sqlSession.selectList("Member.memberList");
	}

	@Override
	public Object idCheck(String id) throws SQLException {
		return sqlSession.selectOne("Member.idCheck", id);		
	}

	@Override
	public int myPhotoUpdate(HashMap<String, String> map) throws SQLException {
		return sqlSession.update("Member.myPhotoUpdate", map);
	}
	
	@Override
	public MemberDTO findPassword(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectOne("Member.findPassword", map);
	}
}

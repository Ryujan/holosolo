package team.project.holosolo.model.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import team.project.holosolo.model.dao.GroupDAO;
import team.project.holosolo.model.dto.GroupDTO;

public class GroupDAOImpl implements GroupDAO {
	// field
	private SqlSessionTemplate sqlSession;

	// setter
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int groupPost(GroupDTO gdto) throws SQLException {
		return sqlSession.insert("Group.groupPost", gdto);
	}

	// ver5 수정
	@Override
	public List<GroupDTO> groupList(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("Group.groupList", map);
	}

	@Override
	public HashMap<String, Object> groupShowDetail(String gno) throws SQLException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		GroupDTO groupDTO = sqlSession.selectOne("Group.groupShowDetail", gno);
		map.put("groupDTO", groupDTO);
		map.put("memberDTO", sqlSession.selectOne("Member.getMemberById", groupDTO.getMemberDTO().getId()));
		
		return map;
	}

	@Override
	public int groupJoin(HashMap<String, String> map) throws SQLException {
		return sqlSession.insert("Group.groupJoin", map);
	}

	@Override
	public GroupDTO getMyGroup(String id) throws SQLException {
		return sqlSession.selectOne("Group.getMyGroup", id);
	}

	// 추가
	@Override
	public int groupUpdate(GroupDTO groupDTO) throws SQLException {
		return sqlSession.update("Group.groupUpdate", groupDTO);
	}

	@Override
	public List<String> getMemberGroupByGno(String gno) throws SQLException {
		return sqlSession.selectList("Group.getMemberGroupByGno", gno);
	}

	@Override
	public List<GroupDTO> getGroupInLastDate() throws SQLException {
		return sqlSession.selectList("Group.getGroupInLastDate");
	}

	@Override
	public int updateStatus(HashMap<String, String> map) throws SQLException {
		return sqlSession.update("Group.updateStatus", map);
	}

	@Override
	public int groupLeave(HashMap<String, String> map) throws SQLException {
		return sqlSession.delete("Group.groupLeave", map);
	}

	@Override
	public HashMap<String, String> checkGroupJoined(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectOne("Group.checkGroupJoined", map);
	}

	@Override
	public int increaseCurrentNum(String gno) throws SQLException {
		return sqlSession.update("Group.increaseCurrentNum", gno);
	}

	@Override
	public int decreaseCurrentNum(String gno) throws SQLException {
		return sqlSession.update("Group.decreaseCurrentNum", gno);
	}

	@Override
	public int groupCancel(String gno) throws SQLException {
		return sqlSession.delete("Group.groupCancel", gno);
	}
	
	// 12.10 추가 수정
	@Override
	public List<String> myJoinedGroup(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("Group.myJoinedGroup", map);
	}

	@Override
	public List<GroupDTO> indexGroupList() throws SQLException {
		return sqlSession.selectList("Group.indexGroupList");
	}

	@Override
	public GroupDTO getJoinedGroupByGno(String gno) throws SQLException {
		return sqlSession.selectOne("Group.getJoinedGroupByGno", gno);
	}
	
	// ver5
	public int totalCount(String category)throws SQLException{
		return sqlSession.selectOne("Group.totalCount",category);
	}
	
	// 12.10 추가
	@Override
	public int myTotalCount(String id) throws SQLException {
		return sqlSession.selectOne("Group.myTotalCount",id);
	}
}
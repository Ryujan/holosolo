package team.project.holosolo.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.GroupDTO;

public interface GroupDAO {
	// ver 1,2
	public abstract int groupPost(GroupDTO gdto) throws SQLException;
	// ver 5 수정 argument(String->HashMap)
	public abstract List<GroupDTO> groupList(HashMap<String, String> map) throws SQLException;
	public abstract HashMap<String, Object> groupShowDetail(String gno) throws SQLException;
	// ver 3
	public abstract int groupJoin(HashMap<String, String> map) throws SQLException;
	public abstract int groupLeave(HashMap<String, String> map) throws SQLException;
	public abstract HashMap<String, String> checkGroupJoined(HashMap<String, String> map) throws SQLException;
	public abstract int groupUpdate(GroupDTO groupDTO) throws SQLException;
	public abstract int increaseCurrentNum(String gno) throws SQLException;
	public abstract int decreaseCurrentNum(String gno) throws SQLException;
	public abstract int updateStatus(HashMap<String, String> map) throws SQLException;
	public abstract int groupCancel(String gno) throws SQLException;
	// ver 4
	public abstract GroupDTO getMyGroup(String id) throws SQLException;
	public abstract List<String> getMemberGroupByGno(String gno) throws SQLException;
	public abstract List<GroupDTO> getGroupInLastDate() throws SQLException;
	// 12.10 추가 수정
	public abstract List<String> myJoinedGroup(HashMap<String, String> map) throws SQLException;
	public abstract List<GroupDTO> indexGroupList()throws SQLException;
	public abstract GroupDTO getJoinedGroupByGno(String gno) throws SQLException;
	// ver 5
	public abstract int totalCount(String category)throws SQLException;
	
	//12.10 추가
	public abstract int myTotalCount(String id)throws SQLException;
}
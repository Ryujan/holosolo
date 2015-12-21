package team.project.holosolo.model.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dao.GroupDAO;
import team.project.holosolo.model.dto.GroupDTO;
import team.project.holosolo.model.dto.GroupListDTO;
import team.project.holosolo.model.service.GroupService;
import team.project.holosolo.util.PagingBean;

public class GroupServiceImpl implements GroupService {
	// field
	private GroupDAO groupDAO;
	// ver 5 추가
	int numberOfContentPerPage;
	int numberOfPageGroup;
	
	// setter
	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}
	public void setNumberOfContentPerPage(int numberOfContentPerPage) {
		this.numberOfContentPerPage = numberOfContentPerPage;
	}
	public void setNumberOfPageGroup(int numberOfPageGroup) {
		this.numberOfPageGroup = numberOfPageGroup;
	}

	@Override
	public int groupPost(GroupDTO gdto) throws SQLException {
		return groupDAO.groupPost(gdto);
	}

	//ver 5 추가
	@Override
	public GroupListDTO groupList(HashMap<String, String> map) throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
			pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<GroupDTO> groupList = groupDAO.groupList(map);
		
		int total = groupDAO.totalCount(map.get("category"));
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		GroupListDTO groupListDTO = new GroupListDTO(groupList, paging);
		
		return groupListDTO;
	}
	
	@Override
	public HashMap<String, Object> groupShowDetail(String gno) throws SQLException {
		return groupDAO.groupShowDetail(gno);
	}

	@Override
	public int groupJoin(HashMap<String, String> map) throws SQLException {
		return groupDAO.groupJoin(map);
	}

	@Override
	public GroupDTO getMyGroup(String id) throws SQLException {
		return groupDAO.getMyGroup(id);
	}

	// 추가
	@Override
	public int groupUpdate(GroupDTO groupDTO) throws SQLException {
		return groupDAO.groupUpdate(groupDTO);
	}

	@Override
	public List<String> getMemberGroupByGno(String gno) throws SQLException {
		return groupDAO.getMemberGroupByGno(gno);
	}

	@Override
	public List<GroupDTO> getGroupInLastDate() throws SQLException {
		return groupDAO.getGroupInLastDate();
	}

	@Override
	public int updateStatus(HashMap<String, String> map) throws SQLException {
		return groupDAO.updateStatus(map);
	}

	@Override
	public int groupLeave(HashMap<String, String> map) throws SQLException {
		return groupDAO.groupLeave(map);
	}

	@Override
	public HashMap<String, String> checkGroupJoined(HashMap<String, String> map) throws SQLException {
		return groupDAO.checkGroupJoined(map);
	}

	@Override
	public int increaseCurrentNum(String gno) throws SQLException {
		return groupDAO.increaseCurrentNum(gno);
	}

	@Override
	public int decreaseCurrentNum(String gno) throws SQLException {
		return groupDAO.decreaseCurrentNum(gno);
	}

	@Override
	public int groupCancel(String gno) throws SQLException {
		return groupDAO.groupCancel(gno);
	}
	
	// 12.10 추가 수정
	@Override
	public GroupListDTO myJoinedGroup(HashMap<String, String> map) throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
			pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<String> gnoList = groupDAO.myJoinedGroup(map);
		List<GroupDTO> myGroupList = new ArrayList<GroupDTO>();
		for(String gno : gnoList){
			myGroupList.add(groupDAO.getJoinedGroupByGno(gno));
		}
		
		int total = groupDAO.myTotalCount(map.get("id"));
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		GroupListDTO groupListDTO = new GroupListDTO(myGroupList, paging);
		
		return groupListDTO;
	}

	@Override
	public List<GroupDTO> indexGroupList() throws SQLException {
		return groupDAO.indexGroupList();
	}
}
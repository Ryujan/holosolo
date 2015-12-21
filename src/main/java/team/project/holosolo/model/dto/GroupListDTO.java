package team.project.holosolo.model.dto;

import java.util.List;

import team.project.holosolo.util.PagingBean;

/**
 * 게시물 리스트 정보와 
 * 페이징 정보를 가지고 있는 클래스 
 * @author inst
 *
 */
public class GroupListDTO {
	private List<GroupDTO> groupList;
	private PagingBean pagingBean;
	
	public GroupListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GroupListDTO(List<GroupDTO> groupList, PagingBean pagingBean) {
		this.groupList = groupList;
		this.pagingBean = pagingBean;
	}

	public List<GroupDTO> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<GroupDTO> groupList) {
		this.groupList = groupList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}

	@Override
	public String toString() {
		return "ListVO [groupList=" + groupList + ", pagingBean=" + pagingBean + "]";
	}
	
}











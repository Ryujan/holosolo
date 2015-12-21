package team.project.holosolo.model.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dao.GroupCommentDAO;
import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.GroupCommentDTO;
import team.project.holosolo.model.service.GroupCommentService;
import team.project.holosolo.util.PagingBean;

public class GroupCommentServiceImpl implements GroupCommentService{
	private GroupCommentDAO groupCommentDAO;
	private int numberOfCommentsPerPage;
	private int numberOfPageGroup;
	
	public void setGroupCommentDAO(GroupCommentDAO groupCommentDAO) {
		this.groupCommentDAO = groupCommentDAO;
	}
	public void setNumberOfCommentsPerPage(int numberOfCommentsPerPage) {
		this.numberOfCommentsPerPage = numberOfCommentsPerPage;
	}
	public void setNumberOfPageGroup(int numberOfPageGroup) {
		this.numberOfPageGroup = numberOfPageGroup;
	}

	@Override
	public int groupCommentWrite(GroupCommentDTO g_commentDTO) throws SQLException {
		return groupCommentDAO.groupCommentWrite(g_commentDTO);
	}

	@Override
	public int groupCommentDelete(String id) throws SQLException {
		return groupCommentDAO.groupCommentDelete(id);
	}

	//12.10 추가수정
	@Override
	public CommentListByPageDTO groupCommentList(HashMap<String, String> map) throws SQLException {
		String replyPageNo = map.get("replyPageNo");
		if(replyPageNo == null | replyPageNo == "")
			replyPageNo = "1";
		
		map.put("replyPageNo", replyPageNo);
		
		// 이 아래서 문제 발생
		List<GroupCommentDTO> commentList = groupCommentDAO.groupCommentList(map);
		
		int total = groupCommentDAO.totalCount(map.get("gno"));
		
		PagingBean pagingBean = new PagingBean(total, Integer.parseInt(replyPageNo));
		pagingBean.setNumberOfContentPerPage(numberOfCommentsPerPage);
		pagingBean.setNumberOfPageGroup(numberOfPageGroup);
		
		CommentListByPageDTO commentListByPageDTO = new CommentListByPageDTO(commentList, pagingBean);
		
		return commentListByPageDTO;
	}
}

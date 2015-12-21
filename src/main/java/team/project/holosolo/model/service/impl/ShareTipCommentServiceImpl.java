package team.project.holosolo.model.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dao.ShareTipCommentDAO;
import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.ShareTipCommentDTO;
import team.project.holosolo.model.service.ShareTipCommentService;
import team.project.holosolo.util.PagingBean;

public class ShareTipCommentServiceImpl implements ShareTipCommentService{
	private ShareTipCommentDAO shareTipCommentDAO;
	private int numberOfCommentsPerPage;
	private int numberOfPageGroup;
	
	public void setShareTipCommentDAO(ShareTipCommentDAO shareTipCommentDAO) {
		this.shareTipCommentDAO = shareTipCommentDAO;
	}
	public void setNumberOfCommentsPerPage(int numberOfCommentsPerPage) {
		this.numberOfCommentsPerPage = numberOfCommentsPerPage;
	}
	public void setNumberOfPageGroup(int numberOfPageGroup) {
		this.numberOfPageGroup = numberOfPageGroup;
	}

	@Override
	public int shareCommentWrite(ShareTipCommentDTO s_commentDTO) throws SQLException {
		return shareTipCommentDAO.shareCommentWrite(s_commentDTO);
	}
	
	//12.10 추가수정
	@Override
	public CommentListByPageDTO shareCommentList(HashMap<String, String> map) throws SQLException {
		String replyPageNo = map.get("replyPageNo");
		if(replyPageNo == null | replyPageNo == "")
			replyPageNo = "1";
		map.put("replyPageNo", replyPageNo);
		
		List<ShareTipCommentDTO> commentList = shareTipCommentDAO.shareCommentList(map);
		
		int total = shareTipCommentDAO.totalCount(map.get("sno"));
		
		PagingBean pagingBean = new PagingBean(total, Integer.parseInt(replyPageNo));
		pagingBean.setNumberOfContentPerPage(numberOfCommentsPerPage);
		pagingBean.setNumberOfPageGroup(numberOfPageGroup);
		
		CommentListByPageDTO commentListByPageDTO = new CommentListByPageDTO(commentList, pagingBean);
		
		return commentListByPageDTO;
	}
	
	@Override
	public int shareCommentDelete(String rno) throws SQLException {
		return shareTipCommentDAO.shareCommentDelete(rno);
	}
		
	@Override
	public int decreaseHits(String sno) throws SQLException {
		return shareTipCommentDAO.decreaseHits(sno);
	}
}

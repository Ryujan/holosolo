package team.project.holosolo.model.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dao.ContentCommentDAO;
import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.ContentCommentDTO;
import team.project.holosolo.model.service.ContentCommentService;
import team.project.holosolo.util.PagingBean;

public class ContentCommentServiceImpl implements ContentCommentService{
	private ContentCommentDAO contentCommentDAO;
	private int numberOfCommentsPerPage;
	private int numberOfPageGroup;
	
	public void setContentCommentDAO(ContentCommentDAO contentCommentDAO) {
		this.contentCommentDAO = contentCommentDAO;
	}
	public void setNumberOfCommentsPerPage(int numberOfCommentsPerPage) {
		this.numberOfCommentsPerPage = numberOfCommentsPerPage;
	}
	public void setNumberOfPageGroup(int numberOfPageGroup) {
		this.numberOfPageGroup = numberOfPageGroup;
	}
	
	@Override
	public int contentsCommentWrite(ContentCommentDTO c_commentDTO) throws SQLException {
		return contentCommentDAO.contentsCommentWrite(c_commentDTO);		
	}

	//12.10 추가수정
	@Override
	public CommentListByPageDTO contentsCommentList(HashMap<String, String> map) throws SQLException{
		String replyPageNo = map.get("replyPageNo");
		if(replyPageNo == null | replyPageNo == "")
			replyPageNo = "1";
		
		map.put("replyPageNo", replyPageNo);
		
		// 이 아래서 문제 발생
		List<ContentCommentDTO> commentList = contentCommentDAO.contentsCommentList(map);
		
		int total = contentCommentDAO.totalCount(map.get("cno"));
		
		PagingBean pagingBean = new PagingBean(total, Integer.parseInt(replyPageNo));
		pagingBean.setNumberOfContentPerPage(numberOfCommentsPerPage);
		pagingBean.setNumberOfPageGroup(numberOfPageGroup);
		
		CommentListByPageDTO commentListByPageDTO = new CommentListByPageDTO(commentList, pagingBean);
		
		return commentListByPageDTO;
	}


	@Override
	public int contentsCommentDelete(String writer) throws SQLException {
		return contentCommentDAO.contentsCommentDelete(writer);	
	}
	
	@Override
	public int decreaseHits(String cno) throws SQLException {
		return contentCommentDAO.decreaseHits(cno);
	}
}

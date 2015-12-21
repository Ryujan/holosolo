package team.project.holosolo.model.dto;

import java.util.List;

import team.project.holosolo.util.PagingBean;

public class CommentListByPageDTO {
	// fields
	private List commentList; // 하나의 DTO로 모든 comment의 페이징을 할 수 있도록 하기 위해서 List에 Generic을 주지 않는다.
	private PagingBean pagingBean;
	
	// Constructors
	public CommentListByPageDTO() {}
	public CommentListByPageDTO(List commentList, PagingBean pagingBean) {
		this.commentList = commentList;
		this.pagingBean = pagingBean;
	}
	
	// Getters() & Setters()
	public List getCommentList() {
		return commentList;
	}
	public void setCommentList(List commentList) {
		this.commentList = commentList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}
	
	// toString()
	@Override
	public String toString() {
		return "CommentListByPageDTO [commentList=" + commentList
				+ ", pagingBean=" + pagingBean + "]";
	}
}

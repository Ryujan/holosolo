package team.project.holosolo.model.dto;

import java.util.List;

import team.project.holosolo.util.PagingBean;

/**
 * 게시물 리스트 정보와 
 * 페이징 정보를 가지고 있는 클래스 
 * @author inst
 *
 */
public class ContentListDTO {
	private List<ContentDTO> contentList;
	private PagingBean pagingBean;
	
	public ContentListDTO() {
	}

	public ContentListDTO(List<ContentDTO> contentList, PagingBean pagingBean) {
		this.contentList = contentList;
		this.pagingBean = pagingBean;
	}

	public List<ContentDTO> getContentList() {
		return contentList;
	}
	public void setContentList(List<ContentDTO> contentList) {
		this.contentList = contentList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}

	@Override
	public String toString() {
		return "ListVO [contentList=" + contentList + ", pagingBean=" + pagingBean + "]";
	}
	
}











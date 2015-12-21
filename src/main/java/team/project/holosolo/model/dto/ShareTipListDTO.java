package team.project.holosolo.model.dto;

import java.util.List;

import team.project.holosolo.util.PagingBean;

/**
 * 게시물 리스트 정보와 
 * 페이징 정보를 가지고 있는 클래스 
 * @author inst
 *
 */
public class ShareTipListDTO {
	private List<ShareTipDTO> shareTipList;
	private PagingBean pagingBean;
	
	public ShareTipListDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShareTipListDTO(List<ShareTipDTO> shareTipList, PagingBean pagingBean) {
		super();
		this.shareTipList = shareTipList;
		this.pagingBean = pagingBean;
	}

	public List<ShareTipDTO> getShareTipList() {
		return shareTipList;
	}
	public void setShareTipList(List<ShareTipDTO> shareTipList) {
		shareTipList = shareTipList;
	}
	public PagingBean getPagingBean() {
		return pagingBean;
	}
	public void setPagingBean(PagingBean pagingBean) {
		this.pagingBean = pagingBean;
	}

	@Override
	public String toString() {
		return "ListVO [shareTipList=" + shareTipList + ", pagingBean=" + pagingBean + "]";
	}
	
}











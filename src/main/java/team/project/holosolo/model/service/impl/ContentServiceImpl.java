package team.project.holosolo.model.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dao.ContentDAO;
import team.project.holosolo.model.dto.ContentDTO;
import team.project.holosolo.model.dto.ContentListDTO;
import team.project.holosolo.model.dto.ShareTipDTO;
import team.project.holosolo.model.dto.ShareTipListDTO;
import team.project.holosolo.model.service.ContentService;
import team.project.holosolo.util.PagingBean;

public class ContentServiceImpl implements ContentService {
	private ContentDAO contentDAO;
	// ver 5 추가
	int numberOfContentPerPage;
	int numberOfPageGroup;

	public void setContentDAO(ContentDAO contentDAO) {
		this.contentDAO = contentDAO;
	}
	// ver 5 추가	
	public void setNumberOfContentPerPage(int numberOfContentPerPage) {
		this.numberOfContentPerPage = numberOfContentPerPage;
	}
	public void setNumberOfPageGroup(int numberOfPageGroup) {
		this.numberOfPageGroup = numberOfPageGroup;
	}

	@Override
	public int contentPost(ContentDTO cdto) throws SQLException {
		return contentDAO.contentPost(cdto);
	}

	//ver 5 수정
	@Override
	public ContentListDTO contentList(HashMap<String, String> map) throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
			pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<ContentDTO> contentList = contentDAO.contentList(map);
		
		int total = contentDAO.totalCount(map.get("category"));
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		ContentListDTO contentListDTO = new ContentListDTO(contentList, paging);
		
		return contentListDTO;
	}

	@Override
	public ContentDTO contentShowDetail(String cno) throws SQLException {
		return contentDAO.contentShowDetail(cno);
	}

	@Override
	public int contentRecommend(HashMap<String, String> map)
			throws SQLException {
		return contentDAO.contentRecommend(map);
	}

	// 12.10 추가수정
	@Override
	public ContentListDTO contentSearch(HashMap<String, String> map)
			throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
			pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<ContentDTO> contentList = contentDAO.contentSearch(map);
		
		int total = contentDAO.searchTotalCount(map);
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		ContentListDTO contentListDTO = new ContentListDTO(contentList, paging);
		
		return contentListDTO;
	}

	@Override
	public int contentUpdate(ContentDTO cdto) throws SQLException {
		return contentDAO.contentUpdate(cdto);
	}

	@Override
	public int contentDelete(String cno) throws SQLException {
		return contentDAO.contentDelete(cno);
	}

	@Override
	public List<String> contentRecommendCount(String cno) throws SQLException {
		return contentDAO.contentRecommendCount(cno);
	}

	@Override
	public int updateHits(String cno) throws SQLException {
		return contentDAO.updateHits(cno);
	}
	
	//12.10 수정
	@Override
	public ContentListDTO getCListByRecommend(HashMap<String, String> map) throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
		pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<String> cnoList = contentDAO.getCListByRecommend(map);
		List<ContentDTO> myRecommendedCList = new ArrayList<ContentDTO>();
		for(String cno : cnoList){
			myRecommendedCList.add(contentDAO.contentByCno(cno));
		}
		
		int total = contentDAO.myRecommandTotalCount(map.get("id"));
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		ContentListDTO contentListDTO = new ContentListDTO(myRecommendedCList, paging);
		return contentListDTO;
	}

	@Override
	public ContentDTO contentByCno(String cno) throws SQLException {
		return contentDAO.contentByCno(cno);
	}
	
	// ver 5 추가
	@Override
	public List<ContentDTO> showContentListInDetail(String category) throws SQLException {
		if(category.equals("seeAll")) category = null;
		
		return contentDAO.showContentListInDetail(category);
	}
}

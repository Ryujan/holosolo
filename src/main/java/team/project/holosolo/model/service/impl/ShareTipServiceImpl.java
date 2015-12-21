package team.project.holosolo.model.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import team.project.holosolo.model.dao.ShareTipDAO;
import team.project.holosolo.model.dto.ShareTipDTO;
import team.project.holosolo.model.dto.ShareTipListDTO;
import team.project.holosolo.model.service.ShareTipService;
import team.project.holosolo.util.PagingBean;

public class ShareTipServiceImpl implements ShareTipService {
	private ShareTipDAO shareTipDAO;
	//ver 5 추가
	int numberOfContentPerPage;
	int numberOfPageGroup;

	public void setShareTipDAO(ShareTipDAO shareTipDAO) {
		this.shareTipDAO = shareTipDAO;
	}
	
	public void setNumberOfContentPerPage(int numberOfContentPerPage) {
		this.numberOfContentPerPage = numberOfContentPerPage;
	}

	public void setNumberOfPageGroup(int numberOfPageGroup) {
		this.numberOfPageGroup = numberOfPageGroup;
	}


	@Override
	public int sharePost(ShareTipDTO sdto) throws SQLException {
		return shareTipDAO.sharePost(sdto);
	}

	@Override
	public ShareTipDTO shareShowDetail(String sno) throws SQLException {
		return shareTipDAO.shareShowDetail(sno);
	}
	
	//ver 5 수정
	@Override
	public ShareTipListDTO shareList(HashMap<String, String> map) throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
			pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<ShareTipDTO> shareTipList = shareTipDAO.shareList(map);
		
		int total = shareTipDAO.totalCount(map.get("category"));
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		ShareTipListDTO shareTipListDTO = new ShareTipListDTO(shareTipList, paging);
		
		return shareTipListDTO;
	}

	@Override
	public int shareRecommend(HashMap<String, String> map) throws SQLException {
		return shareTipDAO.shareRecommend(map);
	}

	//ver 5 수정
	@Override
	public ShareTipListDTO shareSearch(HashMap<String, String> map)
			throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
			pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<ShareTipDTO> shareTipList = shareTipDAO.shareSearch(map);
		
		int total = shareTipDAO.searchTotalCount(map);
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		ShareTipListDTO shareTipListDTO = new ShareTipListDTO(shareTipList, paging);
		
		return shareTipListDTO;
	}
	
	@Override
	public int shareUpdate(ShareTipDTO sdto) throws SQLException {
		return shareTipDAO.shareUpdate(sdto);
	}

	@Override
	public int shareDelete(String sno) throws SQLException {
		return shareTipDAO.shareDelete(sno);
	}

	@Override
	public int updateHits(String sno) throws SQLException {
		return shareTipDAO.updateHits(sno);
	}

	@Override
	public List<String> shareRecommendCount(String sno) {
		return shareTipDAO.shareRecommendCount(sno);
	}

	//추가  12.09 수정
	@Override
	public ShareTipListDTO myActivityShare(HashMap<String, String> map) throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
			pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<ShareTipDTO> shareTipList = shareTipDAO.myActivityShare(map);
		
		int total = shareTipDAO.myTotalCount(map.get("id"));
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		ShareTipListDTO shareTipListDTO = new ShareTipListDTO(shareTipList, paging);
		
		return shareTipListDTO;
	}

	//12.10 수정
	@Override
	public ShareTipListDTO getSListByRecommend(HashMap<String,String> map) throws SQLException {
		String pageNo = map.get("pageNo");
		if(pageNo==null | pageNo=="")//목록보기 버튼을 누를경우 최신글을 보여주기 위해서 setting
		pageNo = "1";//페이징 처리 안해서 최근 페이지를 보여지도록 한다.
		
		map.put("pageNo", pageNo);
		
		// @@@@@@@@ 페이징 처리시 수정되는 부분 @@@@@@@@@
		List<String> snoList = shareTipDAO.getSListByRecommend(map);
		List<ShareTipDTO> myRecommendedSList = new ArrayList<ShareTipDTO>();
		
		for(String sno : snoList){
			myRecommendedSList.add(shareTipDAO.shareBySno(sno));
		}
		
		int total = shareTipDAO.myRecommandTotalCount(map.get("id"));
		PagingBean paging=  new PagingBean(total, Integer.parseInt(pageNo));
		paging.setNumberOfContentPerPage(numberOfContentPerPage);
		paging.setNumberOfPageGroup(numberOfPageGroup);
		ShareTipListDTO shareTipListDTO = new ShareTipListDTO(myRecommendedSList, paging);
		return shareTipListDTO;
	}

	@Override
	public ShareTipDTO shareBySno(String sno) throws SQLException {
		return shareTipDAO.shareBySno(sno);
	}
	
	//ver5 추가수정
	@Override
	public List<ShareTipDTO> showShareListInDetail(HashMap<String, String> map) throws SQLException {
		if(map.get("category").equals("seeAll")) map.put("category", null);

		HashMap<String, Object> map1 = shareTipDAO.showShareListInDetail(map);
		List<ShareTipDTO> list = new ArrayList<ShareTipDTO>();
		
		if(map1 != null){
			Set keySet = map1.keySet();
			Iterator it = keySet.iterator();
			while(it.hasNext()){
				String key = (String)it.next();
				if(map1.get(key) != null){
					list.add(shareTipDAO.shareBySno(map1.get(key).toString()));
				}
			}
		}
		return list;
	}
}

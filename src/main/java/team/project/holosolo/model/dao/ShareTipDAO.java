package team.project.holosolo.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.ShareTipDTO;

public interface ShareTipDAO {
	// ver 1,2
	public abstract int sharePost(ShareTipDTO sdto) throws SQLException;
	public abstract ShareTipDTO shareShowDetail(String sno) throws SQLException;
	// ver 5 수정(argument HashMap으로 수정)
	public abstract List<ShareTipDTO> shareList(HashMap<String, String> map) throws SQLException;
	public abstract int shareRecommend(HashMap<String, String> map) throws SQLException;
	public abstract List<ShareTipDTO> shareSearch(HashMap<String, String> map) throws SQLException;
	public abstract int shareUpdate(ShareTipDTO sdto)throws SQLException;
	public abstract int shareDelete(String sno)throws SQLException;
	// ver 3
	public abstract List<String> shareRecommendCount(String sno);
	public abstract List<String> getSListByRecommend(HashMap<String, String> map) throws SQLException;
	// ver 4
	public abstract int updateHits(String sno) throws SQLException;
	// 12.09 추가수정
	public abstract List<ShareTipDTO> myActivityShare(HashMap<String, String> map) throws SQLException;
	public abstract ShareTipDTO shareBySno(String sno) throws SQLException;
	// ver 5
	public abstract int totalCount(String category)throws SQLException;
	// 12.11 수정
	public abstract HashMap<String, Object> showShareListInDetail(HashMap<String, String> map) throws SQLException;
	
	// 추가 12.09
	public abstract int myTotalCount(String id)throws SQLException;
	public abstract int searchTotalCount(HashMap<String, String> map) throws SQLException;
	
	// 추가 12.10
	public abstract int myRecommandTotalCount(String id) throws SQLException;
}

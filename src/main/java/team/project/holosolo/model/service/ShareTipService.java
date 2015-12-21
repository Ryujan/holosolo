package team.project.holosolo.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.ShareTipDTO;
import team.project.holosolo.model.dto.ShareTipListDTO;

public interface ShareTipService {
	// ver 1,2
	public abstract int sharePost(ShareTipDTO sdto) throws SQLException;
	public abstract ShareTipDTO shareShowDetail(String sno) throws SQLException;
	// ver 5 수정 return type,인자값
	public abstract ShareTipListDTO shareList(HashMap<String, String> map) throws SQLException;
	public abstract int shareRecommend(HashMap<String, String> map) throws SQLException;
	// ver 5 수정 return type // 12.09 추가수정
	public abstract ShareTipListDTO shareSearch(HashMap<String, String> map) throws SQLException;
	public abstract int shareUpdate(ShareTipDTO sdto)throws SQLException;
	public abstract int shareDelete(String sno)throws SQLException;
	// ver 3
	public abstract List<String> shareRecommendCount(String sno);
	
	//12.10 수정
	public abstract ShareTipListDTO getSListByRecommend(HashMap<String, String> map) throws SQLException;
	// ver 4
	public abstract int updateHits(String sno) throws SQLException;
	// 12.09 추가수정
	public abstract ShareTipListDTO myActivityShare(HashMap<String, String> map) throws SQLException;
	public abstract ShareTipDTO shareBySno(String sno) throws SQLException;
	// ver 5
	//12.11 수정
	public abstract List<ShareTipDTO> showShareListInDetail(HashMap<String, String> map) throws SQLException;
}

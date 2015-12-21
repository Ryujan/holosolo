package team.project.holosolo.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.ContentDTO;

public interface ContentDAO {
	// ver1,2
	public abstract int contentPost(ContentDTO cdto) throws SQLException;
	// ver 5 수정 인자값
	public abstract List<ContentDTO> contentList(HashMap<String, String> map) throws SQLException;
	public abstract ContentDTO contentShowDetail(String cno) throws SQLException;
	public abstract int contentRecommend(HashMap<String, String> map) throws SQLException;
	public abstract List<ContentDTO> contentSearch(HashMap<String, String> map) throws SQLException;
	public abstract int contentUpdate(ContentDTO cdto) throws SQLException;
	public abstract int contentDelete(String cno) throws SQLException;
	// ver3
	public abstract List<String> contentRecommendCount(String cno) throws SQLException;
	//12.10 인자값 수정
	public abstract List<String> getCListByRecommend(HashMap<String, String> map) throws SQLException;
	// ver4
	public abstract int updateHits(String cno) throws SQLException;
	public abstract ContentDTO contentByCno(String cno) throws SQLException;
	
	// ver 5 추가
	//추가 12.07
	public abstract List<ContentDTO> showContentListInDetail(String category) throws SQLException;
	public abstract int totalCount(String category)throws SQLException;
	//12.10 추가
	public abstract int searchTotalCount(HashMap<String, String> map) throws SQLException;
	public abstract int myRecommandTotalCount(String id) throws SQLException;
}

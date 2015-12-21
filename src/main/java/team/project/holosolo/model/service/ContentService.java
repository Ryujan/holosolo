package team.project.holosolo.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.ContentDTO;
import team.project.holosolo.model.dto.ContentListDTO;

public interface ContentService {
	// ver1,2
	public abstract int contentPost(ContentDTO cdto) throws SQLException;
	// ver 5 수정 리턴타입, 인자값
	public abstract ContentListDTO contentList(HashMap<String, String> map) throws SQLException;
	public abstract ContentDTO contentShowDetail(String cno) throws SQLException;
	public abstract int contentRecommend(HashMap<String, String> map) throws SQLException;
	//12.10 리턴값 수정
	public abstract ContentListDTO contentSearch(HashMap<String, String> map) throws SQLException;
	public abstract int contentUpdate(ContentDTO cdto) throws SQLException;
	public abstract int contentDelete(String cno) throws SQLException;
	// ver3
	public abstract List<String> contentRecommendCount(String cno) throws SQLException;
	//12.10 수정
	public abstract ContentListDTO getCListByRecommend(HashMap<String, String> map) throws SQLException;
	// ver4
	public abstract int updateHits(String cno) throws SQLException;
	public abstract ContentDTO contentByCno(String cno) throws SQLException;
	
	// ver 5 추가
	public abstract List<ContentDTO> showContentListInDetail(String category) throws SQLException;
}

package team.project.holosolo.model.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.project.holosolo.model.dao.ShareTipDAO;
import team.project.holosolo.model.dto.ShareTipDTO;

public class ShareTipDAOImpl implements ShareTipDAO{
	
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int sharePost(ShareTipDTO sdto) throws SQLException {
		return sqlSession.insert("ShareTips.sharePost", sdto);
	}

	@Override
	public ShareTipDTO shareShowDetail(String sno) throws SQLException {
		return sqlSession.selectOne("ShareTips.shareShowDetail", sno);
	}
	
	//ver 5 수정
	@Override
	public List<ShareTipDTO> shareList(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("ShareTips.shareList", map);
	}

	@Override
	public int shareRecommend(HashMap<String, String> map) throws SQLException {
		return sqlSession.insert("ShareTips.shareRecommend", map);
	}

	@Override
	public List<ShareTipDTO> shareSearch(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("ShareTips.shareSearch", map);
	}

	@Override
	public int shareUpdate(ShareTipDTO sdto) throws SQLException {
		return sqlSession.update("ShareTips.shareUpdate", sdto);
	}

	@Override
	public int shareDelete(String sno) throws SQLException {
		return sqlSession.update("ShareTips.shareDelete", sno);
	}

	@Override
	public int updateHits(String sno) throws SQLException {
		return sqlSession.update("ShareTips.updateHits", sno);
	}

	@Override
	public List<String> shareRecommendCount(String sno) {
		return sqlSession.selectList("ShareTips.shareRecommendCount",sno);
	}
	
	// 12.09 추가수정
	@Override
	public List<ShareTipDTO> myActivityShare(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("ShareTips.myActivityShare", map);
	}
	
	// 12.10 인자값 수정
	@Override
	public List<String> getSListByRecommend(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("ShareTips.getSListByRecommend", map);
	}

	@Override
	public ShareTipDTO shareBySno(String sno) throws SQLException {
		return sqlSession.selectOne("ShareTips.shareBySno",sno);
	}
	
	//ver5 수정
	@Override
	public int totalCount(String category) throws SQLException {
		return sqlSession.selectOne("ShareTips.totalCount",category);
	}

	//12.11수정
	@Override
	public HashMap<String, Object> showShareListInDetail(HashMap<String, String> map) throws SQLException{
		return sqlSession.selectOne("ShareTips.showShareListInDetail", map);
	}

	// 12.09 추가수정
	@Override
	public int myTotalCount(String id) throws SQLException {
		return sqlSession.selectOne("ShareTips.myTotalCount",id);
	}
	
	// 12.09 추가수정
	@Override
	public int searchTotalCount(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectOne("ShareTips.searchTotalCount",map);
	}

	//12.10 추가
	@Override
	public int myRecommandTotalCount(String id) throws SQLException {
		return sqlSession.selectOne("ShareTips.myRecommandTotalCount", id);
	}
}

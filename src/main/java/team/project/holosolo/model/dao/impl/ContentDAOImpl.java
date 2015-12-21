package team.project.holosolo.model.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import team.project.holosolo.model.dao.ContentDAO;
import team.project.holosolo.model.dto.ContentDTO;

public class ContentDAOImpl implements ContentDAO{
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public int contentPost(ContentDTO cdto) throws SQLException {
		return sqlSession.insert("Content.contentPost", cdto);
	}

	// ver 5 수정
	@Override
	public List<ContentDTO> contentList(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("Content.contentList", map);
	}

	@Override
	public ContentDTO contentShowDetail(String cno) throws SQLException {
		return sqlSession.selectOne("Content.contentShowDetail", cno);
	}

	@Override
	public int contentRecommend(HashMap<String, String> map)
			throws SQLException {
		return sqlSession.insert("Content.contentRecommend", map);
	}

	@Override
	public List<ContentDTO> contentSearch(HashMap<String, String> map)
			throws SQLException {
		return sqlSession.selectList("Content.contentSearch", map);
	}

	@Override
	public int contentUpdate(ContentDTO cdto) throws SQLException {
		return sqlSession.update("Content.contentUpdate", cdto);
	}

	@Override
	public int contentDelete(String cno) throws SQLException {
		return sqlSession.delete("Content.contentDelete", cno);
	}

	@Override
	public List<String> contentRecommendCount(String cno) throws SQLException {
		return sqlSession.selectList("Content.contentRecommendCount",cno);
	}
	
	@Override
	public int updateHits(String cno) throws SQLException {
		return sqlSession.update("Content.updateHits", cno);
	}
	
	//12.10 수정
	@Override
	public List<String> getCListByRecommend(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectList("Content.getCListByRecommend", map);
	}

	@Override
	public ContentDTO contentByCno(String cno) throws SQLException {
		return sqlSession.selectOne("Content.contentByCno", cno);
	}
	
	// ver 5 추가
	@Override
	public int totalCount(String category) throws SQLException {
		return sqlSession.selectOne("Content.totalCount",category);
	}

	@Override
	public List<ContentDTO> showContentListInDetail(String category)
			throws SQLException {
		return sqlSession.selectList("Content.showContentListInDetail",category);
	}
	
	// 12.10 추가수정
	@Override
	public int searchTotalCount(HashMap<String, String> map) throws SQLException {
		return sqlSession.selectOne("Content.searchTotalCount",map);
	}

	@Override
	public int myRecommandTotalCount(String id) throws SQLException {
		return sqlSession.selectOne("Content.myRecommandTotalCount", id);
	}
}

package team.project.holosolo.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team.project.holosolo.model.dao.impl.MemberDAOImpl;
import team.project.holosolo.model.dao.impl.ShareTipDAOImpl;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.dto.ShareTipDTO;

public class ShareTipsDAOAppTest {
	public static void main(String[] args) throws IOException, SQLException {
		ShareTipDAOImpl dao = new ShareTipDAOImpl();
		MemberDAOImpl mdao = new MemberDAOImpl();
		
		Reader reader = Resources.getResourceAsReader("config/sqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		
		SqlSession sqlSession = factory.openSession();
		dao.setSqlSession(sqlSession);
		SqlSession sqlSession1 = factory.openSession();
		mdao.setSqlSession(sqlSession1);
		
	/*	MemberDTO member1 = new MemberDTO("pjk1", "1234", "박정기", "m", "pjk1@naver.com");
		MemberDTO member2 = new MemberDTO("pjw1", "1234", "박종완", "m", "pjw1@naver.com");*/
		HashMap<String, String> hashmap = new HashMap<String, String>();
		/*hashmap.put("id", "pjk1");*/
		/*hashmap.put("id", "pjw1");*/
		/*hashmap.put("sno", "3");*/
		/*hashmap.put("sno", "4");*/
		hashmap.put("category", "공부");
		hashmap.put("word", "content");
		
		/*mdao.memberRegister(new MemberDTO("pjk1", "1234", "박정기", "m", "pjk1@naver.com", true));*/
		/*mdao.memberRegister(member2);*/
		/*dao.sharePost(new ShareTipDTO(0, "asdf", "asdf", "음식", "", 0, new MemberDTO("pjk1", "1234", "박정기", "m", "pjk1@naver.com", true)));*/
		/*dao.sharePost(new ShareTipDTO(0, "title", "content", "공부", "", 0, member2));*/
//		dao.sharePost(new ShareTipDTO(0, "title123", "content123", "공부", "", 0, member2));
		/*List<ShareTipDTO> list = dao.shareList("음식");
		for(ShareTipDTO dto : list){
			System.out.println(dto);
		}*/
		/*dao.shareRecommend(hashmap);*/
		
		List<ShareTipDTO> list = dao.shareSearch(hashmap);
		for(ShareTipDTO dto : list){
			System.out.println(dto);
		}
		sqlSession.commit();
		sqlSession1.commit();
	}
}

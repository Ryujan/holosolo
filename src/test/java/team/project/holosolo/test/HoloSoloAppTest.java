package team.project.holosolo.test;

import java.io.Reader;
import java.util.HashMap;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team.project.holosolo.model.dao.impl.ContentDAOImpl;
import team.project.holosolo.model.dao.impl.MemberDAOImpl;
import team.project.holosolo.model.dto.ContentDTO;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.service.impl.ContentServiceImpl;
import team.project.holosolo.model.service.impl.MemberServiceImpl;

public class HoloSoloAppTest {

	public static void main(String[] args) throws Exception{
		Reader reader = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession sqlSession = factory.openSession();	
		
		MemberServiceImpl mservice = new MemberServiceImpl();
		MemberDAOImpl mdao = new MemberDAOImpl();
		mservice.setMemberDAO(mdao);
		mdao.setSqlSession(sqlSession);
		
		ContentServiceImpl cservice = new ContentServiceImpl();
		ContentDAOImpl cdao = new ContentDAOImpl();
		cservice.setContentDAO(cdao);
		cdao.setSqlSession(sqlSession);
		///////// member /////////////////
//		MemberDTO mdto = new MemberDTO("holo", "solo", "관리자", "M", "gofnrk1@naver.com");
//		mservice.memberRegister(mdto);
		
		
//		MemberDTO dto = new MemberDTO("park", "kkkk", "관리자", "M", "123@kosta.com");
//		mservice.memberUpdate(dto);
		
//		mservice.memberDelete(dto);
		
//		MemberDTO dto = new MemberDTO("holo", "solo");
//		System.out.println(mservice.login(dto));


		// ======================== Contents ==============================
//		ContentDTO cdto = new ContentDTO(0, "제목bbb", "내bbbb", "음료수", "", 0, mdto);
//		cservice.contentsPost(cdto);
		
//		System.out.println(cservice.contentsShowDetail("4"));
		
//		System.out.println(cservice.contentsList("음료수"));
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("id", "holo");
//		map.put("cno", "4");
//		cservice.contentsRecommend(map);
		
//		map.put("category", "건강");
//		map.put("word", "2");
//		map.put("sort", "1");
		
//		System.out.println(cservice.contentsSearch(map));
	}
}

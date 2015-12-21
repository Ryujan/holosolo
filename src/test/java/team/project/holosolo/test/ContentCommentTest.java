package team.project.holosolo.test;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team.project.holosolo.model.dao.ContentCommentDAO;
import team.project.holosolo.model.dao.impl.ContentCommentDAOImpl;
import team.project.holosolo.model.dao.impl.ContentDAOImpl;
import team.project.holosolo.model.dao.impl.MemberDAOImpl;
import team.project.holosolo.model.dto.ContentCommentDTO;
import team.project.holosolo.model.dto.ContentDTO;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.service.ContentCommentService;
import team.project.holosolo.model.service.MemberService;
import team.project.holosolo.model.service.impl.ContentCommentServiceImpl;
import team.project.holosolo.model.service.impl.ContentServiceImpl;
import team.project.holosolo.model.service.impl.MemberServiceImpl;

public class ContentCommentTest{
	public static void main(String[] args) throws SQLException, IOException{		

	//Reader reader=Resources.getResourceAsReader("config/SqlMapConfig.xml");
	//SqlSession session=null;
	//SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
	//session=factory.openSession();
	
	//MemberDTO mdto=new MemberDTO("id", "password", "name", "f", "email");
	//System.out.println("member insert ::"+session.insert("Member.memberRegister",mdto));
	//session.commit();
	//System.out.println(mdto);
	
	//ContentDTO cdto=new ContentDTO(1, "title", "content", "category", "1", 1,mdto);
	//System.out.println("content insert::"+session.insert("Content.contentsPost",cdto));
	//session.commit();
	
	
	//ContentCommentDTO ccdto=new ContentCommentDTO(1, "reply", "category", "1", 1, mdto);
	//System.out.println("test1");
	//System.out.println("insert :" + session.insert("ContentComment.contentsCommentWrite", ccdto));
	//System.out.println("test2");
	//session.commit();
	
	//System.out.println("select :"+session.selectList("ContentComment.contentsCommentList", 1));
	
	//System.out.println("delete :"+session.delete("ContentComment.contentsCommentDelete","id"));
	//session.commit();				
		
		
//		MemberDTO mdto=new MemberDTO("ydh123", "1111", "유다형", "f", "제발좀..");
//		ContentDTO cdto=new ContentDTO(1, "정신차립시다", "네? 제발", "공부", "", 0, mdto);
		
//		ContentCommentDTO ccdto=new ContentCommentDTO(1, "정신을", "차리자", "", 1, mdto);
		
	    Reader reader = Resources.getResourceAsReader("config/sqlMapConfig.xml");
	    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
	    SqlSession sqlSession1 = factory.openSession();
	    SqlSession sqlSession2 = factory.openSession();
	    SqlSession sqlSession3 = factory.openSession();
	    
	    MemberDAOImpl dao = new MemberDAOImpl();
	    ContentDAOImpl cdao = new ContentDAOImpl();
	    ContentCommentDAOImpl ccdao = new ContentCommentDAOImpl();
	    
	    MemberServiceImpl mser = new MemberServiceImpl();
	    ContentServiceImpl cser = new ContentServiceImpl();
	    ContentCommentServiceImpl ccser = new ContentCommentServiceImpl();
	    
	    dao.setSqlSession(sqlSession1);
	    cdao.setSqlSession(sqlSession2);
	    ccdao.setSqlSession(sqlSession3);
	    
	    mser.setMemberDAO(dao);
	    cser.setContentDAO(cdao);
	    ccser.setContentCommentDAO(ccdao);
	    
	    /*mser.memberRegister(mdto);*/
	   /* cser.contentsPost(cdto);*/
	   /* ccser.contentsCommentWrite(ccdto);*/
	    /*List<ContentCommentDTO> list = ccser.contentsCommentList(1);
	    for(ContentCommentDTO asd : list){
	    	System.out.println(asd);
	    }*/
	   /* ccser.contentsCommentDelete("ydh123");*/
	    
	    sqlSession1.commit();
	    sqlSession2.commit();
	    sqlSession3.commit();
	}
}

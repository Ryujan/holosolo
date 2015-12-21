package team.project.holosolo.test;

import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team.project.holosolo.model.dao.ContentCommentDAO;
import team.project.holosolo.model.dto.ContentCommentDTO;
import team.project.holosolo.model.dto.ContentDTO;
import team.project.holosolo.model.dto.MemberDTO;

public class ContentCommentAppTest{
	public static void main(String[] args){
		
		try{
	Reader reader=Resources.getResourceAsReader("config/SqlMapConfig.xml");
	SqlSession session=null;
	SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(reader);
	session=factory.openSession();
	
//	MemberDTO mdto=new MemberDTO("id","pa","name","f","email");
	//System.out.println("member insert ::"+session.insert("Member.memberRegister",mdto));
	//session.commit();
	//System.out.println(mdto);
	
	//ContentDTO cdto=new ContentDTO(1, "title", "content", "category", "1", 1,mdto);
	//System.out.println("content insert::"+session.insert("Content.contentsPost",cdto));
	//session.commit();
	
	
//	ContentCommentDTO ccdto=new ContentCommentDTO(1, "reply", "category", "1", 1, mdto);
	//System.out.println("test1");
	//System.out.println("insert :" + session.insert("ContentComment.contentsCommentWrite", ccdto));
	//System.out.println("test2");
	//session.commit();
	
	System.out.println("select :"+session.selectList("ContentComment.contentsCommentList", 1));
	
	//System.out.println("delete :"+session.delete("ContentComment.contentsCommentDelete","id"));
	//session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	}
}

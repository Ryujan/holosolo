package team.project.holosolo.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import team.project.holosolo.model.dto.GroupCommentDTO;
import team.project.holosolo.model.dto.GroupDTO;
import team.project.holosolo.model.dto.MemberDTO;

public class MemberAndContentAppTest {

	public static void main(String[] args) throws Exception {
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		SqlSession session = factory.openSession();
		
		//member
//		MemberDTO dto = new MemberDTO("holo", "solo", "관리자", "W", "kosta.com");
//		session.insert("Member.memberRegister",dto);
		session.commit();
		System.out.println(session.selectList("Member.memberList")); 
		
		//member
		/*MemberDTO dto = new MemberDTO("ghyun", "123", "김지현", "W", "ghyun@naver.com");
		session.insert("Member.memberRegister",dto);
		session.commit();
		System.out.println(session.selectList("Member.memberList"));*/
		
		//groupPost
//		GroupDTO dto = new GroupDTO(0, "제목은", "내용은", "스포츠", "14-11-11", "W", 1, 2, 0, new MemberDTO("ghyun", "123"));			
//		GroupDTO dto = new GroupDTO(0, "모임을정하자", "더워", "문화", "14-12-12", "W", 1, 2, 0, new MemberDTO("ghyun", "123"));			
//		GroupDTO dto = new GroupDTO(0, "제목은", "내용은", "스포츠", "11-12-12", "W", new MemberDTO("ghyun", "123"));
//		session.insert("Group.groupPost", dto);
//		session.commit();
//		System.out.println(session.selectList("Group.groupList"));
		
		//groupshowDetail
//		System.out.println(session.selectOne("Group.groupShowDetail",15));
		
		//groupCommentWrite
//		GroupCommentDTO dto = new GroupCommentDTO(0, "gg", "레포츠", "", 14, new MemberDTO("ghyun", "123"));
//		session.insert("GroupComment.groupCommentWrite", dto);
//		session.commit();
		
		//groupCommentDelete
//		session.delete("GroupComment.groupCommentDelete", "ghyun");
//		session.commit();
		
		//groupCommentList
		//System.out.println(session.selectList("GroupComment.groupCommentList",15));
		}	

}

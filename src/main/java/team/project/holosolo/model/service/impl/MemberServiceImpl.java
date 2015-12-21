package team.project.holosolo.model.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dao.MemberDAO;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.service.MemberService;

public class MemberServiceImpl implements MemberService{
	private MemberDAO memberDAO;
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public int memberRegister(MemberDTO mdto) throws SQLException {
		return memberDAO.memberRegister(mdto);
	}

	@Override
	public MemberDTO login(MemberDTO mdto) throws SQLException {
		return memberDAO.login(mdto);
	}

	@Override
	public int memberUpdate(MemberDTO mdto) throws SQLException {		
		return memberDAO.memberUpdate(mdto);
	}

	@Override
	public int memberDelete(MemberDTO mdto) throws SQLException {
		return memberDAO.memberDelete(mdto);
	}

	@Override
	public List<MemberDTO> memberList() throws SQLException {
		return memberDAO.memberList();
	}
	
	@Override
	public boolean idCheck(String id) throws SQLException {
		boolean result = false;
		if(memberDAO.idCheck(id) != null){ //이미 디비에 있다면....
			result = true;
		}
		return result;
	}

	@Override
	public int myPhotoUpdate(HashMap<String, String> map) throws SQLException {
		return memberDAO.myPhotoUpdate(map);
	}
	
	@Override
    public MemberDTO findPassword(HashMap<String, String> map) throws SQLException {
        return memberDAO.findPassword(map);
    }
}

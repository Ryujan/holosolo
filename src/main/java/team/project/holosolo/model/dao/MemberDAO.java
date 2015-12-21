package team.project.holosolo.model.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import team.project.holosolo.model.dto.MemberDTO;

public interface MemberDAO {
	// ver 1,2
	public abstract int memberRegister(MemberDTO mdto) throws SQLException;
	public abstract MemberDTO login(MemberDTO mdto) throws SQLException;
	public abstract int memberUpdate(MemberDTO mdto) throws SQLException;
	public abstract int memberDelete(MemberDTO mdto) throws SQLException;
	public abstract List<MemberDTO> memberList() throws SQLException;
	// ver 3
	public abstract Object idCheck(String id) throws SQLException;
	public abstract int myPhotoUpdate(HashMap<String, String> map) throws SQLException;
	
	public abstract MemberDTO findPassword(HashMap<String, String> map) throws SQLException;
}

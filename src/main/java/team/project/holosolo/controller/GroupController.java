package team.project.holosolo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.GroupCommentDTO;
import team.project.holosolo.model.dto.GroupDTO;
import team.project.holosolo.model.dto.GroupListDTO;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.service.GroupCommentService;
import team.project.holosolo.model.service.GroupService;

public class GroupController extends MultiActionController {
	// field
	public GroupService groupService;
	public GroupCommentService groupCommentService;

	// setter
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public void setGroupCommentService(GroupCommentService groupCommentService) {
		this.groupCommentService = groupCommentService;
	}

	/*
	 ***************************************** ver 1,2 *****************************************
	 */
	
	// group 게시판 글 등록
	public ModelAndView groupPost(HttpServletRequest request, HttpServletResponse response, GroupDTO gdto) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String promiseAddr = request.getParameter("promiseAddr");
		
		gdto.setMemberDTO(memberDTO);
		String content = gdto.getContent();
		content = content.replaceAll("src=\"../", "src=\"");
		String fileName = "noImage.jpg";
		int startIndex = 0;
		int endIndex = 0;

		// 파일 확장자 명과 확장자 명의 인덱스를 찾는 로직
		String[] allow_file = { "jpg", "png", "bmp", "gif", "JPG", "PNG", "BMP", "GIF" }; // 허용되는 확장자.
		String ext = null;
		for (int i = 0; i < allow_file.length; i++) {
			if (content.contains(allow_file[i])) {
				ext = "." + allow_file[i];
				startIndex = content.indexOf("uploadImg/");
				endIndex = content.indexOf(ext);
			}
		}

		// 파일명을 추출하는 로직
		if (startIndex != -1 && startIndex != 0) {
			fileName = content.substring(startIndex, endIndex);
			int index = fileName.lastIndexOf('/');
			fileName = fileName.substring(index+1).concat(ext);
		}

		gdto.setFileName(fileName);
		gdto.setContent(content);
		gdto.setPromiseAddr(promiseAddr);
		// 추가 위도&경도

		groupService.groupPost(gdto);
		GroupDTO myGroup = groupService.getMyGroup(memberDTO.getId());

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", memberDTO.getId());
		map.put("gno", String.valueOf(myGroup.getGno()));
		groupService.groupJoin(map);

		return new ModelAndView("redirect:/group.do?command=groupList&&category=seeAll");
	}
	
	// ver 5 추가 12.10 추가수정
	// group 게시판 리스트 보기
	public ModelAndView groupList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<GroupDTO> groupList = new ArrayList<GroupDTO>();
		GroupListDTO groupListDTO = null;
		String category = request.getParameter("category");
		String pageNo = request.getParameter("pageNo");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pageNo", pageNo);
		if (category.equals("seeAll")) {
			map.put("category", null);
			groupListDTO = groupService.groupList(map);
			
		} else {
			map.put("category", category);
			groupListDTO = groupService.groupList(map);
		}
		request.setAttribute("category",category);
		//12.10추가수정
		request.setAttribute("beforeCommand", "groupList");
		return new ModelAndView("views/groups/group_list", "groupListDTO", groupListDTO);
	}
	
	// group 상세 페이지
	public ModelAndView groupShowDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		/*
		 * 변경된 점 :: 그룹 페이지의 변경으로 인해서 Detail 페이지에 들어가야 할 값은 아래와 같다.
		 * 1. 글 정보 :: 이 때, member_groups의 list의 갯수를 가져와서 current_num에 주입한다.
		 * 2. 주최자 정보
		 * 3. 참여자 id list
		 * 4. 댓글 list
		 */
		String gno = request.getParameter("gno");
		String replyPageNo = request.getParameter("replyPageNo");
		
		// 1. 글 정보 완료.
		HashMap<String, Object> mapForDetail = groupService.groupShowDetail(gno);
		GroupDTO groupDTO = (GroupDTO)mapForDetail.get("groupDTO");
		String category = groupDTO.getCategory();
		int currentNum = groupService.getMemberGroupByGno(gno).size();
		groupDTO.setCurrentNum(currentNum);
		
		// 2. 주최자 정보.
		MemberDTO writerInfo = (MemberDTO)mapForDetail.get("memberDTO"); // 주최자 정보를 가져옴.
		// 3. 참여자 id List
		List<String> entry = groupService.getMemberGroupByGno(gno); // id List를 가져옴.
		// 4. 댓글 List
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("gno", gno);
		map.put("replyPageNo", replyPageNo);
		map.put("category", category);
		
		CommentListByPageDTO commentListByPageDTO = groupCommentService.groupCommentList(map);
		// 5. 현재 그룹에 참가한 사람인지 아닌지를 판별하는 부분.
		HashMap<String, String> mapForIdentifier = new HashMap<String, String>();
		
		boolean result = false;
		
		mapForIdentifier.put("id", memberDTO.getId());
		mapForIdentifier.put("gno", gno);

		if(groupService.checkGroupJoined(mapForIdentifier) != null) 
			result = true;
		
		// End & Binding.
		request.setAttribute("groupDTO", groupDTO);
		request.setAttribute("writerInfo", writerInfo);
		request.setAttribute("entry", entry);
		request.setAttribute("commentListByPageDTO", commentListByPageDTO);
		request.setAttribute("result", result);

		return new ModelAndView("views/groups/group_showDetail");
	}
	
	/*
	 ***************************************** ver 3 *****************************************
	 */

	// group 참가
	public ModelAndView groupJoin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String gno = request.getParameter("gno");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", memberDTO.getId());
		map.put("gno", gno);

		groupService.groupJoin(map); // 그룹 참가.
		groupService.increaseCurrentNum(gno); // 그룹에 한 명 추가함.

		return new ModelAndView("redirect:/group.do?command=groupShowDetail&&gno=" + gno);
	}

	// 모임 참가 취소하기
	public ModelAndView groupLeave(HttpServletRequest request, HttpServletResponse response, GroupDTO gdto) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String gno = request.getParameter("gno");

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", memberDTO.getId());
		map.put("gno", gno);

		groupService.groupLeave(map); // member_groups 에서 제거 완료.
		groupService.decreaseCurrentNum(gno); // group에서 현재 인원 한 명 감소.

		return new ModelAndView("redirect:/group.do?command=groupShowDetail&&gno="+ gdto.getGno());
	}
	
	// 컨텐츠 업데이트를 위한 해당 컨텐츠의 상세정보를 보냄
	public ModelAndView updateView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String gno = request.getParameter("gno");
		GroupDTO groupDTO = (GroupDTO)groupService.groupShowDetail(gno).get("groupDTO"); // map방식이라서..

		String content = groupDTO.getContent();
		content = content.replaceAll("src=\"", "src=\"../");
		groupDTO.setContent(content);

		return new ModelAndView("views/groups/group_update", "groupDTO", groupDTO);
	}

	// 폼에 수정된 컨텐츠 내용 업데이트
	public ModelAndView groupUpdate(HttpServletRequest request, HttpServletResponse response, GroupDTO gdto) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String content = request.getParameter("content");
		content = content.replaceAll("src=\"../", "src=\"");
		String fileName = "noImage.jpg";
		int startIndex = 0;
		int endIndex = 0;

		// 파일 확장자 명과 확장자 명의 인덱스를 찾는 로직
		String[] allow_file = { "jpg", "png", "bmp", "gif", "JPG", "PNG", "BMP", "GIF" }; // 허용되는 확장자.
		String ext = null;
		for (int i = 0; i < allow_file.length; i++) {
			if (content.contains(allow_file[i])) {
				ext = "." + allow_file[i];
				startIndex = content.indexOf("uploadImg/");
				endIndex = content.indexOf(ext);
			}
		}

		// 파일명을 추출하는 로직
		if (startIndex != -1 && startIndex != 0) {
			fileName = content.substring(startIndex, endIndex);
			int index = fileName.lastIndexOf('/');
			fileName = fileName.substring(index+1).concat(ext);
		}
		
		gdto.setFileName(fileName);
		gdto.setContent(content);

		int result = groupService.groupUpdate(gdto);
		if (result == 1)
			request.setAttribute("groupDTO", gdto);

		return new ModelAndView("redirect:/group.do?command=groupShowDetail&&gno="+ gdto.getGno());
	}
	
	// 모임 취소
	public ModelAndView groupCancel(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String gno = request.getParameter("gno");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("status", "-1");
		map.put("gno", gno);
		groupService.updateStatus(map);
		// 여기까지가 그룹의 상태 변경.
		
		groupService.groupCancel(gno); // 그룹에 가입된 모든 사람을 삭제한다.
		
		return new ModelAndView("redirect:/group.do?command=groupShowDetail&&gno="+gno);
	}

	/*
	 ***************************************** ver 4 *****************************************
	 */
	
	// group 게시글 댓글 작성
	public ModelAndView groupCommentWrite(HttpServletRequest request, HttpServletResponse response, GroupCommentDTO g_commentDTO) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
			if(memberDTO == null){
				return new ModelAndView("redirect:/views/error_session.jsp");
			}
		int gno = Integer.parseInt(request.getParameter("gno"));

		g_commentDTO.setGno(gno);
		g_commentDTO.setMemberDTO(memberDTO);
		
		groupCommentService.groupCommentWrite(g_commentDTO);
		
		return new ModelAndView("redirect:/group.do?command=groupShowDetail&&gno="+gno);
	}
	
	// group 게시글 댓글 삭제
	public ModelAndView groupCommentDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String rno = request.getParameter("rno");
		String gno = request.getParameter("gno");
		
		groupCommentService.groupCommentDelete(rno);
		
		return new ModelAndView("redirect:/group.do?command=groupShowDetail&&gno="+gno);
	}
	
	// 12.10 추가수정
	// 내가 참여한 그룹 가져오기
	public ModelAndView myJoinedGroup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		GroupListDTO groupListDTO = null;
		String id = request.getParameter("id");
		String pageNo = request.getParameter("pageNo");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pageNo", pageNo);
		map.put("id", id);
		
		if(id == null){
			map.put("id", ((MemberDTO)request.getSession().getAttribute("memberDTO")).getId());
		}
		groupListDTO = groupService.myJoinedGroup(map);
		request.setAttribute("category", "seeAll");
		//12.10추가수정
		request.setAttribute("beforeCommand", "myJoinedGroup");
		return new ModelAndView("views/groups/group_list", "groupListDTO", groupListDTO);
	}
}

package team.project.holosolo.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import team.project.holosolo.model.dto.ContentListDTO;
import team.project.holosolo.model.dto.GroupDTO;
import team.project.holosolo.model.dto.ShareTipListDTO;
import team.project.holosolo.model.service.ContentService;
import team.project.holosolo.model.service.GroupService;
import team.project.holosolo.model.service.ShareTipService;

public class MainController extends MultiActionController{
	// 메인 페이지에서 모든 테이블을 컨트롤 하기 위해 3개의 서비스를 해징한다.
	private ContentService contentService;
	private GroupService groupService;
	private ShareTipService shareTipService;
	
	//setter
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public void setShareTipService(ShareTipService shareTipService) {
		this.shareTipService = shareTipService;
	}
	
	//ver 5 수정
	// 메인페이지의 목록을 바로 뿌려주기 위한 함수
	public ModelAndView showMain(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		//추가 12.07
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("category", null);
		map.put("pageNo", null);
		
		// ver 5 수정
		ContentListDTO contentListDTO = contentService.contentList(map);
		ShareTipListDTO shareTipListDTO = shareTipService.shareList(map);
		List<GroupDTO> groupList = groupService.indexGroupList();
		
		// ver 5 수정
		request.setAttribute("contentListDTO", contentListDTO);
		request.setAttribute("shareTipListDTO", shareTipListDTO);
		request.setAttribute("groupList", groupList);
		
		return new ModelAndView("/views/index");
	}
	
	public ModelAndView introduce(HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		
		return new ModelAndView("/views/introduce");
	}
}

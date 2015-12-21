package team.project.holosolo.advice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;

import team.project.holosolo.model.dto.ContentDTO;
import team.project.holosolo.model.dto.ContentListDTO;
import team.project.holosolo.model.dto.GroupDTO;
import team.project.holosolo.model.dto.GroupListDTO;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.dto.ShareTipDTO;
import team.project.holosolo.model.dto.ShareTipListDTO;
import team.project.holosolo.model.service.ContentService;
import team.project.holosolo.model.service.GroupService;
import team.project.holosolo.model.service.ShareTipService;

public class MyPageAspect {
	private ContentService contentService;
	private ShareTipService shareTipService;
	private GroupService groupService;
	
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	public void setShareTipService(ShareTipService shareTipService) {
		this.shareTipService = shareTipService;
	}
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	/*
	 * 자신이 추천한 contents
	 * 자신이 추천한 sharetips
	 * 자신이 활동했던 group
	 * 자신이 작성했던 sharetips
	 * 
	 */
	// controller 간섭 Aspect.
	// 12.09 수정
	public Object showMyActivity(ProceedingJoinPoint pjp) throws Throwable{
		Object[] params = pjp.getArgs();
		HttpServletRequest request = (HttpServletRequest)params[0];
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		//쿼리문 작성된 이후.
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", null);
		map.put("pageNo", null);

		if(memberDTO != null){
			// 12.09 수정
			// 내가 작성한 Sharetip 
			map.put("id", memberDTO.getId());
			ShareTipListDTO shareTipListDTO = shareTipService.myActivityShare(map);
			
			// 내가 참여한 Group
			GroupListDTO groupListDTO = groupService.myJoinedGroup(map);
	       
			// 내가 추천한 Content
			ContentListDTO contentCommentListDTO = contentService.getCListByRecommend(map);
	         
			// 내가 추천한 Sharetip
			ShareTipListDTO shareCommentListDTO = shareTipService.getSListByRecommend(map);
			         
			request.setAttribute("shareCommentListDTO", shareCommentListDTO);
			request.setAttribute("contentCommentListDTO", contentCommentListDTO);
			// 12.09 수정
			request.setAttribute("shareTipListDTO", shareTipListDTO);
			request.setAttribute("groupListDTO", groupListDTO);
		}
		Object returnValues = pjp.proceed();
	      
		return returnValues;
	}
}
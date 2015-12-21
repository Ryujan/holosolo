package team.project.holosolo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.dto.ShareTipCommentDTO;
import team.project.holosolo.model.dto.ShareTipDTO;
import team.project.holosolo.model.dto.ShareTipListDTO;
import team.project.holosolo.model.service.ShareTipCommentService;
import team.project.holosolo.model.service.ShareTipService;

public class ShareTipController extends MultiActionController {
	//fields
	private ShareTipService shareTipService;
	private ShareTipCommentService shareTipCommentService;

	//setters()
	public void setShareTipService(ShareTipService shareTipService) {
		this.shareTipService = shareTipService;
	}
	public void setShareTipCommentService(
			ShareTipCommentService shareTipCommentService) {
		this.shareTipCommentService = shareTipCommentService;
	}

	/*
	 ***************************************** ver 1,2 *****************************************
	 */
	// ver 5 수정
	// 게시물 등록
	public ModelAndView sharePost(HttpServletRequest request, HttpServletResponse response, ShareTipDTO sdto)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		sdto.setMemberDTO(memberDTO);
		
		//수정 12.02
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
		/////
		
		sdto.setFileName(fileName);
		sdto.setContent(content);

		shareTipService.sharePost(sdto);
		// 12.8 수정 부분
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pageNo", request.getParameter("pageNo"));
		map.put("category", null);
		request.setAttribute("category", "seeAll");
		
		//12.10 추가
		request.setAttribute("beforeCommand", "shareList");
		return new ModelAndView("views/sharetips/shareTip_list", "shareTipListDTO", shareTipService.shareList(map));
	}
	
	//12.11 추가수정
	// 게시물 상세보기
	public ModelAndView shareShowDetail(HttpServletRequest request, HttpServletResponse response)throws Exception{
		System.out.println("start showdetail");
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String sno = request.getParameter("sno");
		ShareTipDTO shareTipDTO = shareTipService.shareShowDetail(sno);
		String selectedCategory = request.getParameter("category");
		String replyPageNo = request.getParameter("replyPageNo");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("sno", sno);
		map.put("category", selectedCategory);
		map.put("replyPageNo", replyPageNo);

		//ver5 수정 12.11 추가수정
		List<ShareTipDTO> shareTipList = shareTipService.showShareListInDetail(map);
		CommentListByPageDTO commentListByPageDTO = shareTipCommentService.shareCommentList(map);
		List<String> srList=shareTipService.shareRecommendCount(sno);
		boolean srflag = srList.contains(memberDTO.getId());
		int recommendCount = srList.size();
		
		request.setAttribute("shareTipDTO", shareTipDTO);
		request.setAttribute("shareTipList", shareTipList);//전체 리스트
		request.setAttribute("commentListByPageDTO", commentListByPageDTO);//댓글 리스트
		request.setAttribute("srflag", srflag);//추천 아이디 확인
		request.setAttribute("recommendCount", recommendCount);//추천수
		//12.11 추가
		request.setAttribute("selectedCategory", selectedCategory);
		
		return new ModelAndView("views/sharetips/share_showDetail");
	}
	
	// ver5 수정
	// 게시물 목록
	public ModelAndView shareList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ShareTipListDTO shareTipListDTO = null;
		String category = request.getParameter("category");
		String pageNo = request.getParameter("pageNo");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pageNo", pageNo);
		
		if(category.equals("seeAll")){
			map.put("category", null);
		}else{
			map.put("category", category);
		}
		shareTipListDTO = shareTipService.shareList(map);
		
		request.setAttribute("category", category);
		//12.10추가
		request.setAttribute("beforeCommand", "shareList");
		return new ModelAndView("views/sharetips/shareTip_list", "shareTipListDTO", shareTipListDTO);
	}

	// sharetip 추천
	public ModelAndView shareRecommend(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
			
		String id= request.getParameter("id");
		String sno= request.getParameter("sno");
			
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("sno", sno);
		shareTipService.shareRecommend(map);
			
			
		List<String> srList=shareTipService.shareRecommendCount(sno);
		int recommendCount = srList.size();//추천수를 넘긴다.
			
		return new ModelAndView("JsonView", "recommendCount", recommendCount);
	}
	
	// ver5 수정
	// 12.09 수정
	//shareTip 검색
	public ModelAndView shareSearch(HttpServletRequest request, HttpServletResponse respose)throws Exception{
		ShareTipListDTO shareTipListDTO = null;
		String category = request.getParameter("category");
		String word=request.getParameter("word");
		String sort=request.getParameter("sort");		
		// 12.09 수정
		String pageNo = request.getParameter("pageNo");
	
		// 12.10 추가
		if(word == null && sort == null){
			word = (String)request.getSession().getAttribute("word");
			sort = (String)request.getSession().getAttribute("sort");
		}
		
		HashMap<String,String> map=new HashMap<String, String>();
		if(category.equals("seeAll")){
			map.put("category", null); // 카테고리
		}else{
			map.put("category", category); // 카테고리
		}
		map.put("word", word); // 검색어
		map.put("sort", sort); // 검색종류(제목, 내용, 작성자)
		// 12.09 추가
		map.put("pageNo", pageNo);
		 	
		shareTipListDTO = shareTipService.shareSearch(map);
		request.setAttribute("category", category);
		//12.10 추가
		request.setAttribute("beforeCommand", "shareSearch");
		request.getSession().setAttribute("word", word);
		request.getSession().setAttribute("sort", sort);
		return new ModelAndView("views/sharetips/shareTip_list", "shareTipListDTO", shareTipListDTO);
	}
	
	// ver 5 추가수정
	// 게시물 업데이트를 위한 해당 게시물의 상세정보를 보냄
	public ModelAndView updateView(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String sno = request.getParameter("sno");
		
		ShareTipDTO shareTipDTO = shareTipService.shareShowDetail(sno);
		
		String content = shareTipDTO.getContent();
		content = content.replaceAll("src=\"", "src=\"../");
		shareTipDTO.setContent(content);
		
		List<String> categoryList = new ArrayList<String>();
		categoryList.add("Cooking");
		categoryList.add("Place");
		categoryList.add("Information");
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("selectedCategory", request.getParameter("category"));
		return new ModelAndView("views/sharetips/sharetip_update", "shareTipDTO", shareTipDTO);
	}
	
	// ver 5 수정
	// 폼에 수정된 게시물 내용 업데이트
	public ModelAndView shareUpdate(HttpServletRequest request, HttpServletResponse response, ShareTipDTO sdto)throws Exception{
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
		
		sdto.setFileName(fileName);
		sdto.setContent(content);
		
		shareTipService.shareUpdate(sdto);
		// 12.8 수정
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pageNo", request.getParameter("pageNo"));
		map.put("category", null);
		
		// 12.10추가
		request.setAttribute("category", request.getParameter("selectedCategory"));
		return new ModelAndView("views/sharetips/shareTip_list", "shareTipListDTO", shareTipService.shareList(map));
		
	}
	
	// ver 5 수정
	//게시물 삭제
	public ModelAndView shareDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String sno = request.getParameter("sno");
		shareTipService.shareDelete(sno);
		String category = request.getParameter("category");
		// 12.8 수정
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pageNo", request.getParameter("pageNo"));
		map.put("category", null);
		
		//12.10 추가
		request.setAttribute("beforeCommand", request.getParameter("command"));
		return new ModelAndView("views/sharetips/shareTip_list","shareTipListDTO", shareTipService.shareList(map));
	}
	
	/*
	 ***************************************** ver 3 *****************************************
	 */
	
	// 12.10 수정
	// 내가 추천한 shareTipList 불러오기
	public ModelAndView getMyRecommendedSList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ShareTipListDTO shareTipListDTO = null;
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		String pageNo = request.getParameter("pageNo");
		String id = memberDTO.getId();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pageNo", pageNo);

		shareTipListDTO =  shareTipService.getSListByRecommend(map);

		request.setAttribute("beforeCommand", "getMyRecommendedSList");
		return new ModelAndView("views/sharetips/shareTip_list", "shareTipListDTO", shareTipListDTO);
	}
	
	/*
	 ***************************************** ver 4 *****************************************
	 */
	
	// sharetip 게시글 댓글 작성
	public ModelAndView shareCommentWrite(HttpServletRequest request, HttpServletResponse response, ShareTipCommentDTO s_commentDTO)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		int sno = Integer.parseInt(request.getParameter("sno"));
		String category = request.getParameter("category");
		s_commentDTO.setSno(sno);
		s_commentDTO.setMemberDTO(memberDTO);
		
		shareTipCommentService.shareCommentWrite(s_commentDTO);
		//덧글 작성 후, 조회수가 증가하는 문제 해결하기 위한 부분
		shareTipCommentService.decreaseHits(sno+"");
		return new ModelAndView("redirect:/share.do?command=shareShowDetail&&sno="+sno+"&&category="+category);
	}
	
	// sharetip 게시글 댓글 삭제
	public ModelAndView shareCommentDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String sno = request.getParameter("sno");
		String rno = request.getParameter("rno");
		String category = request.getParameter("category");
		shareTipCommentService.shareCommentDelete(rno);
		//덧글 작성 후, 조회수가 증가하는 문제 해결하기 위한 부분
		shareTipCommentService.decreaseHits(sno+"");
		return new ModelAndView("redirect:/share.do?command=shareShowDetail&&sno="+sno+"&&category="+category);
	}
	
	//12.09 수정
	// 내가 활동한 shareTip 게시글
	public ModelAndView myActivityShare(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ShareTipListDTO shareTipListDTO = null;
		String id = request.getParameter("id");
		String pageNo = request.getParameter("pageNo");

		HashMap<String, String> map = new HashMap<String, String>();
		if(id == null){
			map.put("id", ((MemberDTO)request.getSession().getAttribute("memberDTO")).getId());
		}else{
			map.put("id", id);
		}
		map.put("pageNo", pageNo);
		
		shareTipListDTO = shareTipService.myActivityShare(map);
		
		//12.10 추가수정
		request.setAttribute("beforeCommand", "myActivityShare");
		return new ModelAndView("views/sharetips/shareTip_list", "shareTipListDTO", shareTipListDTO);
	}
}

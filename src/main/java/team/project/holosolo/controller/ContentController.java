package team.project.holosolo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import team.project.holosolo.model.dto.CommentListByPageDTO;
import team.project.holosolo.model.dto.ContentCommentDTO;
import team.project.holosolo.model.dto.ContentDTO;
import team.project.holosolo.model.dto.ContentListDTO;
import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.service.ContentCommentService;
import team.project.holosolo.model.service.ContentService;

public class ContentController extends MultiActionController {
	//fields
	private ContentService contentService;
	private ContentCommentService contentCommentService;
	
	//setters()
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	public void setContentCommentService(ContentCommentService contentCommentService) {
		this.contentCommentService = contentCommentService;
	}

	/*
	 ***************************************** ver 1,2 *****************************************
	 */
	
	// ver 5 추가수정
	// 컨텐츠 등록
	public ModelAndView contentPost(HttpServletRequest request,HttpServletResponse response, ContentDTO cdto)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}

		cdto.setMemberDTO(memberDTO);

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

		cdto.setFileName(fileName);
		cdto.setContent(content);

		contentService.contentPost(cdto);
		
		//ver 5 추가
		//추가 12.07
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("category", null);
		map.put("pageNo", null);
		
		//12.10 추가수정
		request.setAttribute("category", "seeAll");
		request.setAttribute("beforeCommand", "contentList");
		return new ModelAndView("views/contents/content_list", "contentListDTO", contentService.contentList(map));
	}
	
	// ver 5 수정	
	// 컨텐츠 목록	12.07 수정 ////
	public ModelAndView contentList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ContentListDTO contentListDTO = null;
		String category = request.getParameter("category");
		String pageNo = request.getParameter("pageNo");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("pageNo", pageNo);
		
		if(category.equals("seeAll")){
			map.put("category", null);
			contentListDTO = contentService.contentList(map);
		}else{
			map.put("category", category);
			contentListDTO = contentService.contentList(map);
		}
		
		request.setAttribute("category", category);
		//12.10 추가
		request.setAttribute("beforeCommand", "contentList");
		return new ModelAndView("views/contents/content_list", "contentListDTO", contentListDTO);
	}
	
	// ver 5 수정 12.10 추가수정
	// 컨텐츠 상세보기
	public ModelAndView contentShowDetail(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String cno = request.getParameter("cno"); // 기본 base
		ContentDTO contentDTO =contentService.contentShowDetail(cno);
		
		String selectedCategory = request.getParameter("category"); // 이전글 다음글의 cno를 list에서 출력해오기 위해서 필요하다.
		String replyPageNo = request.getParameter("replyPageNo"); // 각 디테일에서 표시되는 페이지는 유일하게 댓글뿐이다.
		
		// 페이지별로 출력하기 위해서 DAO및 Query에서는 인자값을 2개를 필요로 한다.
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("cno", cno);
		map.put("replyPageNo", replyPageNo);
		map.put("category", selectedCategory); // service에서만 사용된다.
		
		// ver 5 수정
		List<ContentDTO> contentList = contentService.showContentListInDetail(selectedCategory); // category별로 가져오는 것 아님.
		CommentListByPageDTO commentListByPageDTO = contentCommentService.contentsCommentList(map);
		List<String> crList=contentService.contentRecommendCount(cno);
		boolean crflag = crList.contains(memberDTO.getId());
		int recommendCount = crList.size();
		
		//추가
		//list에서 현재 cno와 일치하는 글의 index 찾아서 그 즉시, 값을 바인딩한다.
		String beforeCno = null; // 현재 cno를 기준으로 찾을 이전 cno
		String afterCno = null;	// 현재 cno를 기준으로 찾을 다음 cno
		
		if(contentList.size() != 1){
			for(int i=0; i<contentList.size(); i++){
				if(contentList.get(i).getCno() == Integer.parseInt(cno)){ 
					if(i == 0){
						afterCno = String.valueOf(contentList.get(i+1).getCno());
						break;
					}else if(i > 0 && i < contentList.size()-1){
						afterCno = String.valueOf(contentList.get(i+1).getCno());
						beforeCno = String.valueOf(contentList.get(i-1).getCno());
						break;
					}else if(i == contentList.size()-1){
						beforeCno = String.valueOf(contentList.get(i-1).getCno());
						break;
					}
				}
			}
		}
		// 12.10 바인딩. null 값으로 처리하기 위해서 String으로. // Controller 안에서 처리되므로 값을 계속해서 넘겨줄 필요 없음.
		request.setAttribute("beforeCno", beforeCno);
		request.setAttribute("afterCno", afterCno);
		
		// 바인딩
		request.setAttribute("contentDTO", contentDTO);
		request.setAttribute("contentList", contentList);//전체 게시글
		request.setAttribute("commentListByPageDTO", commentListByPageDTO);//댓글 리스트
		request.setAttribute("crflag", crflag);//추천 리스트 아이디 비교
		request.setAttribute("recommendCount", recommendCount);//추천수
		//12.11 추가
		request.setAttribute("selectedCategory", selectedCategory);
		return new ModelAndView("views/contents/content_showDetail");
	}
	
	// content 추천
	public ModelAndView contentRecommend(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String id= request.getParameter("id");
		String cno= request.getParameter("cno");
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("cno", cno);
		contentService.contentRecommend(map);
		
		
		List<String> crList=contentService.contentRecommendCount(cno);
		int recommendCount = crList.size();//추천수를 넘긴다.
		
		return new ModelAndView("JsonView", "recommendCount", recommendCount);
	}

	// ver 5 수정추가
	//content 검색  12.07 추가 & 수정 ///
	public ModelAndView contentSearch(HttpServletRequest request, HttpServletResponse respose)throws Exception{
		String category = request.getParameter("category");
		String word=request.getParameter("word");
		String sort=request.getParameter("sort");		
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
		map.put("pageNo", pageNo);
		
		ContentListDTO contentListDTO=contentService.contentSearch(map);
		request.setAttribute("category", category);
		
		//12.10추가수정
		request.setAttribute("beforeCommand", "contentSearch");
		request.getSession().setAttribute("word", word);
		request.getSession().setAttribute("sort", sort);
		return new ModelAndView("views/contents/content_list", "contentListDTO", contentListDTO);
	}
	
	// ver 5 추가
	// 컨텐츠 업데이트를 위한 해당 컨텐츠의 상세정보를 보냄
	public ModelAndView updateView(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String cno = request.getParameter("cno");
		ContentDTO contentDTO = contentService.contentShowDetail(cno);
		
		String content = contentDTO.getContent();
		content = content.replaceAll("src=\"", "src=\"../");
		contentDTO.setContent(content);
		
		//12.12 추가
		List<String> categoryList = new ArrayList<String>();
		categoryList.add("Cooking");
		categoryList.add("Place");
		categoryList.add("Information");
		
		request.setAttribute("categoryList", categoryList);
		return new ModelAndView("views/contents/content_update","contentDTO", contentDTO);
	}
		
	// ver 5 추가수정
	// 폼에 수정된 컨텐츠 내용 업데이트
	public ModelAndView contentUpdate(HttpServletRequest request, HttpServletResponse response, ContentDTO cdto)throws Exception{
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
		
		cdto.setFileName(fileName);
		cdto.setContent(content);
		
		contentService.contentUpdate(cdto);
		//ver 5 12.08 추가
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("category", null);
		map.put("pageNo", null);
		
		//12.10추가수정
		request.setAttribute("beforeCommand", "contentList");
				
		return new ModelAndView("views/contents/content_list", "contentListDTO", contentService.contentList(map));
	}
		
	// ver 5 추가수정 
	// 컨텐츠 삭제
	public ModelAndView contentDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String cno = request.getParameter("cno");
		
		contentService.contentDelete(cno);
		String category = request.getParameter("category");
		// ver 5 추가 12.08
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("category", null);
		map.put("pageNo", null);
				
		//12.10추가수정
		request.setAttribute("beforeCommand", "contentList");
		
		return new ModelAndView("views/contents/content_list","contentListDTO",contentService.contentList(map));
	}
	
	/*
	 ***************************************** ver 3 *****************************************
	 */
	
	//12.10 수정
	// 내가 추천한 컨텐츠 리스트 불러오기
	public ModelAndView getMyRecommendedCList(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ContentListDTO contentListDTO = null;
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		String pageNo = request.getParameter("pageNo");
		String id = memberDTO.getId();

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pageNo", pageNo);
		
		contentListDTO = contentService.getCListByRecommend(map);
		
		request.setAttribute("beforeCommand", "getMyRecommendedCList");
		return new ModelAndView("views/contents/content_list", "contentListDTO", contentListDTO);
	}
	
	/*
	 ***************************************** ver 4 *****************************************
	 */
	
	// content 게시글 댓글 작성
	public ModelAndView contentCommentWrite(HttpServletRequest request, HttpServletResponse response, ContentCommentDTO c_commentDTO)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		int cno = Integer.parseInt(request.getParameter("cno"));
		String category = request.getParameter("category");
		c_commentDTO.setCno(cno);
		c_commentDTO.setMemberDTO(memberDTO);
		
		contentCommentService.contentsCommentWrite(c_commentDTO);
		contentCommentService.decreaseHits(cno+"");
		
		return new ModelAndView("redirect:/content.do?command=contentShowDetail&&cno="+cno+"&&category="+category);
	}
	
	// content 게시글 댓글 삭제
	public ModelAndView contentCommentDelete(HttpServletRequest request, HttpServletResponse response)throws Exception{
		// 로그인 확인 부분
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		if(memberDTO == null){
			return new ModelAndView("redirect:/views/error_session.jsp");
		}
		
		String cno = request.getParameter("cno");
		String rno = request.getParameter("rno");
		String category = request.getParameter("category");
		
		contentCommentService.contentsCommentDelete(rno);
		contentCommentService.decreaseHits(cno+"");
		
		return new ModelAndView("redirect:/content.do?command=contentShowDetail&&cno="+cno+"&&category="+category);
	}
}

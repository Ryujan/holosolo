package team.project.holosolo.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import team.project.holosolo.model.dto.MemberDTO;
import team.project.holosolo.model.service.MemberService;
import team.project.holosolo.util.Editor;
import team.project.holosolo.util.MailServiceManager;

public class MemberController extends MultiActionController {
	private MemberService memberService;
	private String imgPath;
	private MailServiceManager mailServiceManager;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void setMailServiceManager(MailServiceManager mailServiceManager) {
		this.mailServiceManager = mailServiceManager;
	}
	
	/*
	 ***************************************** ver 1,2 *****************************************
	 */
	
	//12.10 추가수정
	// 회원 회원가입
	public ModelAndView memberRegister(HttpServletRequest request,
			HttpServletResponse respose, MemberDTO mdto)throws Exception{
		String myPhoto = mdto.getMyPhoto();
		
		if(myPhoto.equals("")){
			if(mdto.getSex().equals("M")) mdto.setMyPhoto("man.png");
			else if(mdto.getSex().equals("W")) mdto.setMyPhoto("woman.png");
		}
		
		memberService.memberRegister(mdto);
		request.getSession().setAttribute("memberDTO", mdto);
			
		return new ModelAndView("intro");
	}

	// 회원 로그인
	public ModelAndView login(HttpServletRequest request,
         HttpServletResponse respose, HttpSession session, MemberDTO mdto)throws Exception{      
		MemberDTO memberDTO = memberService.login(mdto);
		boolean loginResult = false;
		if(memberDTO!=null){
			session.setAttribute("memberDTO", memberDTO);
			loginResult = true;
		}      
		
		return new ModelAndView("JsonView", "loginResult", loginResult);
	}

	// 회원 로그아웃
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse respose, HttpSession session, MemberDTO mdto)throws Exception{
		if(session.getAttribute("memberDTO")!=null){
			session.invalidate();
		}
		
		return new ModelAndView("intro");
	}

	// 회원 정보 수정
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse respose)throws Exception{
		System.out.println("memberUpdate starts!");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
	  
		MemberDTO memberDTO = new MemberDTO(id, password, email);
		memberService.memberUpdate(memberDTO);
		memberDTO =  memberService.login(memberDTO);
	  
		request.getSession().setAttribute("memberDTO", memberDTO);
	  
		return new ModelAndView("JsonView", "memberDTO", memberDTO);
	}
	
	/*
	 ***************************************** ver 3 *****************************************
	 */
	
	// 회원 아이디 체크
	public ModelAndView idCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		boolean flag=memberService.idCheck(request.getParameter("id"));	
		
		return new ModelAndView("JsonView","flag",flag);
	}

	// 포토 업로드
	public ModelAndView uploadMyPhoto(HttpServletRequest request, HttpServletResponse response, Editor editor) throws Exception{
		System.out.println("fileUploaderController starts!");
		//추가
		String kind = request.getParameter("kind");
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		//
		
		MultipartFile mFile = editor.getFileData();
		// 내가 가져온 File이 존재하는지, 존재하지 않는지 체크하는 부분.
		
		String imgName = ""; // 이미지 명
		String returnValues = ""; // 아마도 에러를 의미하는 값. 만약 아래의 확장자 체크하는 부분에서 문제가 없다면 이 부분은 추가되지 않는다.
		
		HashMap<String, String> map = new HashMap<String, String>();
		try{
			if(mFile != null && mFile.getOriginalFilename() != null && !mFile.getOriginalFilename().equals("")){
				imgName = mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(File.separator)+1);//파일명 가져오기
				String imgName_ext = imgName.substring(imgName.lastIndexOf(".")+1); // 확장자를 가져오는 부분.
				imgName_ext = imgName_ext.toLowerCase();
				String[] allow_file = {"jpg","png","bmp","gif"}; // 허용되는 확장자.
				
				int cnt = 0;
				for(int i=0; i<allow_file.length; i++){ // 확장자를 체크하는 부분. 위의 배열에 해당하는지 해당하지 않는지를 체크.
					if(imgName_ext.equals(allow_file[i])){
						cnt++;
					}
				}
				
				if(cnt == 0){
					returnValues = "&errstr="+imgName; // 위의 확장자에 포함되지 않으므로 변수에 추가한다. 또한 아래의 문장이 수행되지 않으므로 값은 유일하게 한개.
				}else{
					String filePath = imgPath;
					
					File file = new File(filePath);
					if(!file.exists()) // 생성 경로가 없을 경우에만 하위 폴더들을 생성하나, 이미 존재한다. 
						file.mkdirs();
					
					String realImgName = "";// 날짜 추가 및 확장자를 제거한 이미지 명 => DB에 저장하고, 경로에 표시할 실제 이름.
					String realFileName = "";// 위의 realImgName과 경로가 추가된 변수.
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
					String today = formatter.format(new java.util.Date());
					realImgName = today+UUID.randomUUID().toString() + imgName.substring(imgName.lastIndexOf("."));
					
					realFileName = filePath + realImgName;
					
					mFile.transferTo(new File(realFileName)); // 실 경로에 업로드된 파일을 copy.
					// 이제 callback.html에 보내야할 값을 get방식으로 입력한다.
					Thread.sleep(3000);
					map.put("flag", "true");
					map.put("realImgName", realImgName);
					
					/* 추가 12.01 */
					map.put("kind", kind);
					if(kind.equals("profile")){
						HashMap<String, String> updateMap = new HashMap<String, String>();
						updateMap.put("id", memberDTO.getId());
						updateMap.put("myPhoto", realImgName);
						
						int result = memberService.myPhotoUpdate(updateMap);
						if(result == 1){
							memberDTO.setMyPhoto(realImgName);
							request.setAttribute("memberDTO", memberDTO);
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("JsonView", "result", map);
	}
	
	/*
	 ***************************************** ver 4 *****************************************
	 */

	// 로그인 상태 확인
	public ModelAndView verifyLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		String flag = "false";
		if(memberDTO != null) flag = "true";

		return new ModelAndView("JsonView", "flag", flag);
	}

	// 12/2 수요일 추가부분 
	// 비밀번호 확인
	public ModelAndView checkPassword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String checkPass = request.getParameter("checkPass");
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
	
		boolean matchingResult = false;
		if(checkPass.equals(memberDTO.getPassword())) matchingResult = true;
		
		return new ModelAndView("JsonView", "matchingResult", matchingResult);
	}

	// 회원 탈퇴
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean deleteResult = false;
	
		int result = memberService.memberDelete(new MemberDTO(id, password));
		if(result == 1){
			request.getSession().invalidate();
			deleteResult = true;
		}
	
		return new ModelAndView("JsonView", "deleteResult", deleteResult);
	}

	// 12월 4일 추가된 부분
	public ModelAndView findPassword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("starts findPassword!");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
	
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("name", name);
	
		MemberDTO memberDTO = memberService.findPassword(map);
		boolean findResult = false;
	
		if(memberDTO != null){
			mailServiceManager.sendEmail(memberDTO);
			findResult = true;
		}
	
		return new ModelAndView("JsonView", "findResult", findResult);
	}
}

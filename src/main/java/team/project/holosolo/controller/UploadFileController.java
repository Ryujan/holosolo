package team.project.holosolo.controller;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import team.project.holosolo.util.Editor;

public class UploadFileController extends MultiActionController{
	private String imgPath;

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String fileUploader(HttpServletRequest request, HttpServletResponse response, Editor editor) throws Exception{
		System.out.println("fileUploaderController starts!");
		MultipartFile mFile = editor.getFileData();
		// 내가 가져온 File이 존재하는지, 존재하지 않는지 체크하는 부분.
		
		//이미지 경로
		String categoryPath = request.getParameter("categoryPath");
		
		String imgName = ""; // 이미지 명
		String callback = request.getParameter("callback"); // callback.html이 저장된 경로.
		String callback_func = request.getParameter("callback_func"); // 업로드에 필요한 변수가 아닌 업로드 후, Editor에 뿌려주기 위한 값.
		String returnValues = ""; // 아마도 에러를 의미하는 값. 만약 아래의 확장자 체크하는 부분에서 문제가 없다면 이 부분은 추가되지 않는다.
		
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
					
					/*String filePath = "C:"+ File.separator +"PerfLogs"+ File.separator +"team"+ File.separator +"apache-tomcat-7.0.64"+ File.separator +"webapps"+ File.separator +"upload"+ File.separator;*/
					String filePath = imgPath + "\\" + categoryPath + "\\";
					/*String filePath = request.getSession().getServletContext().getRealPath("/")+"upload\\";*/
					
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
					/*returnValues += "&sFileName="+imgName;*/
					returnValues += "&sFileName="+categoryPath+"/"+realImgName;
					returnValues += "&bNewLine="+true;
					returnValues += "&sFileURL="+realFileName;
				}
			}else{
				returnValues += "&errstr=error";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String finalPath = "redirect:" + callback + "?callback_func=" + callback_func + returnValues;
		
		return finalPath;
	}
}

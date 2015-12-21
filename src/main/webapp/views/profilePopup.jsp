<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<style type="text/css">
/* NHN Web Standard 1Team JJS 120106 */ 
/* Common */
body,p,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,table,th,td,form,fieldset,legend,input,textarea,button,select{margin:0;padding:0}
body,input,textarea,select,button,table{font-family:'돋움',Dotum,Helvetica,sans-serif;font-size:16px}
img,fieldset{border:0}
ul,ol{list-style:none}
em,address{font-style:normal}
a{text-decoration:none}
a:hover,a:active,a:focus{text-decoration:underline}

/* Contents */
.blind{visibility:hidden;position:absolute;line-height:0}
#pop_wrap{width:383px}
#pop_header{height:26px;padding:14px 0 0 20px;border-bottom:1px solid #ededeb;background:#f4f4f3}
.pop_container{padding:11px 20px 0}
#pop_footer{margin:21px 20px 0;padding:10px 0 16px;border-top:1px solid #e5e5e5;text-align:center}
h1{color:#333;font-size:14px;letter-spacing:-1px}
.btn_area{word-spacing:2px}
.pop_container .drag_area{overflow:hidden;overflow-y:auto;position:relative;width:341px;height:129px;margin-top:4px;border:1px solid #eceff2}
.pop_container .drag_area .bg{display:block;position:absolute;top:0;left:0;width:341px;height:129px;background:#fdfdfd url(../../img/photoQuickPopup/bg_drag_image.png) 0 0 no-repeat}
.pop_container .nobg{background:none}
.pop_container .bar{color:#e0e0e0}
.pop_container .lst_type li{overflow:hidden;position:relative;padding:7px 0 6px 8px;border-bottom:1px solid #f4f4f4;vertical-align:top}
.pop_container :root .lst_type li{padding:6px 0 5px 8px}
.pop_container .lst_type li span{float:left;color:#222}
.pop_container .lst_type li em{float:right;margin-top:1px;padding-right:22px;color:#a1a1a1;font-size:11px}
.pop_container .lst_type li a{position:absolute;top:6px;right:5px}
.pop_container .dsc{margin-top:6px;color:#666;line-height:18px}
.pop_container .dsc_v1{margin-top:12px}
.pop_container .dsc em{color:#13b72a}
.pop_container2{padding:46px 60px 20px}
.pop_container2 .dsc{margin-top:6px;color:#666;line-height:18px}
.pop_container2 .dsc strong{color:#13b72a}
.upload{margin:0 4px 0 0;_margin:0;padding:6px 0 4px 6px;border:solid 1px #d5d5d5;color:#a1a1a1;font-size:12px;border-right-color:#efefef;border-bottom-color:#efefef;length:300px;}
:root  .upload{padding:6px 0 2px 6px;}
</style>
<title>Insert title here</title>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="/holosolo/js/jquery.form.js"></script>
<script type="text/javascript">
	$(function(){
	   	//ajax form submit
	    $('#frm').ajaxForm({
	    	success: function(data,status){
	    		if(data.result.flag == 'true'){
	    			if(data.result.kind == 'register'){
	    				var img = "<img alt='' width='200px' height='150px'" + 
	    						" src='/holosolo/resources/uploadImg/memberImg/"
	    						+ data.result.realImgName + "'>";
						$("#registerImgView", opener.document).html(img);
						$("#fileName", opener.document).val(data.result.realImgName);
	    			}else if(data.result.kind == 'profile'){
	    				var img = "<img style='margin-top: 18px;' width='85%' height='80%'" 
	    						+ " alt='' src='/holosolo/resources/uploadImg/memberImg/" 
	    						+ data.result.realImgName + "'>";
	    				$("#profileImgView", opener.document).html(img);
	    			}
					self.close();
				}
	    	}
	    });
		
		$('#cancleBtn').click(function(){
			self.close();
		});
	});
</script>
</head>
<body>
	<div id="pop_wrap">
		<!-- header -->
	    <div id="pop_header">
	        <h1>사진 첨부하기</h1>
	    </div>
	    <!-- //header -->
	    <!-- container -->
	    <!-- [D] HTML5인 경우 pop_container 클래스와 하위 HTML 적용
		    	 그밖의 경우 pop_container2 클래스와 하위 HTML 적용      -->
		<div id="pop_container2" class="pop_container2" align="center">
	    	<!-- content -->
			<form action="/holosolo/member.do" id="frm" method="post">
			<input type="hidden" name="command" value="uploadMyPhoto">
			
			<input type="hidden" name="kind" value="${param.kind}">
			
	        <div id="pop_content2">
				<input type="file" name="fileData">
	            <p class="dsc" id="info"><strong>10MB</strong>이하의 이미지 파일만 등록할 수 있습니다.<br>(JPG, GIF, PNG, BMP)</p>
	            <br><br><br><br>
	            <input type="submit" id="uploadBtn" value="확 인"> &nbsp;&nbsp;&nbsp;
	            <input type="button" id="cancleBtn" value="취 소"> 
	        </div>
			</form>
	        <!-- //content -->
	    </div>
	    <div id="pop_container" class="pop_container" style="display:none;">
	    	<!-- content -->
	        <div id="pop_content">
		        <p class="dsc"><em id="imageCountTxt">0장</em>/10장 <span class="bar">|</span> <em id="totalSizeTxt">0MB</em>/50MB</p>
	        	<!-- [D] 첨부 이미지 여부에 따른 Class 변화 
	            	 첨부 이미지가 있는 경우 : em에 "bg" 클래스 적용 //첨부 이미지가 없는 경우 : em에 "nobg" 클래스 적용   -->
					 
	            <div class="drag_area" id="drag_area">
					<ul class="lst_type" >
					</ul>
	            	<em class="blind">마우스로 드래그해서 이미지를 추가해주세요.</em><span id="guide_text" class="bg"></span>
	            </div>
				<div style="display:none;" id="divImageList"></div>
	            <p class="dsc dsc_v1"><em>한 장당 10MB, 1회에 50MB까지, 10개</em>의 이미지 파일을<br>등록할 수 있습니다. (JPG, GIF, PNG, BMP)</p>
	        </div>
	        <!-- //content -->
	    </div>
	</div>
</body>
</html>
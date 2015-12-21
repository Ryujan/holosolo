<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/holosolo/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<script type="text/javascript" src="//code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/holosolo/se/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
	/* 제목과 Content값을 null상태로 넘기면 DB문제 생김. 그래서 필요한 Script. */
	function content_submit(){
		oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []); 
	}
	
	$(function(){
		var auth = "${sessionScope.memberDTO.authority}";
		if(auth == "false"){
			location.href="/holosolo/intro.jsp";
		}
	});
</script>
</head>
<body style="background-color: rgba(249, 249, 249, 1)">
<c:import url="../header.jsp"></c:import>
<div align="center" style="margin-top: 8%;">
	<form action="/holosolo/content.do" method="post" name="write_form" enctype="multipart/form-data">
		<!-- command로 표기될 메서드 명은 조장하고 상의 후, 정하도록. value 부분. -->
		<input type="hidden" name="command" value="contentUpdate">
		<input type="hidden" name="cno" value="${contentDTO.cno}">
		<input id="categoryPath" type="hidden" name="categoryPath" value="contentImg">
		<table style="width: 50%; margin: 0px 0px 1% 2%">
			<tr>
				<td>
					<select name="category" id="category" required="required"  class="form-control-category">
						<option value="">카테고리</option>
						<c:forEach var="kind" items="${ categoryList }" >
							<c:choose>
								<c:when test="${ contentDTO.category == kind }">
									<c:set var="selected" value="selected"/>
								</c:when>
								<c:otherwise>
									<c:set var="selected" value=""/>
								</c:otherwise>
							</c:choose>
							<c:if test="${kind == 'Cooking'}">
								<c:set var="category" value="음식"/>
							</c:if>
							<c:if test="${kind == 'Place'}">
								<c:set var="category" value="장소"/>
							</c:if>
							<c:if test="${kind == 'Information'}">
								<c:set var="category" value="생활정보"/>
							</c:if>
							<option value="${kind}" ${selected}>${ category }</option>
						</c:forEach>
					</select>&nbsp;
					<input type="text" class="form-control-write" id="title" maxlength="20" name="title" maxlength="50" size="67" required="required" value="${contentDTO.title }">
				</td>
			</tr>
		</table>
		<textarea name="content" id="content" rows="10" cols="100" style="height: 412px; width: 100%">${contentDTO.content}</textarea>
		<script type="text/javascript">
			var oEditors = [];
			         
			nhn.husky.EZCreator.createInIFrame({
				oAppRef: oEditors,
				elPlaceHolder: "content",
				sSkinURI: "/holosolo/se/SmartEditor2Skin.html",   
				
				htParams : {
					bUseToolbar : true,            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,      // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChaner : true,         // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					//aAdditionalFontList : aAdditionalFontSet,      // 추가 글꼴 목록
					   
					fOnBeforeUnload : function(){
					//alert("완료!");
					}
				}, //boolean
				fOnAppLoad : function(){
				//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
				},
				fCreator: "createSEditor2"
			});
		</script>
		<input type="submit" value="수정" class="btn updatebtn" onclick="content_submit()">
	</form>
</div>
<c:import url="../footer.jsp"></c:import>
</body>
</html>
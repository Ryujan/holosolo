<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function(){
	 	var memberDTO = "${sessionScope.memberDTO}";
		if(memberDTO == ""){
			location.href="/holosolo/intro.jsp";
		}
		
		$('#commentWrite').click(function(){
			var reply = $('#reply').val();
			if(reply==""){
				alert("내용을 입력하세요");
				reply.focus();
				return false;
			}else{
				location.href="content.do?command=contentCommentWrite&&cno=${contentDTO.cno}&&reply="+reply+"&&category=${requestScope.selectedCategory}";
			}
		});//click
	
		var flag = "${requestScope.crflag}";
			$('#recommend').click(function(){
				if(flag=="true"){
					confirm("이미 추천하신 게시물 입니다.");
				}else{
					$.ajax({
					type:"post",
					url:"content.do",
					data:"command=contentRecommend&&id=${sessionScope.memberDTO.id}&&cno=${requestScope.contentDTO.cno}",
					dataType:"Json",
					
					success:function(data){
						flag="true";
						$('#recommendCount').html('<b>' + data.recommendCount +'명</b>');
					}//success
				});//ajax
			}//else
		});//click
		
		$('#deleteBtn').click(function(){
			return confirm("삭제하시겠습니까?");
		});//click
	});
	
	function commentDelete(rno,id,cno){
		location.href = "content.do?command=contentCommentDelete&&rno="+rno+"&&id="+id+"&&cno="+cno+"&&category=${requestScope.selectedCategory}";
	}
	
</script>

<style type="text/css">
	#comment tr:HOVER{
		background-color: bisque;
	}
		
	.beforeimg{
		opacity: 0.6;
	}
	
	.beforeimg:hover{
		opacity: 1;
	
	}
	
	.afterimg{
		opacity: 0.6;
	}
	
	.afterimg:hover{
		opacity: 1;
	}
	
</style>
</head>

<body style="background-color: rgba(177, 176, 176, 0.06);">
	<c:import url="../header.jsp"></c:import>
	
	<div id="body" align="center" class="container" style="margin-top: 8%;">
		
		<!-- 이전글 -->
		<div class="col-md-1" id="beforeContent" style=" left: 10%; width: 160px; height: 500px; margin-top: 4%; margin-left: 5%; position: fixed;">
			 <c:if test="${requestScope.beforeCno != null}">
				<a href="content.do?command=contentShowDetail&&cno=${requestScope.beforeCno}&&category=${requestScope.selectedCategory}">
					<img alt="" class="beforeimg" src="img/left.png" style="width: 110px; height: 110px; margin-top:30%;"></a>
			</c:if>
		</div>
		
		<div class="col-md-10" style="width: 700px; margin-right: 200px; margin-left: 200px;" >
			<c:if test="${contentDTO.category == 'Cooking'}">
				<c:set var="category" value="음식"/>
			</c:if>
			<c:if test="${contentDTO.category == 'Place'}">
				<c:set var="category" value="장소"/>
			</c:if>
			<c:if test="${contentDTO.category == 'Information'}">
				<c:set var="category" value="생활정보"/>
			</c:if>
			<legend style="border-color: #70011C; width: 98%; color: black; padding-right: 0px; margin-top: 10px; margin-right: 5%; margin-left: 5%;">
				<b>[ ${ category } ] - </b> &nbsp;${ contentDTO.title }
				<span style="position: absolute; right: 26px; margin-right: 5%;">조회수 : ${contentDTO.hits }</span>
			</legend>
			<table cellpadding="5">
				<tr>
					<td>
						<table width="650">						
							<tr>
								<td style="background-color:rgba(177, 176, 176, 0.12); width:100%; height:100%;padding: 20px 20px 20px 20px; border-radius:33px">
									작성자 : ${ contentDTO.memberDTO.id }<span style="position: absolute; right: 47px;">작성일시 : ${ contentDTO.postedTime }</span>
									<hr style="color: #0B1B50;"> 
									<div style="border: 0px; background-color: white; padding: 10px">${contentDTO.content}</div>
									
									<a href="content.do?command=contentList&&category=seeAll"
										class="btn btn-sm btn-default" style="margin-top:1.7%;">목록</a>
									<c:if test="${sessionScope.memberDTO.id==contentDTO.memberDTO.id || sessionScope.memberDTO.authority == true}">
										<a href="content.do?command=updateView&&cno=${contentDTO.cno}"
											class="btn btn-sm btn-default" style="margin-top:1.7%;">수정</a>
										<a href="content.do?command=contentDelete&&cno=${contentDTO.cno}"
											class="btn btn-sm btn-default" style="margin-top:1.7%;">삭제</a>	
									</c:if>
									 <a href="#" class="btn btn-sm btn-default" id="recommend" 
									 	style="position: absolute; right:72px; margin-top:1.7%;">
									 	<span><img src="img/recommand.png"></span>추천</a>
									<span id ="recommendCount" style="position: absolute; right: 47px; margin-top: 2.5%">
										<font size="2px;">${recommendCount}명</font></span>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<!-- 댓글 쓰기 -->
			<br>
			<form name="commentWrite" id="cfm" style="width: 635px;">
				<h3 align="left" style="padding-left: 8px;">댓글</h3><hr>
				<textarea id="reply" name="reply" rows="3" cols="50"
					style="width: 550px; height: 50px; margin-right: 10px; margin-bottom: 15px; margin-left: 10px; background-color: rgba(177, 176, 176, 0.06);"></textarea>
				<input type="button" id="commentWrite" class="btn btn-default" value="작성" style="margin-bottom: 10px;">
				<br><br><br>
			</form>

			<table width="640px" border="0px;" style="margin-bottom: 40px;">
				<tr align="center" style="border-bottom: 2px solid #0B1B50;">
					<th style="width: 130px;">
						<h4 align="center"><b>아이디</b></h4>
					</th>
					<th>
						<h4 align="center"><b>댓글</b></h4>
					</th>
					<th style="width: 175px;">
						<h4 align="center"><b>작성시간</b></h4>
					</th>
					<th style="width: 70px;"></th>
				</tr>
				<tbody id="comment">
					<c:forEach var="comment" items="${requestScope.commentListByPageDTO.commentList}">
						<tr align="center" height="30">
							<td>${comment.memberDTO.id}</td>
							<td>${comment.reply}</td>
							<td>${comment.postedTime}</td>
							<td style="width: 70px;">
								<c:if test="${sessionScope.memberDTO.id == comment.memberDTO.id 
												|| sessionScope.memberDTO.authority == true}">
									<a href="javascript:commentDelete('${comment.rno}',
											'${comment.memberDTO.id}','${contentDTO.cno}')">
										<img alt="삭제" src="img/x.png" border="0" style="width: 20%;"></a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 댓글의 페이징이 시작된다. -->
			<c:if test="${requestScope.commentListByPageDTO.pagingBean.previousPageGroup}">
				<a href="content.do?command=contentShowDetail&&cno=${ requestScope.contentDTO.cno }&&category=${ requestScope.contentDTO.category }&&replyPageNo=${requestScope.commentListByPageDTO.pagingBean.startPageOfPageGroup-1}">
				<img src="./img/left_arrow_btn.gif"></a>
			</c:if> &nbsp;&nbsp;
			
			<c:forEach var="i"
				begin="${requestScope.commentListByPageDTO.pagingBean.startPageOfPageGroup}"
				end="${requestScope.commentListByPageDTO.pagingBean.endPageOfPageGroup}">
				<c:choose>
					<c:when test="${requestScope.commentListByPageDTO.pagingBean.nowPage!=i}">
						<a href="content.do?command=contentShowDetail&&cno=${ requestScope.contentDTO.cno }&&category=${ requestScope.contentDTO.category }&&replyPageNo=${i}">${i}</a>
					</c:when>
					<c:otherwise>
						${i}
					</c:otherwise>
				</c:choose>
			</c:forEach>
			&nbsp;&nbsp;

			<c:if test="${requestScope.commentListByPageDTO.pagingBean.nextPageGroup}">
				<a href="content.do?command=contentShowDetail&&cno=${ requestScope.contentDTO.cno }&&category=${ requestScope.contentDTO.category }&&replyPageNo=${requestScope.commentListByPageDTO.pagingBean.endPageOfPageGroup+1}">
					<img src="./img/right_arrow_btn.gif">
				</a>
			</c:if>
			<!-- 여기까지가 내용, 댓글, 그리고 댓글의 페이지가 진행된 부분이다. -->
		</div>
		
		<!-- 다음글 -->
		<div class="col-md-1" id="afterContnet" style="right: 8%; width: 160px; height: 500px; margin-top: 4%;margin-left:5%; margin-right:5%; position: fixed;">
			<c:if test="${requestScope.afterCno != null}">
				<a href="content.do?command=contentShowDetail&&cno=${requestScope.afterCno}&&category=${requestScope.selectedCategory}">
					<img alt="" class="beforeimg" src="img/right.png" style="width: 110px; height: 110px;  margin-top:30%;"></a>
			</c:if>
		</div>
	</div>

	<c:import url="../footer.jsp"></c:import>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<style type="text/css">
	#commentTable{
		border:0px;	
		border-bottom: 1px solid gray;
	}
	#commentTable th{
		border: 0px;
		border-bottom: 1px solid gray;
	}
	
	#comment td{
		border: 0px;
	}
	
	#comment tr:HOVER{
		background-color: bisque;
	}
	
	#listTable tr{
		border-bottom: 2px solid white;
	}
	
	#listTable td{
		padding: 10px 0px 10px 0px;
	}
</style>
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
				return;
			}else{
				location.href="/holosolo/share.do?command=shareCommentWrite&&sno=${shareTipDTO.sno}&&reply="+reply+"&&category=${requestScope.selectedCategory}";
			}
		});//click
	
		var flag = "${requestScope.srflag}";
		$('#recommend').click(function(){
			if(flag=="true"){
				confirm("이미 추천하신 게시물 입니다.");
			}else{
				$.ajax({
					type:"post",
					url:"/holosolo/share.do",
					data:"command=shareRecommend&&id=${sessionScope.memberDTO.id}&&sno=${requestScope.shareTipDTO.sno}&&selectedCategory=${requestScope.selectedCategory}",
					dataType:"Json",
				
					success:function(data){
						flag = "true";
						$('#recommendCount').html("<b>" + data.recommendCount + "명</b>");
					}//success
				});//ajax
			}//else
		});//click
	
		$('#deleteBtn').click(function(){
			return confirm("삭제하시겠습니까?");
		});//click
	
	});
		
	function commentDelete(rno,id,sno){
		location.href = "/holosolo/share.do?command=shareCommentDelete&&rno="+rno+"&&id="+id+"&&sno="+sno+"&&category=${requestScope.selectedCategory}";
	}
	
</script>

</head>
<body style="background-color: rgba(177, 176, 176, 0.06);">
<c:import url="../header.jsp"></c:import>
	<div id="body" align="left" class="container" style="margin-top: 8%; ">
		<div class="col-md-8" style="width: 700px">
			<c:if test="${shareTipDTO.category == 'Cooking'}">
				<c:set var="category" value="음식"/>
			</c:if>
			<c:if test="${shareTipDTO.category == 'Place'}">
				<c:set var="category" value="장소"/>
			</c:if>
			<c:if test="${shareTipDTO.category == 'Information'}">
				<c:set var="category" value="생활정보"/>
			</c:if>
			<legend style="border-color: gray; width: 550px; color: black; margin-top: 10px; margin-right: 20px; margin-left: 25px;">
				<b>[ ${ category } ] - </b> &nbsp;${ shareTipDTO.title }
				<span style="position: absolute; right: 37px; margin-right: 30px;">조회수 : ${shareTipDTO.hits }</span>
			</legend>
			
			<table cellpadding="5">
				<tr>
					<td>
						<table width="650">
							<tr>
								<td style="background-color:rgba(177, 176, 176, 0.12); width:100%; height:100%;padding: 20px 20px 20px 20px; border-radius:33px">
									작성자 : ${ shareTipDTO.memberDTO.id }<span style="position: absolute; right: 50px;">작성일시 : ${ shareTipDTO.postedTime }</span>
									<hr style="color: #0B1B50;"> 
									<div style="border: 0px; background-color: white; padding: 10px">${ shareTipDTO.content }</div>
									
									<a href="/holosolo/share.do?command=shareList&&category=seeAll" class="btn btn-sm btn-default" style="margin-top: 1.7%">목록</a>
									<c:if test="${sessionScope.memberDTO.id==shareTipDTO.memberDTO.id 
												|| sessionScope.memberDTO.authority == true}">
										<a href="/holosolo/share.do?command=updateView&&sno=${shareTipDTO.sno}
												&&category=${requestScope.selectedCategory}" 
											class="btn btn-sm btn-default" style="margin-top: 1.7%">수정</a>
									 	<a href="/holosolo/share.do?command=shareDelete&&sno=${shareTipDTO.sno}" 
									 		class="btn btn-sm btn-default" style="margin-top: 1.7%">삭제</a>
									</c:if>
									<a href="#" class="btn btn-sm btn-default" id="recommend" 
										style="position: absolute; right:85px; margin-top:1.7%;">
										<span><img src="/holosolo/img/recommand.png"></span>추천</a>
									<span id ="recommendCount" style="position: absolute; right: 58px; margin-top: 2.5%">
										<font size="2px;">${recommendCount}명</font></span>	
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			
			<!-- 댓글 쓰기 -->
			<form name="commentWrite" id="sfm" style="width: 635px;">
				<h3 align="left" style="padding-left: 8px;">댓글</h3>
				<hr>
				<textarea id="reply" name="reply" rows="3" cols="50"
					style="width: 550px; height: 50px; margin-right: 10px; margin-bottom: 15px; margin-left: 10px; background-color: rgba(177, 176, 176, 0.06);"></textarea>
				<input type="button" id="commentWrite" class="btn btn-default" value="작성" style="margin-bottom: 10px;">
				<br><br><br>
			</form>
			
			<table  width="640px" border="0px;" style="margin-bottom: 50px;">
				<tr align="center"  style="border-bottom: 2px solid #0B1B50;">
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
							<td>
								<c:if test="${sessionScope.memberDTO.id == comment.memberDTO.id 
												|| sessionScope.memberDTO.authority == true}">
									<a href="javascript:commentDelete('${comment.rno}',
												'${comment.memberDTO.id}','${comment.sno}')">
									<img alt="삭제" src="/holosolo/img/x.png" border="0" style="width: 20%;"></a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- 댓글의 페이징이 시작된다. -->
			<div align="center">
				<c:if test="${requestScope.commentListByPageDTO.pagingBean.previousPageGroup}">
					<a href="/holosolo/share.do?command=shareShowDetail&&sno=${ requestScope.shareTipDTO.sno }&&replyPageNo=${requestScope.commentListByPageDTO.pagingBean.startPageOfPageGroup-1}&&category=${requestScope.selectedCategory}">
					<img src="/holosolo/img/left_arrow_btn.gif"></a>
				</c:if> &nbsp;&nbsp;
				
				<c:forEach var="i"
					begin="${requestScope.commentListByPageDTO.pagingBean.startPageOfPageGroup}"
					end="${requestScope.commentListByPageDTO.pagingBean.endPageOfPageGroup}">
					<c:choose>
						<c:when test="${requestScope.commentListByPageDTO.pagingBean.nowPage!=i}">
							<a href="/holosolo/share.do?command=shareShowDetail&&sno=${ requestScope.shareTipDTO.sno }&&replyPageNo=${i}&&category=${requestScope.selectedCategory}">${i}</a>
						</c:when>
						<c:otherwise>
							${i}
						</c:otherwise>
					</c:choose>
				</c:forEach>
				&nbsp;&nbsp;
	
				<c:if test="${requestScope.commentListByPageDTO.pagingBean.nextPageGroup}">
					<a href="/holosolo/share.do?command=shareShowDetail&&sno=${ requestScope.shareTipDTO.sno }&&replyPageNo=${requestScope.commentListByPageDTO.pagingBean.endPageOfPageGroup+1}&&category=${requestScope.selectedCategory}">
						<img src="/holosolo/img/right_arrow_btn.gif">
					</a>
				</c:if>
				<!-- 여기까지가 내용, 댓글, 그리고 댓글의 페이지가 진행된 부분이다. -->
			</div>
		</div>
		
		<div class="col-md-4" style="margin-top: 60px; padding-bottom: 15px;
									 border-radius: 33px; background-color: rgba(177, 176, 176, 0.12);">
			<h3 align="center">다른 게시글 보러가기</h3>
			<table id="listTable" border="0px" align="center" style="width: 300px; margin-top: 30px;">
				<thead>
					<tr>
						<td>사진</td>
						<td>제목</td>
						<td>작성자</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="sdto" items="${requestScope.shareTipList}" varStatus="i">
						<tr align="center" id="tr${i.count}">
							<td>
								<img src="/holosolo/resources/uploadImg/sharetipImg/${sdto.fileName}" 
									width="100px" height="80px"
									class="attachment-portfolio_fixed wp-post-image" />
							</td>
							<c:choose>
								<c:when test="${ shareTipDTO.sno != sdto.sno }">
									<td><a href="/holosolo/share.do?command=shareShowDetail&&sno=${sdto.sno}&&category=${requestScope.selectedCategory}">${sdto.title}</a></td>
								</c:when>
								<c:otherwise>
									<td>${sdto.title}</td>
									<script>
										$('#tr${i.count}').css('background-color','rgba(217, 217, 217, 1)');
									</script>
								</c:otherwise>
							</c:choose>
							<td>${sdto.memberDTO.id}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>
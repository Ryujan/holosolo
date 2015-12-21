<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 if(request.getParameter("command")==null){
 response.sendRedirect("/holosolo/content.do?command=contentList");}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/holosolo/css/bootstrap.css" type="text/css">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

<script type="text/javascript">
	
</script>
<style type="text/css">
	.carousel {
		width: 100%;
		height: 300%;
	}
	
	.contentOne {
		width: 300px;
		height: 200px;
		opacity: 0.6;
	}
	
	.contentOne:hover {
		width: 325px;
		height: 225px;
		border: 4px rgba(177, 176, 176, 0.12) solid;
		opacity: 1
	}
	/* 이미지 안에 글씨 */
	/* 이미지  */
	.box {
		padding: 0px 15px 0px 15px;
		position: relative;
		width: 300px;
		height: 200px;
		float: left;
	}
	/* 이미지안에 글씨 */
	.title {
		color: black;
		font-weight: bold;
		text-decoration: none;
	}
</style>

<script type="text/javascript">
$(function(){
	$('.seeAllcate').hover(function(){
		//mouseenter
		 $('#all').html('전체'); 
		
	},function(){
		//mouseleave
		$('#all').html('');
		
	});
	
	$('.cookcate').hover(function(){
		//mouseenter
		 $('#cook').html('음식'); 
		
	},function(){
		//mouseleave
		$('#cook').html('');
		
	});
	
	$('.placecate').hover(function(){
		//mouseenter
		 $('#place').html('장소'); 
		
	},function(){
		//mouseleave
		$('#place').html('');
		
	});
	
	$('.lifecate').hover(function(){
		//mouseenter
		 $('#life').html('생활정보'); 
		
	},function(){
		//mouseleave
		$('#life').html('');
	});
});

</script>
</head>

<body style="background-color: rgba(249, 249, 249, 1)"> 
<div id="container" style="margin-top: 8%;">
	<c:import url="../header.jsp"></c:import>
		<div class="container-fluid">
			<div class="row">
				<div id="front-page" class="content-area col-md-14">
					<div id="front-home" class="container-fluid ">
						<div class="col-md-15" align="center" style="margin-top: 18px;">
							<table style="width: 400px; height: 75px;">
								<tr align="center">
									<td>
										<table class="seeAllcate">
											<tr>
												<td align="center">
													<a href="content.do?command=contentList&&category=seeAll">
														<img alt="" src="img/all.png" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="all"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="cookcate">
											<tr>
												<td align="center">
													<a href="content.do?command=contentList&&category=Cooking">
														<img alt="" src="img/cook1.png" style="padding-right: 15px; padding-left: 15px; 
															width: 80px; height: 50px;"></a><br>
														<b><span id="cook"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="placecate">
											<tr>
												<td align="center">
													<a href="content.do?command=contentList&&category=Place">
														<img alt="" src="img/place.png" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="place"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="lifecate">
											<tr>
												<td align="center">
													<a href="content.do?command=contentList&&category=Information">
													<img alt="" src="img/idea.png" style="padding-right: 15px; 
														padding-left: 15px; width: 80px; height: 50px;"></a><br>
													<b><span id="life"></span></b>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div class="container" style="margin-top: 3%;">
							<article class="entry-content ">
								<div id="gridblog" class="infinite">
									<c:forEach var="cdto" items="${requestScope.contentListDTO.contentList}">
										<div id="contentImg" class="thumbs col-md-4"
											style="height: 250px">
											
											<div align="center">
												<a href="javascript:verifyLogin('${cdto.cno}', 'detail', 'content', '${requestScope.category}')">
													<div class="box">
														<img class="contentOne" src="resources/uploadImg/contentImg/${cdto.fileName}"
																style="border-radius: 25px;" width="300" height="200" /><br>
														<div style="width: 330px;" class="titlediv" align="left">
															<c:if test="${cdto.category == 'SeeAll'}">
																<c:set var="category" value="전체보기" />
															</c:if>
															<c:if test="${cdto.category == 'Cooking'}">
																<c:set var="category" value="요리" />
															</c:if>
															<c:if test="${cdto.category == 'Place'}">
																<c:set var="category" value="장소" />
															</c:if>
															<c:if test="${cdto.category == 'Information'}">
																<c:set var="category" value="생활정보" />
															</c:if>
															<font class="title" size="3px;"><b>[ ${category } ]</b> ${cdto.title}</font>
														</div>
													</div>
												</a>
											</div>		
										</div>
									</c:forEach>
								</div>
							</article>
						</div>
			
						<!-- 글쓰기 -->
						<div align="center" id="div" style="padding: 20px;">
							<form name="frm" method="post" class="form" action="content.do">
								<input type="hidden" name="command" value="contentSearch">
								<input type="hidden" id="category" name="category" value="${ requestScope.category }">
								<table style="width: 50%; margin: 0px 0px 1% 2%">
									<tr>
										<td align="center">
											<select name="sort" class="form-control2">
												<option value="content">내용</option>
												<option value="writer">작성자</option>
												<option value="title">제목</option>
											</select>&nbsp;
											<input type="text" id="word" name="word" class="form-control1" placeholder="Search"	> 
											<button type="submit" class="btn btn-default" name="search" style="margin-bottom: 4px;">검색</button>
											<c:if test="${sessionScope.memberDTO.authority == true}">
												<a href="javascript:verifyLogin('', 'write', 'content')" 
														class="btn btn-default" style="margin-bottom: 4px;">
													<span><img src="img/write.png"></span>글쓰기</a>
											</c:if>
										</td>
									</tr>
								</table>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!--@@@@@ 페이징 처리@@@@@@ -->
								<!-- 페이징 처리 -->
								<%-- 이전 페이지 그룹이 있으면 이미지 보여준다.
									  이미지 링크는 현 페이지 그룹 시작페이지 번호 -1 =>
									  이전 페이지 그룹의 마지막 페이지 번호로 한다. 
								 --%>
								<c:if test="${requestScope.contentListDTO.pagingBean.previousPageGroup}">
									<a href="content.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${requestScope.contentListDTO.pagingBean.startPageOfPageGroup-1}">
									<img src="./img/left_arrow_btn.gif"></a>
								</c:if> &nbsp;&nbsp;
								<%-- PagingBean 을 이용해서 현재 페이지에 해당되는 페이지그룹의
									 시작페이지~~마지막페이지까지 화면에 보여준다. 
									 이 때 현재 페이지를 제외한 나머지 페이지는 링크를 걸어
									 해당 페이지에 대한 게시물 리스트 조회가 가능하도록 한다. 
								--%>
								
								<c:forEach var="i"
									begin="${requestScope.contentListDTO.pagingBean.startPageOfPageGroup}"
									end="${requestScope.contentListDTO.pagingBean.endPageOfPageGroup}">
									<c:choose>
										<c:when test="${requestScope.contentListDTO.pagingBean.nowPage!=i}">
											<a href="content.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${i}">${i}</a>
										</c:when>
										<c:otherwise>
											${i}
										</c:otherwise>
									</c:choose>
								</c:forEach>
								&nbsp;&nbsp;
								<%-- 다음 페이지 그룹이 있으면 화살표 이미지를 보여준다.
									 이미지 링크는 현재 페이지 그룹의 마지막 번호 + 1 => 
									 다음 그룹의 시작 페이지로 링크한다. 
									 right_arrow_btn.gif
								--%>
			
								<c:if test="${requestScope.contentListDTO.pagingBean.nextPageGroup}">
									<a href="content.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${requestScope.contentListDTO.pagingBean.endPageOfPageGroup+1}">
										<img src="./img/right_arrow_btn.gif">
									</a>
								</c:if>
							</form>
						</div>
					</div>
				</div>	
			</div>
		</div>
		<c:import url="../footer.jsp"></c:import>
	</div>
</body>
</html>
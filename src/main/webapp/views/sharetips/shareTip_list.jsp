<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 if(request.getParameter("command")==null){
 response.sendRedirect("/holosolo/share.do?command=shareList"); }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<link rel="stylesheet" href="/holosolo/css/bootstrap.css" type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<style type="text/css">
	#shareList *{
		vertical-align: middle;
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
<body>
	<div id="container" style="margin-top:8%;">
		<c:import url="../header.jsp"></c:import>
		<div class="container-fluid" >
			<div class="row">
				<div id="front-page" class="col-md-14">
					<div id="front-home" class="container-fluid ">
						<div class="col-md-15" align="center" style="margin-top: 18px;">
							<table style="width: 400px; height: 75px;">
								<tr align="center">
									<td>
										<table class="seeAllcate">
											<tr>
												<td align="center">
													<a href="/holosolo/share.do?command=shareList&&category=seeAll">
														<img alt="" src="/holosolo/img/all.png" style="padding-right: 15px; 
																padding-left: 15px; width: 80px; height: 50px;"></a><Br>
													<b><span id="all"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="cookcate">
											<tr>
												<td align="center">
													<a href="/holosolo/share.do?command=shareList&&category=Cooking">
														<img alt="" src="/holosolo/img/cook1.png" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="cook"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="placecate">
											<tr>
												<td align="center">
													<a href="/holosolo/share.do?command=shareList&&category=Place">
													<img alt="" src="/holosolo/img/place.png"
														style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="place"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="lifecate">
											<tr>
												<td align="center">
													<a href="/holosolo/share.do?command=shareList&&category=Information">
														<img alt="" src="/holosolo/img/idea.png"
														style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="life"></span></b>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>

				<div class="container" align="center" style="margin-top: 3%;">
					<table id="shareList" style="width: 80%; vertical-align: middle;" class="table table-hover" >
						<thead>
							<tr align="center">
								<td><b>사진</b></td>
								<td><b>제목</b></td>
								<td><b>작성자</b></td>
								<td><b>작성일자</b></td>
								<td><b>조회수</b></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="sdto" items="${requestScope.shareTipListDTO.shareTipList}">
								<tr align="center">
									<td style="font-size: 13px;">
										<a href="javascript:verifyLogin('${sdto.sno}', 'detail', 'share','${requestScope.category}')">
											<img src="/holosolo/resources/uploadImg/sharetipImg/${sdto.fileName}"
													style="margin: 5px; width: 100px; height: 90px" /></a>
									</td>
									<td style="font-size: 13px;"><a href="javascript:verifyLogin('${sdto.sno}', 'detail', 'share','${requestScope.category}')">${sdto.title}</a></td>
									<td style="font-size: 13px;">${sdto.memberDTO.id}</td>
									<td style="font-size: 13px;">${sdto.postedTime}</td>
									<td style="font-size: 13px;">${sdto.hits }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<!-- 글쓰기 -->
				<div align="center" id="div" style="padding: 20px;">
					<form name="frm" method="post" class="form" action="/holosolo/share.do">
						<input type="hidden" name="command" value="shareSearch"> 
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
									<c:if test="${sessionScope.memberDTO != null }">
										<a href="javascript:verifyLogin('', 'write', 'share')" class="btn btn-default" style="margin-bottom: 4px;">
											<span><img src="/holosolo/img/write.png"></span>글쓰기</a>
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
						<c:if test="${requestScope.shareTipListDTO.pagingBean.previousPageGroup}">
							<a href="/holosolo/share.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${requestScope.shareTipListDTO.pagingBean.startPageOfPageGroup-1}">
							<img src="/holosolo/img/left_arrow_btn.gif"></a>
						</c:if> &nbsp;&nbsp;
						<%-- PagingBean 을 이용해서 현재 페이지에 해당되는 페이지그룹의
							 시작페이지~~마지막페이지까지 화면에 보여준다. 
							 이 때 현재 페이지를 제외한 나머지 페이지는 링크를 걸어
							 해당 페이지에 대한 게시물 리스트 조회가 가능하도록 한다. 
						--%>
						<c:forEach var="i"
							begin="${requestScope.shareTipListDTO.pagingBean.startPageOfPageGroup}"
							end="${requestScope.shareTipListDTO.pagingBean.endPageOfPageGroup}">
							<c:choose>
								<c:when test="${requestScope.shareTipListDTO.pagingBean.nowPage!=i}">
									<a href="/holosolo/share.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${i}">${i}</a>
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
	
						<c:if test="${requestScope.shareTipListDTO.pagingBean.nextPageGroup}">
							<a href="/holosolo/share.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${requestScope.shareTipListDTO.pagingBean.endPageOfPageGroup+1}">
								<img src="/holosolo/img/right_arrow_btn.gif">
							</a>
						</c:if>
					</form>
				</div>
			</div>
		</div>
		<c:import url="../footer.jsp"></c:import>
	</div>
</body>
</html>
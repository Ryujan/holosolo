<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	if(request.getParameter("command")==null){
	response.sendRedirect("/holosolo/group.do?command=groupList&&category=seeAll"); }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

<script type="text/javascript">

	$(function() {
		$('.seeAllcate').hover(function() {
			//mouseenter
			$('#all').html('전체');
		}, function() {
			//mouseleave
			$('#all').html('');
		});

		$('.cookingcate').hover(function() {
			//mouseenter
			$('#cook').html('요리/음식');
		}, function() {
			//mouseleave
			$('#cook').html('');
		});

		$('.culturecate').hover(function() {
			//mouseenter
			$('#culture').html('문화');
		}, function() {
			//mouseleave
			$('#culture').html('');
		});

		$('.learningcate').hover(function() {
			//mouseenter
			$('#learning').html('지식배움');
		}, function() {
			//mouseleave
			$('#learning').html('');
		});

		$('.talkcate').hover(function() {
			//mouseenter
			$('#talk').html('일상대화');
		}, function() {
			//mouseleave
			$('#talk').html('');
		});

		$('.activitycate').hover(function() {
			//mouseenter
			$('#activity').html('활동');
		}, function() {
			//mouseleave
			$('#activity').html('');
		});
	});
</script>

</head>
<body style="background-color: rgba(249, 249, 249, 1)">
	<div id="container" style="margin-top: 8%;">
		<c:import url="../header.jsp"></c:import>
		<div class="container-fluid">
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
													<a href="/holosolo/group.do?command=groupList&&category=seeAll">
														<img alt="" src="/holosolo/img/all.png" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="all"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="cookingcate">
											<tr>
												<td align="center">
													<a href="/holosolo/group.do?command=groupList&&category=Cooking">
														<img alt="" src="/holosolo/img/cook2.png" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="cook"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="culturecate">
											<tr>
												<td align="center">
													<a href="/holosolo/group.do?command=groupList&&category=Culture">
														<img alt="" src="/holosolo/img/문화.png" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="culture"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="learningcate">
											<tr>
												<td align="center">
													<a href="/holosolo/group.do?command=groupList&&category=Learning">
														<img alt="" src="/holosolo/img/지식배움.png" style="padding-right: 15px;
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="learning"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="talkcate">
											<tr>
												<td align="center">
													<a href="/holosolo/group.do?command=groupList&&category=Talk">
														<img alt="" src="/holosolo/img/일상대화.jpg" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="talk"></span></b>
												</td>
											</tr>
										</table>
									</td>
									<td>
										<table class="activitycate">
											<tr>
												<td align="center">
													<a href="/holosolo/group.do?command=groupList&&category=Activity">
														<img alt="" src="/holosolo/img/활동.png" style="padding-right: 15px; 
															padding-left: 15px; width: 80px; height: 50px;"></a><br>
														<b><span id="activity"></span></b>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</div>
					</div>
					<div class="container" align="center" style="margin-top: 2%;">
						<c:forEach var="gdto" items="${ requestScope.groupListDTO.groupList }" varStatus="i">
						<button id="showBtn${i.count}" onclick="javascript:verifyLogin('${gdto.gno}', 'detail', 'group')" 
								style="border: 0px; background-color:rgba(249, 249, 249, 1); margin-top: 15px; padding: 0px; width:65%">
						<div id="groupDetail${i.count}" style="border-radius: 24px; border-image: none; width: 100%; background-color: rgba(153, 154, 154, 0.09); padding: 10px">
							<!--border: solid 2px white;    -->
							<script type="text/javascript">
								$(function(){
									$('#showBtn${i.count}').mousedown(function(){
										$('#groupDetail${i.count}').css('background-color','rgba(153, 154, 154, 0.09)');
									});//mousedown
									
									$('#showBtn${i.count}').mouseleave(function(){
										$('#groupDetail${i.count}').css('background-color','rgba(153, 154, 154, 0.09)');
									});//mouseleave
								});
							</script>
								<table style="width: 100%">
									<tr>
										<td width="70%" style="padding: 30px; font-size: 16px" align="left">
											<font color="navy" style="font-size: 18px">
												<b style="position: absolute; right: 41.5%">
													<c:if test="${ gdto.status == 0 }">
														<font color="rgba(26, 26, 255, 0.71)">진행중...</font>
													</c:if> <c:if test="${ gdto.status == 1 }">
														<font color="rgba(255, 166, 0, 0.65)">종료</font>
													</c:if> <c:if test="${ gdto.status == -1 }">
														<font color="rgba(255, 0, 0, 0.7)">취소</font>
													</c:if>
												</b>
											<c:if test="${gdto.category == 'Cooking'}">
												<c:set var="category" value="요리/음식"/>
											</c:if>
											<c:if test="${gdto.category == 'Culture'}">
												<c:set var="category" value="문화"/>
											</c:if>
											<c:if test="${gdto.category == 'Learning'}">
												<c:set var="category" value="지식배움"/>
											</c:if>
											<c:if test="${gdto.category == 'Talk'}">
												<c:set var="category" value="일상대화"/>
											</c:if>
											<c:if test="${gdto.category == 'Activity'}">
												<c:set var="category" value="활동"/>
											</c:if>
											<b>[ ${ category } ] - </b>${ gdto.title }
											</font><br>
											<b>작성자 : </b>${ gdto.memberDTO.id }<br>
											<!-- 프로그래스바 -->
											<div class="progress" style="margin: 10px 0px 10px 0px; text-align: right">
												<div id="prog${i.count}" class="progress-bar" role="progressbar" 
														aria-valuenow="${ gdto.currentNum*10 }" aria-valuemin="10" 
														aria-valuemax="${ gdto.maxNum*10 }" 
														style="width:${ (100/gdto.maxNum)*gdto.currentNum }%">
													<font color="black" style="font-weight: bold; font-size: 16px">${ gdto.currentNum } 명</font>
													<script type="text/javascript">
														$(function(){
															var percent = ${ (100/gdto.maxNum)*gdto.currentNum };
															if( percent >= 40 && percent < 70){
																$('#prog${i.count}').addClass('progress-bar-warning');
															}if( percent >= 70 && percent <=100){
																$('#prog${i.count}').addClass('progress-bar-danger');
															}
														});
													</script>
												</div>
											</div>
											<b>모임 조건 : </b>
												<c:if test="${ gdto.sexCheck == 'M' }">
													<font color="green" style="font-weight: bold">남자</font>
												</c:if> 
												<c:if test="${ gdto.sexCheck == 'W' }">
													<font color="crimson" style="font-weight: bold">여자</font>
												</c:if> 
												<c:if test="${ gdto.sexCheck == 'U' }">
													<font style="font-weight: bold">성별무관</font>
												</c:if>
											<font color="black" style="font-size: 16px; position: absolute; right: 42%" >모집 인원 : <b>${ gdto.maxNum } 명</b></font>
										</td>
										<td width="30%" align="left">
											<a href="/holosolo/group.do?command=groupShowDetail&&gno=${gdto.gno}">
												<img alt="" src="/holosolo/resources/uploadImg/groupImg/${gdto.fileName}"
													style="margin: 10px; width: 160px; height: 140px;"></a><Br>
											<font style="font-weight: bold;">[ 모임 일시 ] - </font>${ gdto.promiseDate }
										</td>
									</tr>
								</table>
								<p>
							</div>
						</button>
						</c:forEach>
					</div>

				<!-- 글쓰기 -->
					<div align="center" style="padding: 20px;"><!-- class="col-md-12" -->
						<c:if test="${sessionScope.memberDTO != null }">
							<a href="javascript:verifyLogin('', 'write', 'group')" class="btn btn-default">
								<span><img src="/holosolo/img/open.png"></span>모임개설</a>
						</c:if>
					<!--@@@@@ 페이징 처리@@@@@@ -->
						<br><br>
						<!-- 페이징 처리 -->
						<%-- 이전 페이지 그룹이 있으면 이미지 보여준다.
							  이미지 링크는 현 페이지 그룹 시작페이지 번호 -1 =>
							  이전 페이지 그룹의 마지막 페이지 번호로 한다. 
						 --%>
						<c:if test="${requestScope.groupListDTO.pagingBean.previousPageGroup}">
							<a href="/holosolo/group.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${requestScope.groupListDTO.pagingBean.startPageOfPageGroup-1}">
							<img src="/holosolo/img/left_arrow_btn.gif"></a>
						</c:if> &nbsp;&nbsp;
						<%-- PagingBean 을 이용해서 현재 페이지에 해당되는 페이지그룹의
							 시작페이지~~마지막페이지까지 화면에 보여준다. 
							 이 때 현재 페이지를 제외한 나머지 페이지는 링크를 걸어
							 해당 페이지에 대한 게시물 리스트 조회가 가능하도록 한다. 
						--%>
						<c:forEach var="i"
							begin="${requestScope.groupListDTO.pagingBean.startPageOfPageGroup}"
							end="${requestScope.groupListDTO.pagingBean.endPageOfPageGroup}">
							<c:choose>
								<c:when test="${requestScope.groupListDTO.pagingBean.nowPage!=i}">
									<a href="/holosolo/group.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${i}">${i}</a>
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
	
						<c:if test="${requestScope.groupListDTO.pagingBean.nextPageGroup}">
							<a href="/holosolo/group.do?command=${beforeCommand}&&category=${ requestScope.category }&&pageNo=${requestScope.groupListDTO.pagingBean.endPageOfPageGroup+1}">
								<img src="/holosolo/img/right_arrow_btn.gif">
							</a>
						</c:if>
					</div>				
				</div>
			</div>
		</div>
		<c:import url="../footer.jsp"></c:import>
	</div>

</body>
</html>
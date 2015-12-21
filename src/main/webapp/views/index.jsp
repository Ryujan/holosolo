<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
 if(request.getParameter("command")==null){
 response.sendRedirect("/holosolo/views/intro.jsp");}
%>   
<!DOCTYPE html>
<html lang="ko-KR" class="no-js">
<head>
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta charset="UTF-8">

<style type="text/css">
	.carousel {
		width: 100%;
		height: 300%;
	}
	
	.contentImgOne:hover {
		opacity: 1;
		border: 4px rgba(177, 176, 176, 0.12) solid;
		border-radius: 30px;
	}
	.contentImgOne{
		opacity: 0.7;
	}
	
	#groupform:hover{
		border-bottom:1px solid;
		border-top:1px solid;
		background-color:rgba(153, 154, 154, 0.09);
		border-color: rgba(153, 154, 154, 0.09);
	}
</style>

</head>
<body>
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="margin-top: 6%;">
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<div class="carousel-inner" role="listbox">
			<div class="item active">
				<img src="img/slide1.jpg" style="width: 100%; height: 50%;" />
			</div>
			<div class="item">
				<img src="img/slide2.jpg" style="width: 100%; height: 50%;" />
			</div>
			<div class="item">
				<img src="img/slide3.jpg" style="width: 100%; height: 50%;" />
			</div>
		</div>

		<a class="left carousel-control" href="#carousel-example-generic"
			role="button" data-slide="prev">
			<span class="sr-only">Previous</span>
		</a> <a class="right carousel-control"
			href="#carousel-example-generic" role="button" data-slide="next">
			<span class="sr-only">Next</span>
		</a>
	</div>
	<div id="container">
		<div class="row">
			<c:import url="header.jsp"></c:import>
			<div class="container-fluid">
				<!-- 메인 div -->
				<div class="container" align="center" style="margin-bottom: 10px;">
					<h2 align="center">
						<b>나만의 시간을 <font color="orange" size="6px">더</font> 유익하게</b>
					</h2>
					
					<!-- content 구역 -->
					<div id="gridblog" style="height: 250px;  border-bottom: 1px solid; 
						border-top:1px solid; border-color: rgba(153, 154, 154, 0.09); margin-bottom: 5%; margin-top: 1%;">
						<c:forEach var="cdto" items="${requestScope.contentListDTO.contentList}"
							begin="0" end="3" varStatus="i">
							<c:if test="${cdto.category == 'Cooking'}">
								<c:set var="category" value="음식"/>
							</c:if>
							<c:if test="${cdto.category == 'Place'}">
								<c:set var="category" value="장소"/>
							</c:if>
							<c:if test="${cdto.category == 'Information'}">
								<c:set var="category" value="생활정보"/>
							</c:if>
							<div class="thumbs col-md-3"
                        			style="width: 25%; height: 100%; margin-top: 17px;">
								<div class="box" style="height: 184px; width: 100%;">
									<a href="javascript:verifyLogin('${cdto.cno}', 'detail', 'content', '${cdto.category}')">
										<img class="contentImgOne" style="width: 100%; height: 100%; border-radius: 25px;"
											src="resources/uploadImg/contentImg/${cdto.fileName}" />
										<span class="title"><b>[ ${ category } ] - </b> &nbsp;${ cdto.title }</span>
									</a>
								</div>
							</div>
						</c:forEach>
					</div>

					<!-- sharetip -->
					<div class="col-xs-6" style="width:48%; margin-right: 2%;  border:2px solid; border-color: rgba(153, 154, 154, 0.09); border-radius: 24px;">
						<h2 align="center">
							<b>나의 의견을 <font color="orange" size="6px">더</font> 가치있게</b>
						</h2>
						<div align="center" class="thumbs col-md-5 " style="margin-top: 2.5%; width: 100%;">
							<table class="table table-hover">
								<thead>
									<tr align="center">
										<td width="175px;"><b>사진</b></td>
										<td width="185px;"><b>제목</b></td>										
										<td><b>조회수</b></td>
									</tr>
								</thead>
								<tbody style="border-bottom: 1px solid; border-bottom-color: rgba(153, 154, 154, 0.09);">
									<c:forEach var="sdto" items="${requestScope.shareTipListDTO.shareTipList}"
										begin="0" end="4">
										<tr align="center" style="height: 150px;">
											<td width="175px;">
												<a href="javascript:verifyLogin('${sdto.sno}', 'detail', 'share', '${sdto.category}')">
													<img src="resources/uploadImg/sharetipImg/${sdto.fileName}"
														style="margin-top:6%; width: 146px; height: 110px;" /></a>
											</td>
											<td width="185px;" style="vertical-align: middle">
												<a href="javascript:verifyLogin('${sdto.sno}', 'detail', 'share', '${sdto.category}')">${sdto.title}</a>
											</td style="vertical-align: middle">											
											<td>${sdto.hits }</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>

					<!-- group -->
					<div class="col-xs-6"
						style="width: 48%; margin-left: 2%; border: 2px solid; border-color: rgba(153, 154, 154, 0.09); border-radius: 24px; padding-bottom: 14px">
						<h2 align="center" style="margin-bottom: 3.5%;">
							<b>우리와 함께하며 <font color="orange" size="6px">더</font> 즐겁게</b>		
						</h2>
						<c:forEach var="gdto" items="${requestScope.groupList}" begin="0"
							end="4">
							<div align="middle" class="thumbs col-md-5"
								id="groupform"
								style="margin-bottom: 0.8%; width: 100%;">
								<div style="padding: 5px; height: 156px;">
									<div class="col-xs-2" style="width: 40%; margin-top: 4%;">
										<img style="width: 100%; height: 110px;" align="middle"
											src="/holosolo/resources/uploadImg/groupImg/${gdto.fileName}">
									</div>
									<div class="col-xs-4" style="width: 60%;">
										<div style="width: 100%; margin-top: 8%;" align="left">
											<div class="col-xs-1" style="width: 37%;">제목</div>
											<div class="col-xs-2" style="width: 63%;">${gdto.title}</div>
										</div>
										<div style="width: 100%;" align="left">
											<div class="col-xs-1" style="width: 37%;">카테고리</div>
											<div class="col-xs-2" style="width: 63%;">
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
												${category}
											</div>
										</div>
										<div style="width: 100%;" align="left">
											<div class="col-xs-1" style="width: 37%;">아이디</div>
											<div class="col-xs-2" style="width: 63%;">${gdto.memberDTO.id}</div>
										</div>
										<div style="width: 100%;" align="left">
											<div class="col-xs-1" style="width: 37%;">모임일자</div>
											<div class="col-xs-2" style="width: 63%;">${gdto.promiseDate }</div>
											<a class="btn btn-sm btn-default"
			                                    href="javascript:verifyLogin('${gdto.gno}', 'detail', 'group', '${gdto.category}')"
			                                    style="width: 46px; margin-left: 84%;">more</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- group -->
			</div>			
		</div>	<!-- row -->
		<c:import url="footer.jsp" />
	</div>	<!-- container -->
</body>
</html>
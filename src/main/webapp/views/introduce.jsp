<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<title>하나동 사람들</title>
<meta charset="UTF-8">
</head>

<body>
	<div class="container" style="margin-top: 2%;">
	<c:import url="header.jsp"></c:import>
		<div id="introPage" align="center">
			<div class="row"
				style="margin-top: 10%; background-color: rgba(11, 27, 80, 1); background-image:url('/holosolo/img/intro.png'); height: 180px;">
				<div class="articleTitle">
					<h1 id="title"
						style="color: white; margin-bottom: 0%; margin-top: 3%">
						<b>HANAUS</b>
					</h1>
					<br>
					<h5 style="margin: 0%; color: white">
						<b> 하나 + 우리 </b>
					</h5>
					<br>
					<h3 style="color: white; margin: 0px;">
						<span><b>뭔가 할만한게 없을까?</b></span>
					</h3>
					<br>
				</div>
			</div>
			
			<!-- 컨텐츠인트로  -->
			<div id="contentIntro" class="row" style="margin-top: 3%; margin-left: 13%" align="left">
				<h3 style="margin-left: 13%">
					<b>나만의 시간을 <font color="orange" size="6px">더</font> 유익하게</b>
				</h3>
				<div class="col-md-5"
						style="border-radius: 25px; background-color: rgba(222, 222, 222, 1); padding-top: 10px; padding-bottom: 10px; width: 12%; margin: 0%; margin-left: 3%; margin-top: 3%"
						align="center">
					<img alt="content" src="/holosolo/img/content.png"><br>
					<strong>내게맞는</strong><br> 
					<span>HANAUS를</span><br> 
					<span>배우다</span><br>
				</div>
				<div class="col-md-7" style="margin-left: 2%; margin-top: 2%; margin-bottom: 2%; ">
					<img alt="" src="/holosolo/img/cook1.png"
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px; margin-bottom: 2%;">
						<span><b>집에서 간단하게 할수 있는 요리정보</b></span><br>
					<img alt="" src="/holosolo/img/place.png"
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px; margin-bottom: 2%;">
						<span><b>혼자서 가볼수 있는 장소/공간정보</b></span><br>
					<img alt="" src="/holosolo/img/idea.png"
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px; margin-bottom: 2%;">
						<span><b>알아두면 좋은 생활 꿀팁정보</b></span><br>				
					</div>
			</div>
			<!--그룹인트로 -->
			<div id="groupIntro" class="row" style="margin-top: 3%; margin-right: 13% " align="right">
				<h3 style="margin-right: 13%">
					<b>우리와 함께하며 <font color="orange" size="6px">더</font> 즐겁게</b>
				</h3>
				<div class="col-md-7" style="margin-left: 45%; margin-top: 1%; margin-bottom: 2%;width: 36%;" align="left">
					<img alt="" src="/holosolo/img/cook2.png"
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px;margin-bottom: 3%;">
						<span><b>요리/음식/맛집탐방을 다른이들과 함께</b></span><br>
				 
					<img alt="" src="/holosolo/img/문화.png" 
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px; margin-bottom: 3%;">
						<b><span id="culture">영화/연극/뮤지컬등을 다른이들과 함께</span></b><br>
						
					<img alt=""src="/holosolo/img/지식배움.png" 
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px; margin-bottom: 3%;">
						<b><span id="learning">공통된 스터디/관심사를 다른이들과 함께</span></b><br>
						
					<img alt="" src="/holosolo/img/일상대화.jpg" 
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px; margin-bottom: 3%;">
						<b><span id="talk">고민/토론/만남을 다른사람들과 함께</span></b><br>
						
					<img alt="" src="/holosolo/img/활동.png" 
						style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px; margin-bottom: 3%;">
						<b><span id="activity">스포츠/활동/레저를 다른사람들과 함께</span></b>
					<br>
				</div>
				
				<div class="col-md-5" style="border-radius: 25px; background-color: rgba(222, 222, 222, 1); 
					padding-top: 10px; padding-bottom: 10px;width: 12%; margin: 0%; 
					margin-left: 2%;margin-top: 7%" align="center">
					<img alt="group" src="/holosolo/img/group.png"><br>
					<strong>함께하는</strong><br> 
					<span>HANAUS들의</span><br> 
					<span>공간</span><br>
				</div>
			</div>
			<!-- 쉐어팁인트로 -->
			<div id="sharetipIntro" class="row"  class="row" style="margin-top: 3%; margin-left: 13%;" align="left">
				<h3 style="margin-left: 13%">
					<b>나의 의견을 <font color="orange" size="6px">더</font> 가치있게</b>
				</h3>
				<div class="col-md-5" style="border-radius: 25px; background-color: rgba(222, 222, 222, 1); 
						padding-top: 10px; padding-bottom: 10px; width: 12%; margin: 0%; 
						margin-left: 3%; margin-top: 3%" align="center">
					<img alt="share" src="/holosolo/img/sharetip.png"><br>
					<strong>너를 위한</strong><br> 
					<span>HANAUS들의</span><br> 
					<span>이야기</span><br>
				</div>
				<div class="col-md-7" style="margin-left: 2%; margin-top: 2%;margin-bottom: 2%;">
					<img alt="" src="/holosolo/img/cook1.png" style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px;margin-bottom: 2%;">
							<b><span id="cook2">집에서 하는 나만의 요리/내가 먹어본 요리를 뽐내보자</span></b><br>
					<img alt="" src="/holosolo/img/place.png" style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px;margin-bottom: 2%;">
							<b><span id="place">내가 알고있는 좋은 장소를 공유해보자</span></b><br>
					<img alt="" src="/holosolo/img/idea.png" style="padding-right: 15px; padding-left: 15px; width: 80px; height: 50px;margin-bottom: 2%;">
							<b><span id="life">나만 아는 나만의 꿀팁을 공유하자</span></b><br>			
				</div>
			</div>
		</div>
	</div>
	<c:import url="footer.jsp"></c:import>
</body>
</html>

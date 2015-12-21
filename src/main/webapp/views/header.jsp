<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko-KR" class="no-js">
<head>
<meta charset="UTF-8">

<script type='text/javascript' src='/holosolo/js/jquery.js'></script>
<script type='text/javascript' src='/holosolo/js/jquery-migrate.min.js'></script>
<script src="/holosolo/js/jquery-1.9.1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/holosolo/js/bootstrap.min.js"></script>
<script src="/holosolo/js/ie10-viewport-bug-workaround.js"></script>
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<link rel="stylesheet" href="/holosolo/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/holosolo/css/css.css">
<link rel="stylesheet" href="/holosolo/css/font-awesome.min.css">
<link rel="stylesheet" href="/holosolo/css/style.css">
<link rel='stylesheet' id='box-style-css' href='/holosolo/css/style-box.css'
	type='text/css' media='all' />
<title>취향저격</title>
<style type="text/css">
	@import url('http://fonts.googleapis.com/earlyaccess/jejugothic.css');
	font{font-family: 'Jeju Gothic';}
	#profileImgView{
		background-image: url("/holosolo/img/profileBg.jpg");
		width: 200px;
		height: 180px;
	}
	
	#myTable th{
		text-align: center;
		border-bottom: 1px rgba(184, 204, 228, 1) solid;
		padding-bottom: 3px;
		padding-top: 3px;
	}
	
	#myTable tr td{
		text-align: center;
	}
</style>
</head>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	function logout() {
		if (confirm("로그아웃 하시겠습니까?"))
			location.href = "/holosolo/member.do?command=logout";
	}
	function login() {
		window.showModalDialog("/holosolo/views/index.jsp", window,
			"dialogLeft:0px; dialogTop:0px; dialogWidth:200px; dialogHeight:200px");
	}

	function verifyLogin(no, cmd, kind, category) {
		$.ajax({
			type : "post",
			url : "/holosolo/member.do",
			data : "command=verifyLogin",
			dataType : "json",

			success : function(jsonData) {
				if (jsonData.flag == "false") {
					alert("로그인 후 이용해주세요.")
				} else {
					if (cmd == "detail") {
						if (kind == 'share')
							location.href = "/holosolo/share.do?command=shareShowDetail&&sno="+ no + "&&category="+category;
						else if (kind == 'group')
							location.href = "/holosolo/group.do?command=groupShowDetail&gno="+ no;
						else if (kind == 'content')
							location.href = "/holosolo/content.do?command=contentShowDetail&&cno="+ no + "&&category="+category;
					} else if (cmd == "write") {
						if (kind == 'share')
							location.href = "/holosolo/views/sharetips/sharetip_write.jsp";
						else if (kind == 'group')
							location.href = "/holosolo/views/groups/group_write.jsp";
						else if (kind == 'content')
							location.href = "/holosolo/views/contents/content_write.jsp";
					}
				}
			}
		})
	}

	$(function() {
		$('#id').keyup(function() {
			var inputLength = $(this).val().length;
			//alert(inputLength);
			if (inputLength < 5) {
				$('#idCheckView').html("아이디는 5자이상").css(
						'color', 'red');
				return;
			} else if (inputLength >= 5) {
				$.ajax({
					type : "post",
					url : "/holosolo/member.do",
					data : "command=idCheck&&id="+ $('#id').val(),
					dataType : "json",

					success : function(data) {//data가 json 객체를 받는다.
						if (data.flag == true) {
							$('#idCheckView').html("아이디 사용불가").css('color', 'red');
						} else {
							$('#idCheckView').html("아이디 사용가능").css('color', 'blue');
						}
					}//callback
				});
			}//else if	
		});//id keyup

		$('#confirm_password').keyup(
		function() {
			var confirm_password = $(this).val();
			var password = $('#password').val();
			if (confirm_password != password) {
				$('#passCheckView').html("비밀번호가 일치하지 않습니다!").css('color', 'red');
				checkPassword = "false";
			} else {
				$('#passCheckView').html("비밀번호가 일치합니다!").css('color','blue');
				checkPassword = "true";
			}
		});//pass keyup

		var checkPassword;
		// line 463
		$('#registerBtn').click(function() {
			if (checkPassword == "false") {
				alert("비밀번호를 체크하세요.");
				$('#confirm_password').val("");
				$('#password').val("");
				return;
			}

			document.registerForm.submit();
		});

		$('#updateBtn').click(function() {
			var popUrl = "/holosolo/views/update_prof_popup.jsp";
			var popOption = "width=370, height=360, resizable=no, scrollbars=no, status=no;";

			window.open(popUrl, "", popOption);
		});

		$('#leaveBtn').click(function() {
			var popUrl = "/holosolo/views/delete_account_popup.jsp";
			var popOption = "width=370, height=400, resizable=no, scrollbars=no, status=no;";

			window.open(popUrl, "", popOption);
		});
						
		// 로그인 : about 313 line
		$('#checkFormBtn').click(function(){
		   $.ajax({
		      type:"post",
		      url: "/holosolo/member.do",
		      data: "command=login&&id="+$('#loginId').val()+"&&password="+$('#loginPassword').val(),
		      dataType: "json",
		      
		      success:function(data){
		         if(data.loginResult == false){
		            alert("존재하지 않는 아이디거나 잘못 입력하셨습니다.");
		            return;
		         }else{
		             location.href="/holosolo/main.do?command=showMain";
		         }
		      }
		   })
		})

        // 12월 4일 추가된 부분 :: 비밀번호 찾기 :: line 355
      
		$('#findPassBtn').click(function() {
			var popUrl = "/holosolo/views/find_password.jsp";
			var popOption = "width=370, height=280, resizable=no, scrollbars=no, status=no;";
	
			window.open(popUrl, "", popOption);
		})
		
		$('#reset').click(function(){
			$('#idCheckView').html('');
			$('#passCheckView').html('');
			$('#registerImgView').html('');
		})
		
		$('#registerClose').click(function(){
			$('#id').val('');			
			$('#idCheckView').html('');
			$('#first_name').val('');				
			$("input:radio[name='sex']").removeAttr('checked');
			$('#user_email').val('');	
			$('#password').val('');	
			$('#confirm_password').val('');	
			$('#passCheckView').html('');
			$('#registerImgView').html('');				
        })
        
        $('#loginClose').click(function(){
			$('#loginId').val('');		
			$('#loginPassword').val('');			
        })
	});
</script>
<body id="narrow">
	<div id="page">
		<!-- 탑메뉴 -->
		<div class="container-fluid">
			<div class="container">
				<div class="navbar navbar-fixed-top">
					<div id="head" style="height: 65px;">
						<nav class="navbar navbar-default navbar-fixed-top">
							<div class="container-fluid"
									style="height:90px ;background-color: white; padding-left: 0px; 
										border-bottom: 2px solid; border-bottom-color: #0B1B50;">
								<div class="nav navbar-nav">
									<a href="/holosolo/intro.jsp">
										<img src="/holosolo/img/메인로고.png"
												style="height: 65px; margin: 12px 0px 0px 20px">
									</a>
								</div>
	
								<div id="center" style="padding-left: 35%;">
									<ul class="nav navbar-nav" style="width: 450px; height: 50px;">
										<li>											
											<a title="content" href="/holosolo/content.do?command=contentList&&category=seeAll"
											   style="padding-bottom: 0px;padding-top: 9%;margin-top: 3%;">
											   <img src='/holosolo/img/내게맞는.png' style="width: 85%;"></a>
										</li>
										<li>
											<a title="shareTip" href="/holosolo/share.do?command=shareList&&category=seeAll"
												style="padding-bottom: 0px;padding-top: 9%;margin-top: 3%;">
												<img src='/holosolo/img/너를위한.png' style="width: 85%;"></a>
										</li>
										<li>											
											<a title="group" href="/holosolo/group.do?command=groupList&&category=seeAll"
												style="padding-bottom: 0px;padding-top: 9%;margin-top: 3%;">
												<img src='/holosolo/img/함께하는.png' style="width: 85%;"></a>
										</li>
										<li>											
											<a title="group" href="/holosolo/main.do?command=introduce" 
												style="padding-bottom: 0px;padding-top: 9%;margin-top: 3%;">
												<img src='/holosolo/img/스토리.png' style="width: 85%;"></a>
										</li>
									</ul>
								</div>
								<c:choose>
									<c:when test="${sessionScope.memberDTO == null}">
										<ul class="nav navbar-nav navbar-right" style="margin-top: 2%; margin-right: 1%">
											<li>
												<a href="#" data-toggle="modal" data-target="#myModal" 
													style="margin-top: 13px;"><i class="fa fa-users"></i> 회원가입 </a>
											</li>
											<li>
												<a href="#" data-toggle="modal" data-target="#myModal2" 
													style="margin-top: 13px;"><i class="fa fa-users" id="login"></i>로그인</a>
											</li>
										</ul>
									</c:when>
									<c:otherwise>
										<ul class="nav navbar-nav navbar-right" style="margin-top: 3%; margin-right: 1%">
											<li style="margin-top: 6.5%">
												<font color="navy" style="font-weight: bold;">
												[ ${sessionScope.memberDTO.id}님 ]</font>
											</li>
											<li>
												<a href="#" data-toggle="modal" data-target="#myModal3">
												 	<i class="fa fa-users"id="mypage"></i>마이페이지</a>
											</li>
	
											<li>
												<a href="/holosolo/member.do?command=logout">
													<i class="fa fa-users"></i> 로그아웃 </a>
											</li>
										</ul>
									</c:otherwise>
								</c:choose>
							</div>
						</nav>
					</div>
				</div>

				<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
				<!-- Modal -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content"
							style="border-width: 3px 3px 3px 3px; border-image: none;">
							<div class="modal-header" style="border-bottom: 2px solid #0B1B50;">
								<button type="reset" class="close" data-dismiss="modal"
									aria-label="Close" id="registerClose"><!-- type="button" -->
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">회원가입</h4>
							</div>
							<div class="modal-body">
								<form name="registerForm" method="post" action="member.do"
									class="form-horizontal" role="form">
									<input type="hidden" name="command" value="memberRegister">
									<div class="form-group">
										<label class="control-label col-sm-3" for="username">아이디
											<font class="req">*</font>
										</label>
										<div class="col-sm-8">
											<input name="id" type="text" onkeyup="idCheck()" id="id"
												required="required" class="form-control" /> 
											<input type="hidden" name="idFlag" value="">
											<span id="idCheckView"></span>
										</div>
									</div>

									<div class="form-group">
										<label for="first_name" class="control-label col-sm-3">이름<font
											class="req">*</font></label>
										<div class="col-sm-8">
											<input name="name" type="text" id="first_name" value=""
												required="required" class="form-control" />
										</div>
									</div>

									<div class="form-group">
										<label for="user_sex" class="control-label col-sm-3"
											style="padding-top: 0px;">성별<font class="req">*</font></label>
										<div class="col-sm-8">
											남&nbsp;<input name="sex" type="radio" id="user_sex" value="M" />
											&nbsp;&nbsp;&nbsp; 여&nbsp;<input name="sex" type="radio"
												id="user_sex" value="W" />
										</div>
									</div>

									<div class="form-group">
										<label for="user_email" class="control-label col-sm-3">Email<font
											class="req">*</font></label>
										<div class="col-sm-8">
											<input name="email" type="text" id="user_email"
												required="required" class="form-control" placeholder="비밀번호 분실시 사용되니 정확히 입력해주세요."/>
										</div>
									</div>

									<div class="form-group">
										<label for="password" class="control-label col-sm-3">비밀번호<font
											class="req">*</font></label>
										<div class="col-sm-8">
											<input name="password" type="password" id="password"
												required="required" class="form-control" />
										</div>
									</div>

									<div class="form-group">
										<label for="confirm_password" class="control-label col-sm-3">비밀번호
											확인<font class="req">*</font></label>
										<div class="col-sm-8">
											<input name="confirm_password" type="password"
												id="confirm_password" required="required"
												class="form-control" /> 
											<span id="passCheckView"></span>
										</div>
									</div>
									
									<div class="form-group">
										<label for="confirm_password" class="control-label col-sm-3">프로필
											사진</label>
										<div class="col-sm-8">
											
											
											<div style="width: 200px; margin-bottom:3%; height: 150px; border: 1.5px solid #CCCCCC; border-radius: 4px;"
												align="center"><!-- #70011C -->
												<span id="registerImgView"></span>
											</div>
											<p>
											<input type="hidden" name="myPhoto" id="fileName"> 
											<input type="button" id="imgBtn" value="업로드" class="btn btn-default">
											<script type="text/javascript">
												$('#imgBtn').click(function() {
													window.open("/holosolo/views/profilePopup.jsp?kind=register", "",
																"resizable=true, toolbar=no, width=380, height=300, top=100, left=400");
												});
											</script>
										</div>
									</div>

									<div class="modal-footer" style="margin-top: 0px;">
										<div class="button_div" style="margin-top: -5%;"><!-- style="margin-left: 7%" -->
										
										 <div class="col-xs-6 col-sm-3" style="width: 20%; margin-left: 58%">
										    <input type="reset" class="btn btn-default" id="reset" value="작성취소">
										</div>										 
										 <div class="col-xs-6 col-sm-3" style="width: 20%; margin-left: -2%;">
										 	<input id="registerBtn" type="button" value="회원등록"
												class="btn btn-default" />
										</div>										
											
										</div> 
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>


				<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
				<!-- login -->
				<div class="modal fade" id="myModal2" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog" style="width: 450px">
						<!--  class="modal-dialog" -->
						<div class="modal-content" style="width: 450px">
							<div class="modal-header" style="border-bottom: solid 2px #0B1B50;">
								<button type="button" class="close" data-dismiss="modal"
									aria-label="Close" id="loginClose">
									<span aria-hidden="true">&times;</span>
								</button>
								<h4 class="modal-title" id="myModalLabel">로그인</h4>
							</div>
							<!-- 12월 03일 변경된 부분 -->
							<form name="loginFrm" class="form-horizontal" role="form">
								<div class="modal-body" style="height: 115px;">
									<div class="col-sm-9">
										<div class="form-group">
											<label class="control-label col-sm-4" for="username">아이디
											</label>
											<div class="col-sm-8">
												<input name="id" type="text" id="loginId"
													required="required" class="form-control"
													style="width: 250px" />
											</div>
										</div>

										<div class="form-group">
											<label for="password" class="control-label col-sm-4">비밀번호</label>
											<div class="col-sm-8">
												<input name="password" type="password" id="loginPassword"
													required="required" class="form-control"
													style="width: 250px" />
											</div>
										</div>
									</div>
								</div>
							</form>
							<div>
								<!-- 12월 4일 금요일 추가된 부분 :: 비밀번호 찾기 -->
								<div align="right" style="padding-bottom: 20px; padding-right: 64px;">
									<input class="btn btn-default" type="button" id="checkFormBtn" value="로그인">
									<input class="btn btn-default" type="button" id="findPassBtn" value="비밀번호 찾기">
								</div>
								<!-- 여기까지 -->
							</div>
						</div>
					</div>
				</div>
				<!-- ///////////////////////////////////////////////////////////////////////////////////////// -->
				<!-- profile -->
				<div class="modal fade" role="dialog" id="myModal3" tabindex="-1"
					aria-labelledby="myModalLabel" aria-hidden="true" >
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="gridSystemModalLabel" >마이페이지</h4>
						</div>
						<div class="modal-body"
							style="background-image: url('/holosolo/img/myPageBorder.png'); background-size:100% 100%;">
							<div class="container-fluid" style="margin-left: 16px;padding: 10px 0px 10px 0px;">
								<!-- 내정보 -->
								<div class="col-md-2" style="width: 20%; height: 400px; border-right:1px dotted lightgrey;">
									<div class="row" align="center">
										<legend style="color: rgba(47, 112, 168, 1); width: 90%; margin-top: 10px; border-color: black; margin-left: 13%">
											나의 정보</legend>
										<!-- 추가 : 12.01 -->
										<div id="profileImgView">
											<img style="margin-top: 18px;" width="85%" height="80%"
												alt=""
												src="/holosolo/resources/uploadImg/memberImg/${ memberDTO.myPhoto }">
										</div>
										<input class="btn btn-default" type="button" id="photoBtn"
											value="사진 변경" style="margin-top: 7px; margin-bottom: 5px"><br>
										<script type="text/javascript">
											$('#photoBtn').click(function() {
												window.open("/holosolo/views/profilePopup.jsp?kind=profile","",
														"resizable=true, toolbar=no, width=380, height=300, top=100, left=400");
											});
										</script>
										<!-- 여기까지  12.01 -->
										<span id="myInfo"> 이 름 : ${ memberDTO.name }<br>
											아이디 : ${ memberDTO.id }<br> 이메일 : ${ memberDTO.email }<br>
										</span>
									</div>
									<div class="row" align="center" style="margin-top: 6px">
										<input type="button" class="btn btn-default" id="updateBtn" value="정보 수정">&nbsp;&nbsp;&nbsp; 
										<input type="button" class="btn btn-default" id="leaveBtn" value="회원 탈퇴">
									</div>
								</div>

								<!-- 내가 작성한 게시글 -->
								<div class="col-md-5" style="width: 40%; height: 400px; border-right:1px dotted lightgrey"">
									<div class="row" style="height: 200px" align="center">
										<legend style="color: rgba(47, 112, 168, 1); width: 90%; margin-top: 10px; border-color: black">
											활동 내역</legend>
										<table id="myTable" width="90%" style="border: 0px; text-align: center; margin-bottom: 10px">
											<tr>
												<th style="width: 28%;">제 목</th>
												<th style="width: 10%;">작성일자</th>
												<th style="width: 15%;">조회수</th>
											</tr>
											<c:forEach var="sdto"
												items="${requestScope.shareTipListDTO.shareTipList}"
												begin="0" end="2">
												<c:if test="${ memberDTO.id == sdto.memberDTO.id }">
													<tr align="center" id="trHover">
														<td><a
															href="javascript:verifyLogin('${sdto.sno}', 'detail', 'share')">${sdto.title}</a></td>
														<td>${sdto.postedTime}</td>
														<td>${sdto.hits}</td>
													</tr>
												</c:if>
											</c:forEach>
										</table>
										<a href="/holosolo/share.do?command=myActivityShare&&id=${ memberDTO.id }">더 보기</a>
									</div>

									<!-- 내가 추천한 sharetip -->
									<div class="row" style="height: 200px" align="center">
										<legend style="color:rgba(47, 112, 168, 1); width: 90%; 
											margin-top: 10px; border-color: black">
											추천목록 <img alt="" src="/holosolo/img/sharetip.png" width="30px"></legend>
										<table id="myTable" width="90%" style="border: 0px; text-align: center;margin-bottom: 10px">
											<tr>
												<th style="width: 40%">제 목</td>
												<th style="width: 20%;">작성자</td>
												<th style="width: 15%;">작성일자</td>
											</tr>
											<c:forEach var="sdto"
												items="${ requestScope.shareCommentListDTO.shareTipList }" begin="0"
												end="2">
												<tr align="center" id="trHover">
													<td><a
														href="javascript:verifyLogin('${sdto.sno}', 'detail', 'share')">${sdto.title}</a></td>
													<td>${sdto.memberDTO.id}</td>
													<td>${sdto.postedTime}</td>
												</tr>
											</c:forEach>
										</table>
										<a href="/holosolo/share.do?command=getMyRecommendedSList">더 보기</a>
									</div>
								</div>

								<!-- 내가 활동한 모임 -->
								<div class="col-md-5" style="width: 40%; height: 400px">
									<div class="row" style="height: 200px" align="center">
										<legend style="color: rgba(47, 112, 168, 1); width: 90%; margin-top: 10px; border-color: black">
												참여한 모임</legend>
										<table id="myTable" width="90%" style="border: 0px; text-align: center; margin-bottom: 10px">
											<tr>
												<th>제 목</th>
												<th style="width: 23%;">모임 날짜</th>
												<th style="width: 16%;">현재 인원</th>
												<th style="width: 16%;">최대 인원</th>
												<th style="width: 16%">진행 현황</th>
											</tr>
											<c:forEach var="gdto" items="${requestScope.groupListDTO.groupList}"
												begin="0" end="2">
												<tr align="center" id="trHover">
													<td><a href="javascript:verifyLogin('${gdto.gno}', 'detail', 'group')">${gdto.title}</a></td>
													<td>${gdto.promiseDate}</td>
													<td>${gdto.currentNum}</td>
													<td>${gdto.maxNum}</td>
													<td><c:if test="${ gdto.status == 0 }">
															<font color="blue">진행중</font>
														</c:if> <c:if test="${ gdto.status == 1 }">
															<font color="orange">종료</font>
														</c:if> <c:if test="${ gdto.status == -1 }">
															<font color="red">취소</font>
														</c:if></td>
												</tr>
											</c:forEach>
										</table>
										<a href="/holosolo/group.do?command=myJoinedGroup&&id=${ memberDTO.id }">더
											보기</a>
									</div>

									<!-- 내가 추천한 contents -->
									<div class="row" style="height: 200px"
										align="center">
										<legend style="color: rgba(47, 112, 168, 1); width: 90%; 
												margin-top: 10px; border-color: black">
												추천목록 <img alt="" src="/holosolo/img/content.png" width="30px"></legend>
										<table id="myTable" width="90%" style="border: 0px; text-align: center; margin-bottom: 10px">
											<tr>
												<th style="width: 27%">제 목</td>
												<th style="width: 10%;">작성자</td>
												<th style="width: 10%;">작성일자</td>
											</tr>
											<c:forEach var="cdto" items="${ requestScope.contentCommentListDTO.contentList }" begin="0" end="2">
												<tr align="center" id="trHover">
													<td><a href="javascript:verifyLogin('${cdto.cno}', 'detail', 'share')">${cdto.title}</a></td>
													<td>${cdto.memberDTO.id}</td>
													<td>${cdto.postedTime}</td>
												</tr>
											</c:forEach>
										</table>
										<a href="/holosolo/content.do?command=getMyRecommendedCList">더 보기</a>
									</div>
								</div>
							</div>
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Close</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal -->
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/ie10-viewport-bug-workaround.js"></script>
</body>
</html>
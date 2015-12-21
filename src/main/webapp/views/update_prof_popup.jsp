<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function(){
		$('#updateForm').hide();
		$('#checkPass').focus();
		
		$('#checkPassBtn').click(function(){
			var checkPass = $('#checkPass').val();
			
			$.ajax({
				type:"post",
				url:"/holosolo/member.do",
				data:"command=checkPassword&&checkPass="+checkPass,
				dataType:"json",
				
				success:function(jsonData){
					if(jsonData.matchingResult == false){
						alert("비밀번호가 맞지 않습니다. 마이페이지로 돌아갑니다.");
						self.close();
					}else{
						$('#updateForm').show();
						$('#checkPassword').hide();
					}
				}
			})
		})
		
		$('#subBtn').click(function(){
			if($('#password').val() != $('#password_m').val()){
				alert("비밀번호가 맞지 않습니다.");
				$('#password').val("");
				$('#password_m').val("");
				$('#password').focus();
				
				return false;
			}else{
				$.ajax({
					type:"post",
					url:"/holosolo/member.do",
					data:"command=memberUpdate&&id="+$('#id').val()+
						 "&&password="+$('#password').val()+
						 "&&email="+$('#email').val(),
					dataType:"json",
					
					success:function(data){
						var info = "이  름 : "+data.memberDTO.name+"<br>";
							info += "아이디 : "+data.memberDTO.id+"<br>";
							info += "이메일 : "+data.memberDTO.email+"<br>";
							
						$('#myInfo', opener.document).html(info);
						self.close();
					}
				})
			}
		})
		
		$('#cancelBtn').click(function(){
			self.close();
		});
	})
</script>

<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
	tr{
		font-family: 'Jeju Gothic';	
	}
	
	#updateTable th{
	 	text-align: right;
	 	height: 20px;
	}
	
	.form-control {
		width: 95%;
		height: 20px;
		padding: 6px 12px;
		font-size: 14px;
		line-height: 1.42857143;
		color: #555;
		background-color: #fff;
		background-image: none;
		border: 1px solid #ccc;
		border-radius: 4px;
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
		-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
			ease-in-out .15s;
		-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
			.15s;
		transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	}
	
	.form-control:focus{
		border-color: #66afe9;
		outline: 0;
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
			rgba(102, 175, 233, .6);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
			rgba(102, 175, 233, .6);
	}

	.form-control2 {
		width: 70%;
		height: 20px;
		padding: 6px 12px;
		font-size: 14px;
		line-height: 1.42857143;
		color: #555;
		background-color: #fff;
		background-image: none;
		border: 1px solid #ccc;
		border-radius: 4px;
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
		-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
			ease-in-out .15s;
		-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
			.15s;
		transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	}
	
	.form-control2:focus{
		border-color: #66afe9;
		outline: 0;
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
			rgba(102, 175, 233, .6);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
			rgba(102, 175, 233, .6);
	}
</style>
</head>
<body>
	<div id="checkPassword" style="vertical-align: middle;">
		<img alt="" src="/holosolo/img/footer.png"  width="40%" style="margin-left: 30%; margin-top: 10%"><br><br>
		<legend style="border-color: gray; width: 97%; color: darkblue; font-weight: bold; margin-left: 10px;">비밀번호 확인</legend>
		<hr>
		<input class="form-control2" type="password" id="checkPass" name="checkPass" style="height: 33px; margin-left: 20px;" placeholder="비밀번호를 입력하세요">
		<input class="btn btn-default" type="button" id="checkPassBtn" value="확인" style="width: 58.66px; height: 34.33px;margin-bottom: 2px ">
	</div>
	<div id="updateForm" align="center" style="margin-top: 15px">
		<legend style="color:darkblue;font-weight: bold; width: 97%; border-color:gray; margin-left: 30px;">개인정보변경</legend>
		<hr>
		<form id="frm" method="post" action="/holosolo/member.do" class="form">
			<input type="hidden" name="command" value="memberUpdate">
			<table id="updateTable">
				<tr>
					<th>아이디</th>
					<td>
						<input name="id" type="text" id="id" readonly="readonly"
							value="${sessionScope.memberDTO.id}" class="form-control" style="margin-bottom: 5px; margin-left: 15px; width: 180px; height: 30px; margin-right: 20px;" />
					</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>
						<input name="name" type="text" id="first_name" readonly="readonly"
							 value="${sessionScope.memberDTO.name}" class="form-control" style="margin-bottom: 5px; margin-left: 15px; width: 180px; height: 30px; margin-right: 20px;" />
					</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>
					<div style="margin-left: 20px;">
						<c:choose>
						<c:when test="${sessionScope.memberDTO.sex=='M'}">			
				  			 남&nbsp;<input type="radio" name="sex" id="sex" value="M"
								checked="checked" disabled="disabled">&nbsp;&nbsp;&nbsp;  
					   		 여&nbsp;<input type="radio" name="sex" id="sex" value="W" disabled="disabled">
						</c:when>
						<c:otherwise>			   			  
				          	남&nbsp;<input type="radio" name="sex" id="sex" value="M" disabled="disabled">&nbsp;&nbsp;&nbsp; 
					   		여&nbsp;<input type="radio" name="sex" id="sex" value="W" checked="checked" disabled="disabled">
						</c:otherwise>
					</c:choose>
					</div>
					</td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td>
						<input style="margin-bottom: 5px; width: 180px; height: 30px; margin-right: 20px; margin-left: 15px;" name="email" type="text" id="email"  value="${memberDTO.email}" class="form-control" />
						</td>
				</tr>
				<tr>
					<th><font class="req">*</font>비밀번호</th>
					<td>
						<input name="password" type="password" id="password"
							required="required" class="form-control"  style="margin-bottom: 5px; margin-left: 15px; width: 180px; height: 30px; margin-right: 20px;" />
					</td>
				</tr>
				<tr>
					<th>*비밀번호 확인</th>
					<td>
						<input name="confirm_password" type="password" id="password_m"
							required="required" class="form-control"  style="margin-bottom: 5px; margin-left: 15px; width: 180px; height: 30px; margin-right: 20px;" /> <span
							id="passCheckView"></span>
					</td>
				</tr>
			</table>
			<hr>
			<div class="modal-footer" style="padding-top: 0px;">
				<div class="button_div">
					<input class="btn btn-default" type="submit" id="subBtn" value="수정">
					<input class="btn btn-default" type="button" id="cancelBtn" value="취소">
				</div>
			</div>
		</form>
	</div>
</body>
</html>
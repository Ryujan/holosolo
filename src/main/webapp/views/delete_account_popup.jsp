<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="/holosolo/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<link rel="stylesheet" href="/holosolo/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/holosolo/css/css.css">
<link rel="stylesheet" href="/holosolo/css/font-awesome.min.css">
<link rel="stylesheet" href="/holosolo/css/style.css">
<link rel='stylesheet' id='box-style-css' href='/holosolo/css/style-box.css' type='text/css' media='all' />
<script type="text/javascript">
	$(function(){
		$('#deleteBtn').click(function(){
			$.ajax({
				type:"post",
				url:"/holosolo/member.do",
				data:"command=memberDelete&&id="+$('#id').val()+"&&password="+$('#password').val(),
				dataType:"json",
				
				success:function(data){
					alert("success!");
					if(data.deleteResult == true){
						alert("그동안 고마웠어요. 이젠 안녕~");
						self.close();
						window.opener.location.href="/holosolo/main.do?command=showMain";
					}else{
						alert("비밀번호 틀렸어요. 다시 입력하세요.");
						return;
					}//else
				}//success
			})//ajax
		})//click
	})//function
</script>
<style type="text/css">
	#updateTable th{
	 	text-align: left
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
	<form action="/holosolo/member.do">
		<img alt="" src="/holosolo/img/sub_logo.png" width="100%" height="200px">
		<legend style="color:darkblue;font-weight: bold; width: 97%; border-color:gray; text-align: center;">
			아이디 및 비밀번호 확인</legend>
		<div align="center">
			<table style="text-align: right;">
				<tr>
					<td>아 이 디 : <input class="form-control2" type="text" id="id" name="id" required="required" style="width: 174.87px; height: 32px;"><p></td>				
				</tr>
				<tr>
					<td>비밀번호 : <input class="form-control2" type="password" id="password" name="password" required="required" style="width: 174.87px; height: 32px;"></td>				
				</tr>
			</table>
			<input class="btn btn-primary" type="submit" id="deleteBtn" value="계정 삭제" style="margin-top: 10px">
		</div>
	</form>
</body>
</html>
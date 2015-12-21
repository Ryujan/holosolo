<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(function(){
		$('#findPassBtn').click(function(){
			$.ajax({
				type:"post",
				url:"/holosolo/member.do",
				data:"command=findPassword&&id="+$('#id').val()+"&&name="+$('#name').val(),
				dataType:"json",
				
				success:function(data){
					if(data.findResult == false){
						alert("현재 아이디가 존재하지 않습니다.");
						return;
					}else{
						alert("회원가입 시, 입력하신 이메일 주소로 비밀번호가 발송되었습니다.");
						self.close();
					}//else
				}//callback
			})//ajax
		})//click
	})//function
</script>

<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);
.form-control1 {
		  width: 50%;
		  height: 34px;
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
		  -webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow ease-in-out .15s;
		       -o-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
          transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
	}
	
	.form-control1:focus{
		border-color: #66afe9;
		outline: 0;
		-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
			rgba(102, 175, 233, .6);
		box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px
			rgba(102, 175, 233, .6);
	}
</style>
</head>
<body style="font-family: 'Jeju Gothic';">
	<div align="center">
		<div align="left" style="font-size: x-large;margin-top: 20px; margin-left: 20px;">비밀번호 찾기</div><p>
		<hr style="border-color: #0B1B50"><p>
		회원가입시 기입한 메일로 전송됩니다. <br>정보를 정확히 입력해주세요.<br><Br>
		아이디 : <input type="text" id="id" name="id" required="required" class="form-control1" style="width: 139px; height: 24px;padding: 0px 12px 0px 12px"><p><p><p>
		이  &nbsp; &nbsp;름 : <input type="text" id="name" name="name" required="required" class="form-control1" style="width: 139px; height: 24px;padding: 0px 12px 0px 12px"><p><p><p>
		<div align="right">
			<input class="btn btn-default" type="button" value="확인" id="findPassBtn" style="margin-right: 20px; background-color: #e0e0e0">
		</div>
	</div>
</body>
</html>
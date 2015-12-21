<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<link rel="stylesheet" href="/holosolo/css/bootstrap.css" type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script type="text/javascript">
$(function(){
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
				}else{
					if(confirm("확인 되었습니다. 취소를 진행하시겠습니까?")){
						location.href="/holosolo/group.do?command=groupCancel&&gno=${param.gno}";
						$(opener.location.reload());
					}
				}
				self.close();
			}
		})
	})

	$('#cancelBtn').click(function(){
		self.close();
	})	
});

</script>
<style type="text/css">
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
		<input class="form-control-address" type="password" id="checkPass" name="checkPass" style="height: 33px; margin-left: 15px;" placeholder="비밀번호를 입력하세요">
		<input class="btn btn-default" type="button" id="checkPassBtn" value="확인" style="width: 58.66px; height: 34.33px; margin-bottom: 2px; ">
		<input class="btn btn-default" type="button" id="cancelBtn" value="취소" style="width: 58.66px; height: 34.33px; margin-bottom: 2px;">
	</div>
</body>
</html>
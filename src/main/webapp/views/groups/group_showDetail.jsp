<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<title>Insert title here</title>
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=dbb695a7da97f08eb78b130b5d07929f&libraries=services,clusterer"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>

<style type="text/css">
	#comment tr:HOVER{
		background-color: bisque;
	}
</style>

<script type="text/javascript">
	$(function(){
 		var memberDTO = "${sessionScope.memberDTO}";
		if(memberDTO == ""){
			location.href="/holosolo/intro.jsp";
		}
	
		$('#commentWrite').click(function(){
			var reply = $('#reply').val();
			if(reply==""){
				alert("내용을 입력하세요");
				reply.focus();
				return;
			}else{
				location.href="/holosolo/group.do?command=groupCommentWrite&&gno=${groupDTO.gno}&&reply="+reply+"&&category=${groupDTO.category}";
			}
		});//click
		
		$('#groupCancel').click(function(){
			var popUrl = "/holosolo/views/groups/groupCancel_popup.jsp?gno=${groupDTO.gno}";
			var popOption = "width=370, height=330, resizable=no, scrollbars=no, status=no; top=200, left=500";
	
			window.open(popUrl, "", popOption);
		});//click
		
		$('#groupJoinBtn').click(function(){
			var sexCheck = "${ requestScope.groupDTO.sexCheck}";
			var sex = "${ sessionScope.memberDTO.sex }";
			if(sexCheck == sex || sexCheck == 'U'){
				if (confirm("${ sessionScope.memberDTO.id }님 참가하시겠습니까?") == true) { //참가
					if("${groupDTO.currentNum == groupDTO.maxNum}" == "true"){
		                  alert("인원이 다 찼습니다. 다음에 이용해주세요.");
	               }else{
						location.href = "/holosolo/group.do?command=groupJoin&&id=${sessionScope.memberDTO.id}&&gno=${groupDTO.gno}";
	               }
				}
			}else{
				alert("모임 조건과 일치하지 앖습니다.. 성별을 확인해주세요");
			}
		});//click
						
		$('#groupLeaveBtn').click(function(){
			if (confirm("${ sessionScope.memberDTO.id }님 취소하시겠습니까?") == true) {
				location.href = "/holosolo/group.do?command=groupLeave&&gno=${groupDTO.gno}";
			}
		});//click
	});//function	
		
	function commentDelete(rno,id,gno){
		location.href = "/holosolo/group.do?command=groupCommentDelete&&rno="+rno+"&&id="+id+"&&gno="+gno;
	}

</script>
</head>
<body style="background-color: rgba(177, 176, 176, 0.06);">
	<c:import url="../header.jsp"></c:import>
	<div align="center" style="margin-top: 8%;">
		<div class="container">
			<div class="col-md-8">
			<c:if test="${groupDTO.category == 'Cooking'}">
				<c:set var="category" value="요리/음식"/>
			</c:if>
			<c:if test="${groupDTO.category == 'Culture'}">
				<c:set var="category" value="문화"/>
			</c:if>
			<c:if test="${groupDTO.category == 'Learning'}">
				<c:set var="category" value="지식배움"/>
			</c:if>
			<c:if test="${groupDTO.category == 'Talk'}">
				<c:set var="category" value="일상대화"/>
			</c:if>
			<c:if test="${groupDTO.category == 'Activity'}">
				<c:set var="category" value="활동"/>
			</c:if>
			<legend style="border-color: gray; width: 600px; color: black; margin-top: 10px; margin-right: 3%; margin-left: 3%;">
				<b>[${category}]</b>  ${ groupDTO.title }</legend>
				<table align="center" style="margin-left: 10px; margin-right: 10px;">
					<tr>
						<table width="650px"
							style="background-color: rgba(177, 176, 176, 0.12); border-radius: 33px;">
							<tr>
								<td style="padding: 20px 30px 0px;">
									<p>
									<div style="border: 0px; background-color: white; margin:10px; padding: 2%">${ groupDTO.content }</div>
								</td>
							</tr>
							
							<tr>
								<td valign="middle" style="padding: 0px 30px 30px 40px;">
									<div id="btn">
										<a href="/holosolo/group.do?command=groupList&&category=seeAll" 
											class="btn btn-sm btn-default" style="margin-top:1.7%;">목록</a>
										<c:if test="${ requestScope.groupDTO.status == 0 }">
											<c:if test="${requestScope.groupDTO.memberDTO.id == sessionScope.memberDTO.id}">
												<a href="/holosolo/group.do?command=updateView&&gno=${groupDTO.gno}"
													class="btn btn-sm btn-default" style="margin-top:1.7%;">수정</a>	
											</c:if>
										</c:if>
									</div>
								</td>
							</tr>
						</table> <br> <!-- 댓글쓰기 -->
						<form name="commentWrite" style="width: 630px">
							<h3 align="left" style="padding-left: 8px;">댓글</h3>
							<hr>
							<textarea id="reply" name="reply" rows="3" cols="50"
								style="width: 550px; height: 50px; margin-right: 10px; margin-bottom: 15px; 
								margin-left: 10px; background-color: rgba(177, 176, 176, 0.06);"></textarea>
							<input type="button" id="commentWrite" class="btn btn-default" value="작성" style="margin-bottom: 10px;"><br><br><br>
						</form>

						<table width="630px" border="0px;" style="margin-bottom: 50px;">
							<tr align="center" style="border-bottom: 2px solid #0B1B50;">
								<th style="width: 130px;">
									<h4 align="center"><b>아이디</b></h4>
								</th>
								<th>
									<h4 align="center"><b>댓글</b></h4>
								</th>
								<th style="width: 175px;">
									<h4 align="center"><b>작성시간</b></h4>
								</th>
								<th style="width: 70px;"></th>
							</tr>
							<tbody id="comment">
								<c:forEach var="comment" items="${requestScope.commentListByPageDTO.commentList}">
									<tr height="30">
										<td align="center">${comment.memberDTO.id}</td>
										<td align="left">${comment.reply}</td>
										<td align="center">${comment.postedTime}</td>
										<td>
											<c:if test="${sessionScope.memberDTO.id == comment.memberDTO.id 
																|| sessionScope.memberDTO.authority == true }">
												<a href="javascript:commentDelete('${comment.rno}',
																'${comment.memberDTO.id}','${comment.gno}')">
													<img alt="삭제" src="/holosolo/img/x.png" border="0" style="width: 20%;"></a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<!-- 댓글의 페이징이 시작된다. -->
						<c:if test="${requestScope.commentListByPageDTO.pagingBean.previousPageGroup}">
							<a href="/holosolo/group.do?command=groupShowDetail&&gno=${ requestScope.groupDTO.gno }&&replyPageNo=${requestScope.commentListByPageDTO.pagingBean.startPageOfPageGroup-1}">
							<img src="/holosolo/img/left_arrow_btn.gif"></a>
						</c:if> &nbsp;&nbsp;
						
						<c:forEach var="i"
							begin="${requestScope.commentListByPageDTO.pagingBean.startPageOfPageGroup}"
							end="${requestScope.commentListByPageDTO.pagingBean.endPageOfPageGroup}">
							<c:choose>
								<c:when test="${requestScope.commentListByPageDTO.pagingBean.nowPage!=i}">
									<a href="/holosolo/group.do?command=groupShowDetail&&gno=${ requestScope.groupDTO.gno }&&replyPageNo=${i}">${i}</a>
								</c:when>
								<c:otherwise>
									${i}
								</c:otherwise>
							</c:choose>
						</c:forEach>
						&nbsp;&nbsp;
			
						<c:if test="${requestScope.commentListByPageDTO.pagingBean.nextPageGroup}">
							<a href="/holosolo/group.do?command=groupShowDetail&&gno=${ requestScope.groupDTO.gno }&&replyPageNo=${requestScope.commentListByPageDTO.pagingBean.endPageOfPageGroup+1}">
								<img src="/holosolo/img/right_arrow_btn.gif">
							</a>
						</c:if>
						<!-- 여기까지가 내용, 댓글, 그리고 댓글의 페이지가 진행된 부분이다. -->
					</tr>
				</table>
			</div>
			
			<!-- 수정 -->
			<div class="col-md-4" style="border-radius: 33px; width: 326.66px; padding-right: 6px; padding-bottom: 30px; padding-left: 6px; margin-top: 60px; margin-right: 10px; margin-left: 0px; background-color: rgba(177, 176, 176, 0.12);">
			<p>
			<h4 align="center" style="margin-top: 20px; margin-bottom: 20px;">[ 주최자 정보 ]</h4>
				<table align="center" style="width: 300px; margin-top: 30px;">
					<tr align="left">
						<td rowspan="3" align="center" style="width: 130px;">
							<img src="/holosolo/resources/uploadImg/memberImg/${writerInfo.myPhoto}"
								class="attachment-portfolio_fixed wp-post-image"
								style="border-radius: 65px;" width="100" height="100" /><p>
						</td>
						<td align="left" style="width : 50px;">ID</td>
						<td style="width: 80px">${writerInfo.id}</td>
					</tr>
					<tr>
						<td>이름</td>
						<td>${writerInfo.name}</td>
					</tr>
					<tr>
						<td>성별</td>
						<td>
							<c:if test="${writerInfo.sex == 'W'}">
								<c:set var="sex" value="여성" />
							</c:if> <c:if test="${writerInfo.sex == 'M'}">
								<c:set var="sex" value="남성" />
							</c:if> ${sex}
						</td>
					</tr>
				</table>
				<hr style="color: white; border:solid 2px; width: 250px;">	
				<h4 align="center" style="margin-top: 20px; margin-bottom: 20px;">[ 참여자 정보 ]</h4>
				<p><p>
				<table border="0px" align="center" style="width: 200px; margin-top: 20px;">
					<thead>
						<c:forEach var="entry" items="${entry}">
							<tr align="center" style=" height: 30px;">
								<td><b>${entry}</b> 님</td>
							</tr>
						</c:forEach>
					</thead>				
					<tbody>
						<tr>
							<td align="center" style="height: 40px;">현재참여 인원수  ${groupDTO.currentNum }명 </td>
						</tr>
					</tbody>
				</table>
				<!-- 추가 -->
				<hr style="color: white; border:solid 2px; width: 250px;">		
				<table>
					<tr>
						<td align="center">
							<c:if test="${ requestScope.groupDTO.status == 0 }">
								<c:choose>
									<c:when test="${requestScope.groupDTO.memberDTO.id == sessionScope.memberDTO.id 
														|| sessionScope.memberDTO.authority == true}">
										<a href="#" class="btn btn-default" id="groupCancel">
											<span><img src="/holosolo/img/group_cancel.png"></span>모임취소</a>
									</c:when>
									<c:otherwise>
										<c:if test="${requestScope.result == false }">
											<a href="#" class="btn btn-default" id="groupJoinBtn">
												<span><img src="/holosolo/img/group_join.png"></span>참가신청</a>
										</c:if>
										<c:if test="${requestScope.result == true }">
											<a href="#" class="btn btn-default" id="groupLeaveBtn">
												<span><img src="/holosolo/img/groupjoin_cancel.png"></span>참가취소</a>
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${ requestScope.groupDTO.status == 1 }">
								<font color="blue">모임이 완료되었습니다.</font>
							</c:if>
							<c:if test="${ requestScope.groupDTO.status == -1 }">
								<font color="red">모임이 취소되었습니다.</font>
							</c:if>
							
							<Br><br>	
							<font color="black">일 시 : ${ groupDTO.promiseDate }<br><br></font>
							<font color="red">★  모임 마감일은 모임 날과 같습니다.<br></font>
						</td>
					</tr>
				</table>
				<hr style="color: white; border:solid 2px; width: 250px;"><p>
				<h4>[ 장소 정보 ]</h4>
				<table>
					<tr>
						<td>${groupDTO.promiseAddr}</td>
					</tr>
				</table>
				<table>
					<tr>
						<td style="padding: 0px 30px 0px 30px; width: 500px; height: 220px;"><c:if
								test="${groupDTO.latitude != null && groupDTO.longitude != null}">
							<div id="map" style="width: 250px; height: 200px; position: relative; overflow: hidden;"></div>
								<script type="text/javascript">
									var latitude = '${groupDTO.latitude}';
									var longitude = '${groupDTO.longitude}';
									var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
									mapOption = {
										center : new daum.maps.LatLng(latitude, longitude), // 지도의 중심좌표
										level : 1
									// 지도의 확대 레벨
									};

									////
									var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

									// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
									var mapTypeControl = new daum.maps.MapTypeControl();

									// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
									// daum.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
									map.addControl(mapTypeControl, daum.maps.ControlPosition.TOPRIGHT);

									// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
									var zoomControl = new daum.maps.ZoomControl();
									map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
									////
									var marker = new daum.maps.Marker(
									{
										// 지도 중심좌표에 마커를 생성합니다 
										position : map.getCenter()
									});

									marker.setMap(map);

									/* 추가 12.03 */
									var iwContent = '<div style="width:200px">${groupDTO.promiseAddr}<br><a href="http://map.daum.net/link/to/${groupDTO.promiseAddr},${groupDTO.latitude},${groupDTO.longitude}" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
									iwPosition = new daum.maps.LatLng(latitude, longitude); //인포윈도우 표시 위치입니다

									// 인포윈도우를 생성합니다
									var infowindow = new daum.maps.InfoWindow(
									{
										position : iwPosition,
										content : iwContent
									});

									// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
									infowindow.open(map, marker);
								</script>
							</c:if>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>
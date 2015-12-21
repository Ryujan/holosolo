<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title> 
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="/holosolo/css/bootstrap.min.css">
<link rel="stylesheet" href="/holosolo/css/bootstrap.css" type="text/css">
<script type="text/javascript"
	src="//apis.daum.net/maps/maps3.js?apikey=dbb695a7da97f08eb78b130b5d07929f&libraries=services,clusterer"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript" src="/holosolo/se/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
   /* 제목과 Content값을 null상태로 넘기면 DB문제 생김. 그래서 필요한 Script. */
   function group_submit(){
      oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
   }
</script>
<script>
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : "yy-mm-dd",
			dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
			monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
					'8월', '9월', '10월', '11월', '12월' ],
			showMonthAfterYear : true,
			minDate : '+7',
			maxDate : '+16'
		});//datepicker

		$('#submitBtn').click(function() {
			alert($('#maxNum').val());
			if ($('#datepicker').val() == "") {
				alert("날짜를 선택하세요");
				return false;
			}
			if (mapClick == true && selCheck == false) {
				alert("지도 확인버튼 클릭하세요");
				return false;
			}
		});//click
	});//function
</script>
</head>
<body>
<c:import url="../header.jsp"></c:import>
	<div align="center" style="margin-top: 8%;">
		<form action="/holosolo/group.do" method="post" name="write_form" enctype="multipart/form-data">
		<!-- command로 표기될 메서드 명은 조장하고 상의 후, 정하도록. value 부분. -->
			<input type="hidden" name="command" value="groupUpdate">
			<input type="hidden" name="gno" value="${requestScope.groupDTO.gno }">
			<input type="hidden" name="currentNum" value="${ requestScope.groupDTO.currentNum }">
			<input id="categoryPath" type="hidden" name="categoryPath" value="groupImg">
			<table style="width: 50%;  margin: 0px 0px 1% 2%" >
				<tr>
					<td style="padding-bottom: 10px">
						<select name="category" id="category" required="required" class="form-control-category">
							<option value="">카테고리</option>
							<option value="Cooking">요리/음식</option>
							<option value="Culture">문화</option>
							<option value="Learning">지식배움</option>
							<option value="Talk">일상대화</option>
							<option value="Activity">활동</option>
						</select>
						<select name="sexCheck" id="sexCheck" required="required" class="form-control-category">
							<option value="">성별선택</option>
							<option value="M">남</option>
							<option value="W">여</option>
							<option value="U">무관</option>
						</select>
						<select name="maxNum" id="maxNum" required="required" class="form-control-category">
							<option value="">최대 인원수</option>
							<c:choose>
								<c:when test="${requestScope.groupDTO.currentNum==1}">
									<c:forEach var="num" begin="${requestScope.groupDTO.currentNum+1}" end="8">
										<option value=${num}>${num}</option>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach var="num" begin="${requestScope.groupDTO.currentNum}" end="8">
										<option value=${num}>${num}</option>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</select>
						<input type="text" name="날짜" value="${ requestScope.groupDTO.promiseDate }" width="150px" 
								class="form-control-text" readonly="readonly" 
								style="cursor: not-allowed; background-color: #eee; opacity:1">
						<input type="text" name="현재인원수" value="현재인원 : ${ requestScope.groupDTO.currentNum } 명" width="10%"
								class="form-control-text" readonly="readonly" 
								style="cursor: not-allowed; background-color: #eee; opacity:1">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="text" id="title" maxlength="20" name="title"
							maxlength="50" size="90" required="required" width="450px" class="form-control-title" value="${ groupDTO.title }">
					</td>
				</tr>
			</table>
			<textarea align="center" name="content" id="content" rows="10" cols="100" style=" height: 412px; width: 100%">
				${ requestScope.groupDTO.content}
			</textarea>
			<script type="text/javascript">
				var oEditors = [];
				         
				nhn.husky.EZCreator.createInIFrame({
					oAppRef: oEditors,
					elPlaceHolder: "content",
					sSkinURI: "/holosolo/se/SmartEditor2Skin.html",   
					
					htParams : {
						bUseToolbar : true,            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseVerticalResizer : true,      // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
						bUseModeChaner : true,         // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
						//aAdditionalFontList : aAdditionalFontSet,      // 추가 글꼴 목록
						   
						fOnBeforeUnload : function(){
						//alert("완료!");
						}
					}, //boolean
					fOnAppLoad : function(){
					//oEditors.getById["ir1"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
					},
					fCreator: "createSEditor2"
				});
			</script>
	      
	      	
			<!-- Map 지도!!!! -->
			<div id="map" style="width:48%; height:300px; position:relative; overflow:hidden;"></div>
			<div class="hAddr">
			    <span class="title"><b>지도중심기준 행정동 주소정보 - </b></span>
			    <span id="centerAddr"></span>
			</div>
			<div id="clickLatlng"></div>
			<p id="infoDiv"></p>
			<table style=" border-image: none; width: 647.82px;">
				<td>
					<input type="text" id="searchAddr" class="form-control-category" style="margin-left: 6px;">
					<input type="button" id="Btn" value="검색" class="btn writebtn">
					&nbsp;
				<!-- 전송 hidden으로 위도 경도 전송-->
					<input type="hidden" id="lat" name="latitude" value="${groupDTO.latitude}">
					<input type="hidden" id="lon" name="longitude" value="${groupDTO.longitude}">
					<input type="text" id="addr" name="promiseAddr" size="40" value="${groupDTO.promiseAddr }" class="form-control-address">
					<input type="button" id="selectBtn" value="장소결정" class="btn writebtn"><br>
				</td>
			</table>
			<!-- map Script -->
			<script>
				var temp_lat, temp_long;
				var selCheck = false;
				var mapClick = false;

			    $('input:text').keydown(function(event){
					if(event.keyCode == 13)
						return false;
			    });
				
				$('#selectBtn').click(function(){
					selCheck = true;
					var addr = $('#addr').val();
					if(confirm(addr + " 로 선택하시겠습니까?")){
						$('#lat').val(temp_lat);
						$('#lon').val(temp_long);
					}			
				});
				
				var latitude = "${groupDTO.latitude}";
				var longitude = "${groupDTO.longitude}";

				if (latitude == "" || longitude == "") {
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new daum.maps.LatLng(37.4020259, 127.1036169), // 지도의 중심좌표
						level : 4
					// 지도의 확대 레벨
					};
				} else {
					var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
					mapOption = {
						center : new daum.maps.LatLng(latitude, longitude), // 지도의 중심좌표
						level : 4
					// 지도의 확대 레벨
					};
				}
				
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

				var marker = new daum.maps.Marker({
					// 지도 중심좌표에 마커를 생성합니다 
					position : map.getCenter()
				});
				// 지도에 마커를 표시합니다
				marker.setMap(map);

				// 지도에 클릭 이벤트를 등록합니다
				// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
				daum.maps.event.addListener(map, 'click', function(mouseEvent) {
					mapClick = true;
					// 클릭한 위도, 경도 정보를 가져옵니다 
					var latlng = mouseEvent.latLng;
					// 마커 위치를 클릭한 위치로 옮깁니다
					marker.setPosition(latlng);

					temp_lat = latlng.getLat();
					temp_long = latlng.getLng();
				});

				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new daum.maps.services.Geocoder();

				var marker = new daum.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
				infowindow = new daum.maps.InfoWindow({
					zindex : 1
				}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

				// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
				searchAddrFromCoords(map.getCenter(), displayCenterInfo);

				// 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
				daum.maps.event.addListener(map,'click',function(mouseEvent){
					searchDetailAddrFromCoords(mouseEvent.latLng, function(status, result){
						if (status === daum.maps.services.Status.OK) {
							var detailAddr = !!result[0].roadAddress.name ? '<div>도로명주소 : '
									+ result[0].roadAddress.name + '</div>' : '';
							detailAddr += '<div>지번 주소 : ' + result[0].jibunAddress.name + '</div>';
	
							var content = '<div class="bAddr">' + '<span class="title">법정동 주소정보</span>'
									+ detailAddr + '</div>';
							// 마커를 클릭한 위치에 표시합니다 
							$('#addr').val(result[0].jibunAddress.name);
							marker.setPosition(mouseEvent.latLng);
							marker.setMap(map);
	
							// 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
							infowindow.setContent(content);
							infowindow.open(map, marker);
						}
					});
				});

				// 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
				daum.maps.event.addListener(map, 'idle', function() {
					searchAddrFromCoords(map.getCenter(), displayCenterInfo);
				});

				function searchAddrFromCoords(coords, callback) {
					// 좌표로 행정동 주소 정보를 요청합니다
					geocoder.coord2addr(coords, callback);
				}

				function searchDetailAddrFromCoords(coords, callback) {
					// 좌표로 법정동 상세 주소 정보를 요청합니다
					geocoder.coord2detailaddr(coords, callback);
				}

				// 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
				function displayCenterInfo(status, result) {
					if (status === daum.maps.services.Status.OK) {
						var infoDiv = document.getElementById('centerAddr');
						infoDiv.innerHTML = result[0].fullName;
					}
				}

				//////////////////////////////// 장소 검색 목록 //////////////////////////
				// 장소 검색 객체를 생성합니다
				var addr;

				$('#Btn').click(function() {
					addr = $('#searchAddr').val();
					var ps = new daum.maps.services.Places();

					// 키워드로 장소를 검색합니다
					ps.keywordSearch(addr, placesSearchCB);

					// 키워드 검색 완료 시 호출되는 콜백함수 입니다
					function placesSearchCB(status, data, pagination) {
						if (status === daum.maps.services.Status.OK) {
							// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
							// LatLngBounds 객체에 좌표를 추가합니다
							var bounds = new daum.maps.LatLngBounds();

							for (var i = 0; i < data.places.length; i++) {
								displayMarker(data.places[i]);
								bounds.extend(new daum.maps.LatLng(
										data.places[i].latitude, data.places[i].longitude));
							}
							// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
							map.setBounds(bounds);
						}
					}

					// 지도에 마커를 표시하는 함수입니다
					function displayMarker(place) {

						// 마커를 생성하고 지도에 표시합니다
						var marker = new daum.maps.Marker({
							map : map,
							position : new daum.maps.LatLng(place.latitude, place.longitude)
						});

						// 마커에 클릭이벤트를 등록합니다
						daum.maps.event.addListener(marker,'click', function() {
							mapClick = true;
							// 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
							temp_lat = place.latitude;
							temp_long = place.longitude;
							$('#addr').val(place.title);
							infowindow.setContent('<div style="padding:5px;font-size:12px;">'+ place.title+ '</div>');
							infowindow.open(map,marker);
						});
					}
				});
			</script><p><p>
			<input type="submit" value="수정" class="btn writebtn"  id="submitBtn" onclick="group_submit()">
		</form>
	</div>
	<c:import url="../footer.jsp"></c:import>
</body>
</html>
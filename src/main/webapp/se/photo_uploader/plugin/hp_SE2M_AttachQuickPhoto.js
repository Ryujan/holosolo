/**
 * @use 간단 포토 업로드용으로 제작되었습니다.
 * @author cielo
 * @See nhn.husky.SE2M_Configuration 
 * @ 팝업 마크업은 SimplePhotoUpload.html과 SimplePhotoUpload_html5.html이 있습니다. 
 */

nhn.husky.SE2M_AttachQuickPhoto = jindo.$Class({		
	name : "SE2M_AttachQuickPhoto",

	$init : function(){},
	
	$ON_MSG_APP_READY : function(){
		this.oApp.exec("REGISTER_UI_EVENT", ["photo_attach", "click", "ATTACHPHOTO_OPEN_WINDOW"]);
	},
	
	$LOCAL_BEFORE_FIRST : function(sMsg){
		if(!!this.oPopupMgr){ return; }
		// Popup Manager에서 사용할 param
		this.htPopupOption = {
			oApp : this.oApp,
			sName : this.name,
			bScroll : false,
			sProperties : "",
			sUrl : ""
		};
		this.oPopupMgr = nhn.husky.PopUpManager.getInstance(this.oApp);
	},
	
	/**
	 * 포토 웹탑 오픈
	 */
	$ON_ATTACHPHOTO_OPEN_WINDOW : function(){			
		this.htPopupOption.sUrl = this.makePopupURL();
		this.htPopupOption.sProperties = "left=0,top=0,width=403,height=359,scrollbars=yes,location=no,status=0,resizable=no";

		this.oPopupWindow = this.oPopupMgr.openWindow(this.htPopupOption);
		
		// 처음 로딩하고 IE에서 커서가 전혀 없는 경우
		// 복수 업로드시에 순서가 바뀜	
		this.oApp.exec('FOCUS');
		return (!!this.oPopupWindow ? true : false);
	},
	
	/**
	 * 서비스별로 팝업에  parameter를 추가하여 URL을 생성하는 함수	 
	 * nhn.husky.SE2M_AttachQuickPhoto.prototype.makePopupURL로 덮어써서 사용하시면 됨.
	 */
	makePopupURL : function(){
		var sPopupUrl = "./photo_uploader/popup/photo_uploader.html";
		
		return sPopupUrl;
	},
	/*makePopupURL : function(){ 
        var tcode = parent.document.getElementById("tcode").value; 
        var sPopupUrl = "./photo_uploader/popup/photo_uploader.html?tcode=" + tcode; 
        
        return sPopupUrl; 
	},*/
	
	/**
	 * 팝업에서 호출되는 메세지.
	 */
	$ON_SET_PHOTO : function(aPhotoData){
		// 확인한다(1)
		// alert(aPhotoData);
		
		var sContents,
			aPhotoInfo,
			htData;
		
		if( !aPhotoData ){
			return; 
		}
		
		try{
			sContents = "";
			for(var i = 0; i <aPhotoData.length; i++){		
				htData = aPhotoData[i];
				
				// 확인한다(2)
				// alert(htData.sFileName + ", " + htData.sFileURL + ", " + htData.bNewLine);
				
				if(!htData.sAlign){
					htData.sAlign = "";
				}
				
				aPhotoInfo = { // 값 제대로 넘겨받아서 이곳에 배열형으로 맵방식을 저장했다.
				    /*sName : htData.sFileName || "",*/
				    /*sOriginalImageURL : htData.sFileURL,*/
				    sOriginalImageURL : "../resources/uploadImg/" + htData.sFileName,
					bNewLine : htData.bNewLine || false 
				};
				
				sContents += this._getPhotoTag(aPhotoInfo);
			}
			//alert("url : " + aPhotoInfo.sOriginalImageURL);
			// 경로 가져오는 것 성공.
			 ts1 = new Date().getTime() + 3500;
			 do ts2 = new Date().getTime(); 
			 while (ts2<ts1);
			 
			this.oApp.exec("PASTE_HTML", [sContents]); // 위즐 첨부 파일 부분 확인
		}catch(e){
			// upload시 error발생에 대해서 skip함
			return false;
		}
	},

	/**
	 * @use 일반 포토 tag 생성
	 */
	_getPhotoTag : function(htPhotoInfo){ // 이 부분이 TAG로 에디터에 값을 넘겨주는 함수이다.
		// id와 class는 썸네일과 연관이 많습니다. 수정시 썸네일 영역도 Test
		/*var sTag = '<img src="{=sOriginalImageURL}" title="{=sName}" >';*/
		var sTag = '<img src="{=sOriginalImageURL}" width="100%" height="100%">';
		if(htPhotoInfo.bNewLine){
			sTag += '<br style="clear:both;">';
		}
		sTag = jindo.$Template(sTag).process(htPhotoInfo);
		
		return sTag;
	}
});
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>  
<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script src="/Drift_wechat/js/bootstrap.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FGnoI8RVLDdSe5qWVvKv5XjGphYGNRZ2"></script>
<script type='text/javascript' src='/Drift_wechat/js/swiper.js' charset='utf-8'></script>
<style type="text/css">  
	.placeholder {
	  padding: 0 10px;
	}
</style> 

<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>

<body ontouchstart>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:10%;bottom:10%;position:relative;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						甲醛检测仪
					</h4>
				</div>
				<div class="modal-body" align="center">
					 <img  width=80% height=70%  src="/Drift_wechat/images/info.jpg">
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

    <div style="height:100%; width: 100%;position:absolute;" id="map"></div>
    
<!--     <div class="weui-navbar">
	  	<a class="weui-navbar__item weui-bar__item--on" href="/Drift_wechat/jsp/BaiduMap.jsp">
	    	首页
	    </a>
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Orders.jsp">
	    	我的订单
	    </a> 
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Delivery.jsp">
	    	仪器传递
	    </a>
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/MyIndex.jsp">
	    	个人中心
	    </a>
	</div> -->	
<!-- 	<div class="weui-flex weui-footer weui-footer_fixed-bottom">
		  <div class="weui-flex__item placeholder">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/api/order/set'">我要预约</button>
		  </div>
		  <div class="weui-flex__item placeholder">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:;">联系客服</button>
		  </div>
	</div> -->
	
<!-- 	<div style="top:20%;left:80%;position:relative;">
		<img alt="" src="/Drift_wechat/images/map/yiqi2.png"  id="yiqi" height="32px" width="32px" >详情
	</div> -->
	
	<div style="top:70%;left:80%;position:relative;">
		<img alt="" src="/Drift_wechat/images/map/tomap.png" id="countryMap" height="32px" width="32px">全国
	</div>
	
	<div style="top:75%;left:80%;position:relative;">
		<img alt="" src="/Drift_wechat/images/map/tolocal.png" id="originalMap" height="32px" width="32px">本地
	</div>
	<!--返回键  -->	
	<div class="weui-footer_fixed-bottom" style="margin-left:20%;margin-right:20%;">
		<a href="javascript:backIndex();" class="weui-btn weui-btn_primary">返回</a>
	</div>
	
	<div id="comment" class='weui-popup__container popup-bottom'>
      <div class="weui-popup__overlay"></div>
      <div class="weui-popup__modal">
      
        <div class="toolbar">
          <div class="toolbar-inner">
            <a href="javascript:;" class="picker-button close-popup">关闭</a>
            <p class="title">评论区</p>
          </div>
        </div>
        
		<div class="modal-content">
          <div class="weui-grids" id="ptContent">          
            <a class="weui-grid js_grid pb" >
                <img  id="img1" width=100px height=100px src="/Drift_wechat/images/product.png">
            </a>
            
            <a class="weui-grid js_grid pb" >
                <img  id="img2" width=100px height=100px src="/Drift_wechat/images/product.png">
            </a>
            
            <a class="weui-grid js_grid pb" >
                <img  id="img3" width=100px height=100px src="/Drift_wechat/images/product.png">
            </a>
            <div class="weui-article" id="commentDiv"></div>
	     	</div> 
         </div>
        </div>    
      </div>
</body>
<script type="text/javascript">
    //初始化信息
	var index = 0;
	var allAddressVO = [];
	//var myLocation = {"x":118.786078,"y":32.061531};
	var myLocation = {};
	var level = 18;
	
    var icon1 = new BMap.Icon("/Drift_wechat/images/map/blue.png", new BMap.Size(32,32));
    var icon2 = new BMap.Icon("/Drift_wechat/images/map/red.png", new BMap.Size(32,32));  
         
    var myIcon = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
         new BMap.Size(23, 25), {  
             offset: new BMap.Size(10, 25),  
             imageOffset: new BMap.Size(0, -250)      
         });
    
    //动态效果      
    var pbset;
	$(".pb").click(function() {
      	$.closePopup();
        pbset.open();
    });
    $("#countryMap").click(function(){
    	level = 4;
    	map_init();
    })
    $("#originalMap").click(function(){
    	level = 18;
    	map_init();
    })
    
    $("#yiqi").click(function() {
        $('#myModal').modal();
    });
    
    //返回首页
    function backIndex(){
    	window.location.href="/Drift_wechat/api/wechat/index";
    }
    //评论区初始化展示
	function getComment(openid){
		var ptUrls = [];
		var comment;
		var itemsArray = [];
		var tempUrl = "/Drift_wechat/images/product.png";
		//var baseUrl = "/home/airstaff/Server/apache-tomcat-8.0.33/upload/comment/"+openid+"/";
		var baseUrl = "/upload/comment/"+openid+"/";
		//var baseUrl = "/Drift_wechat/upload/comment/"+openid+"/";	
		$.get("/Drift_wechat/api/comment/getComment?openid="+openid,function(data){
			if(JSON.stringify(data.uc.map) != "{}"){	
				var uc = data.uc;
				comment = uc.map.comment;
				var pts = uc.map.picURLS.split(";");
				pts.pop();
				for(var i=0;i<(pts.length>3?3:pts.length);i++){
					var temp = pts[i];
					var tempImage = {"image":temp};
					itemsArray.push(tempImage);
					ptUrls.push(temp);
				}
				while(ptUrls.length<3){
					ptUrls.push(tempUrl);
				}
				$("#img1").attr('src',ptUrls[0]); 
				$("#img2").attr('src',ptUrls[1]); 
				$("#img3").attr('src',ptUrls[2]); 
				$("#commentDiv").empty();
				$("<p/>").appendTo("#commentDiv").html(comment);
				
				pbset = $.photoBrowser({
			        items: itemsArray,
			        initIndex: 0
			    });	    
			    $("#comment").popup();
			}				
		},"json");
		
	}
	
	//页面加载完成启动
	$("document").ready(function(){
	    var x = <%=session.getAttribute("locationX")%>;
	    var y = <%=session.getAttribute("locationY")%>;
	    myLocation = {"x":parseFloat(x),"y":parseFloat(y)};
		//weChatMap();
		map_init();
	});
	
<%-- 	function weChatMap(){
		wx.config({
	        appId: 'wx80e3eed8e26e852f', // 必填，企业号的唯一标识，此处填写企业号corpid
	        timestamp: parseInt("<%=session.getAttribute("timestamp")%>",10), // 必填，生成签名的时间戳
	        nonceStr: "<%=session.getAttribute("noncestr")%>", // 必填，生成签名的随机串
	        signature: "<%=session.getAttribute("signature")%>",// 必填，签名，见附录1
	        jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	    });
	    
	    wx.ready(function(){
	    	//alert(location.href.split('#')[0]);
	    	 wx.getLocation({
			        success: function (res) {
			            var ggPoint = new BMap.Point(res.longitude,res.latitude);
						var convertor = new BMap.Convertor();
				        var pointArr = [];
				        pointArr.push(ggPoint);
				        convertor.translate(pointArr, 1, 5, translateCallback);
			        },
			        fail: function(error) {
			            AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
			        }
			    });
	    });
	 
	    wx.error(function(res){
	    });
	    
		//变换坐标函数
		translateCallback = function (data){
	    if(data.status === 0) {
		      myLocation = {"x":data.points[0].lng,"y":data.points[0].lat};
				map_init();
		      }
	    else{
	      		AlertUtil.error("无法获取您的位置！");
	    	}
	    }
	} --%>

	
	//获取用户userVO列表
	function getMap(){
		$.get("/Drift_wechat/api/map/getMap",function(data){
			for(var i=0;i<data.userArr.length;i++){
				temp = {};
				temp["deviceNumber"] = data.userArr[i].map.deviceNumber;
				temp["address"] = data.userArr[i].map.address;
				temp["startDate"] = data.userArr[i].map.startDate;
				temp["nickname"] = data.userArr[i].map.nickname;
				temp["deviceState"] = data.userArr[i].map.deviceState;
				temp["openId"] = data.userArr[i].map.openId;
				temp["jqNum"] = data.userArr[i].map.jqNum;
				temp["airBoolean"] = 0;
				allAddressVO.push(temp);
			}
			getAir();
			//map_init();				
		},"json");
	}
	
	function getAir(){
		$.get("http://measure.qingair.net/management/deviceAddress/all",function(res){
				if(res.responseCode=="RESPONSE_OK"){
					for(var i=0;i<10;i++){
						temp = {};
						temp["device_id"] = res.data[i].device_id;
						temp["address"] = res.data[i].province+res.data[i].city+res.data[i].address;
						temp["nickname"] = res.data[i].owner;
						temp["phone"] = res.data[i].phone;
						temp["airBoolean"] = 1;
						if(res.data[i].hasOwnProperty("is_power_on")){
							temp["pm25"] = res.data[i].pm25;
							temp["update_time"] = new Date(res.data[i].update_time);
						}
						allAddressVO.push(temp);
					}			
				}
				else{
					alert("获取净化器地址失败！")
				}
				//map_init();
				addData();
		},"json");
	}
	
	//初始化地图
    function map_init() {  
            map = new BMap.Map("map");  
            //第1步：设置地图中心点，当前城市  
            var point = new BMap.Point(myLocation.x,myLocation.y);
            //第2步：初始化地图,设置中心点坐标和地图级别。  
            map.centerAndZoom(point, level);  
            //第3步：启用滚轮放大缩小  
            map.enableScrollWheelZoom(true);  
            //第4步：向地图中添加缩放控件  
            var ctrlNav = new window.BMap.NavigationControl({  
                anchor: BMAP_ANCHOR_TOP_LEFT,  
                type: BMAP_NAVIGATION_CONTROL_LARGE  
            });  
            map.addControl(ctrlNav);
            //第5步：向地图中添加缩略图控件  
            var ctrlOve = new window.BMap.OverviewMapControl({  
                anchor: BMAP_ANCHOR_BOTTOM_RIGHT,  
                isOpen: 1  
            });  
            map.addControl(ctrlOve);  

            //第6步：向地图中添加比例尺控件  
            var ctrlSca = new window.BMap.ScaleControl({  
                anchor: BMAP_ANCHOR_BOTTOM_LEFT  
            });  
            map.addControl(ctrlSca);  
            getMap();     
    }
    
    function addData(){
   	    //添加我的位置信息
		var myPoint = new BMap.Point(myLocation.x,myLocation.y);
        var myMaker =  new BMap.Marker(myPoint,{icon:myIcon});
        map.addOverlay(myMaker);
        var myWindow = {};
        myWindow["point"] = myPoint;
        myWindow["info"] = "您的位置<br>"+"欢迎使用甲醛仪，预计排队时间一天";          
        addMyInfoWindow(myMaker, myWindow);
        //批量添加地址信息
        bdGEO();
    }
      
   	//将地址解析结果显示在地图上,并调整地图视野
   	function bdGEO(){
		//var add = userArr[index];
		var add = allAddressVO[index];
		index++;
		geocodeSearch(add);
	}
		
	function geocodeSearch(add){
		if(add.airBoolean==1){
			var icon = icon1;
		}
		else{
			var icon = icon2;
		}
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		if(index < allAddressVO.length){
			setTimeout(window.bdGEO(),200);
		}
		myGeo.getPoint(add.address, function(point){
			if (point) {		
				var maker =  new BMap.Marker(point,{icon:icon});
              		map.addOverlay(maker);
              		add["point"] = point;
              		addInfoWindow(maker, add); 
			}else{
				alert("您选择地址没有解析到结果!"+add.map.address);
			}
		}, ""); 
	}
   // 添加检测仪信息窗口  
   function addInfoWindow(marker, addPoint) {
   		var commonInfo;
   		var titleStr;
   		if(addPoint.airBoolean==1){
   			if(addPoint.hasOwnProperty("pm25")){
   				commonInfo = "用户："+addPoint.nickname+"<br>"+"仪器编号："+addPoint.device_id+"<br>"+"pm2.5:"+addPoint.pm25+"<br>"+"检测时间:"+addPoint.update_time+"<br>";
   			}
   			else{
   				commonInfo = "用户："+addPoint.nickname+"<br>"+"仪器编号："+addPoint.device_id+"<br>";
   			}
   			titleStr = "果麦新风净化<br>"+commonInfo;
   		}
   		else{
	   		var startDateStr = addPoint.startDate.substring(0,addPoint.startDate.indexOf(" "));
	   		var commonInfo = "用户："+addPoint.nickname+"<br>"+"检测时间："+startDateStr+"<br>";
	   		if(addPoint.jqNum!=-1){
	   			commonInfo = commonInfo+"甲醛含量："+addPoint.jqNum+"<br>";
	   		}
	   		titleStr = "甲醛检测仪<br>"+commonInfo;
   		}
	    var opts = {
		  width : 150,     // 信息窗口宽度
		  height: 150,     // 信息窗口高度
		  title : titleStr, // 信息窗口标题
		  enableMessage:true,//设置允许信息窗发送短息
		  message:""
		}
		var infoWindow = new BMap.InfoWindow("检测地址："+addPoint.address, opts);  // 创建信息窗口对象 
		marker.addEventListener("click", function(){          
			this.openInfoWindow(infoWindow,addPoint.point);
			if(addPoint.airBoolean == 0){
				getComment(addPoint.openId);
				//getComment("oRTgpweSZbOxfrg9H57JwuPwMJLo");
			}
			else{
				getAirPt(addPoint.device_id);
			}
		});
	}
	
	// 添加我的位置信息窗口  
   function addMyInfoWindow(marker, poi) {  
	    var opts = {
		  width : 80,     // 信息窗口宽度
		  height: 40,     // 信息窗口高度
		  title : "", // 信息窗口标题
		  enableMessage:true,//设置允许信息窗发送短息
		  message:""
		}
		var infoWindow = new BMap.InfoWindow(poi.info, opts);  // 创建信息窗口对象 
		marker.addEventListener("click", function(){          
			this.openInfoWindow(infoWindow,poi.point);
		});
	}
			
	//空气净化器安装图片获取
	function getAirPt(device_id){
		$.get("http://measure.qingair.net/qrcode/"+device_id+"/insight",function(res){
			var pts = res.data;
			var ptUrls = [];
			var comment;
			var itemsArray = [];
			var tempUrl = "/Drift_wechat/images/product.png";
			var baseurl = "http://measure.qingair.net";
			if(res.responseCode=="RESPONSE_OK"){
				for(var i=1;i<(pts.length>3?3:pts.length);i++){
					var temp = baseUrl+pts[i];
					var tempImage = {"image":temp};
					itemsArray.push(tempImage);
					ptUrls.push(temp);
				}
				
				while(ptUrls.length<3){
					ptUrls.push(tempUrl);
				}
					
				$("#img1").attr('src',ptUrls[0]); 
				$("#img2").attr('src',ptUrls[1]); 
				$("#img3").attr('src',ptUrls[2]); 
				$("#commentDiv").empty();
					
				pbset = $.photoBrowser({
				        items: itemsArray,
				        initIndex: 1
				    });	    
				$("#comment").popup();
			}
			else{
				alert("获取空气净化器图片失败！");
			} 
		},"json");
	}
</script>      
</html>
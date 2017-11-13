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
        <style type="text/css">  
	      .placeholder {
	        padding: 0 10px;
	      }
            </style> 
<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>Drift</title>
</head>
<body ontouchstart>
    <div style="height:100%; width: 100%;position:absolute;" id="map"></div>
    
    <div class="weui-navbar">
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item weui-bar__item--on" href="/Drift_wechat/jsp/BaiduMap.jsp">
	    	首页
	    </a>
	  </div>
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Orders.jsp">
	    	我的订单
	    </a>
	  </div>
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Delivery.jsp">
	    	仪器传递
	    </a>
	  </div>
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/MyIndex.jsp">
	    	个人中心
	    </a>
	  </div>
	</div>
		
	<div class="weui-flex weui-footer weui-footer_fixed-bottom">
		  <div class="weui-flex__item placeholder">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/api/order/set'">我要预约</button>
		  </div>
		  <div class="weui-flex__item placeholder">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:;">我要插队</button>
		  </div>
	</div>
</body>

<script type="text/javascript">
	//session中获取ip并得到位置信息
	var index=0;
	var userArr=[];
	var icon1 = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
	            new BMap.Size(23, 25), {  
	                offset: new BMap.Size(10, 25),  
	                imageOffset: new BMap.Size(0, -275)                        
	            });
    var icon2 = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
         new BMap.Size(23, 25), {  
             offset: new BMap.Size(10, 25),  
             imageOffset: new BMap.Size(0, -300)  
               
         });
<%--    $("document").ready(function(){
		var ip="<%=session.getAttribute("ipAddress")%>";
		var url = "https://api.map.baidu.com/location/ip?ip="+ip+"&ak=FGnoI8RVLDdSe5qWVvKv5XjGphYGNRZ2&coor=bd09ll&";
		$.get(url,function(data){
			myLocation = data.content.point;
			getMap(myLocation);
		},"JSONP");
	}); --%>
	
	$("document").ready(function(){
		
	 	wx.config({
	        appId: 'wx80e3eed8e26e852f', // 必填，企业号的唯一标识，此处填写企业号corpid
	        timestamp: parseInt("<%=session.getAttribute("timestamp")%>",10), // 必填，生成签名的时间戳
	        nonceStr: "<%=session.getAttribute("noncestr")%>", // 必填，生成签名的随机串
	        signature: "<%=session.getAttribute("signature")%>",// 必填，签名，见附录1
	        jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	    });
	    
	    wx.ready(function(){
	    	 wx.getLocation({
			        success: function (res) {
			            myLocation = {"x":res.longitude,"y":res.latitude};
			            getMap(myLocation);
			        },
			        fail: function(error) {
			            AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
			        }
			    });
	    });
	 
	    wx.error(function(res){
	    });
	});
	
	//得到用户userVO列表
	function getMap(myLocation){
		$.get("/Drift_wechat/api/map/getMap",function(data){
			userArr = data.userArr;
			map_init(myLocation);				
		},"json");
	}
	
	//初始化地图
    function map_init(myLocation) {  
            map = new BMap.Map("map");  
            //第1步：设置地图中心点，当前城市  
            var point = new BMap.Point(myLocation.x,myLocation.y);  
            //第2步：初始化地图,设置中心点坐标和地图级别。  
            map.centerAndZoom(point, 18);  
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
             
			var myPoint = new BMap.Point(myLocation.x,myLocation.y);
            var myIcon = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
            new BMap.Size(23, 25), {  
                offset: new BMap.Size(10, 25),  
                imageOffset: new BMap.Size(0, -250)      
            });

            var myMaker =  new BMap.Marker(myPoint,{icon:myIcon});
            map.addOverlay(myMaker);
            var myWindow = {};
            myWindow["point"] = myPoint;
            myWindow["info"] = "您的位置<br>"+"欢迎使用甲醛仪，预计排队时间一天";
            
            addMyInfoWindow(myMaker, myWindow);
            bdGEO();
                 
    }
      
   	// 将地址解析结果显示在地图上,并调整地图视野
   	function bdGEO(){
		var add = userArr[index];
		index++;
		geocodeSearch(add);
	}
		
	function geocodeSearch(add){
		if(add.map.deviceState==1){
			var icon = icon1;
		}
		else{
			var icon = icon2;
		}
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		var temp = {};
		temp["nickname"] = add.map.deviceNumber;
		temp["deviceNumber"] = add.map.deviceNumber;
		temp["address"] = add.map.address;
		temp["startDate"] = add.map.startDate;
		temp["nickname"] = add.map.nickname;
		temp["deviceState"] = add.map.deviceState;
		if(index <userArr.length){
			setTimeout(window.bdGEO(),200);
		}
		myGeo.getPoint(add.map.address, function(point){
				if (point) {		
					var maker =  new BMap.Marker(point,{icon:icon});
               		map.addOverlay(maker);
               		temp["point"] = point;
               		addInfoWindow(maker, temp); 
				}else{
					/*alert("您选择地址没有解析到结果!"+add.map.address);*/
				}
		}, ""); 
	}
	
	/*function addPoint(userArr,icon){
	    // 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		
		for (var i = 0; i < userArr.length; i++) {
			var temp = {};
			temp["deviceNumber"] = userArr[i].map.deviceNumber;
			temp["address"] = userArr[i].map.address;
			temp["startDate"] = userArr[i].map.startDate;
			temp["nickname"] = userArr[i].map.nickname;
			temp["deviceState"] = userArr[i].map.deviceState;
			alert(userArr[i].map.address);
			myGeo.getPoint(userArr[i].map.address, function(point){
				if (point) {	
					var maker =  new BMap.Marker(point,{icon:icon});
               		map.addOverlay(maker);
               		temp["point"] = point;
               		console.log(temp);
               		addInfoWindow(maker, temp); 
				}else{
					alert(userArr[i].map.address+"您选择地址没有解析到结果!");
				}
			}, "");
			
           }
	}*/	
   // 添加检测仪信息窗口  
   function addInfoWindow(marker, poi) {
   		var startDateStr = poi.startDate.substring(0,poi.startDate.indexOf(" "));
   		var commonInfo = "用户："+poi.nickname+"<br>"+"仪器编号："+poi.deviceNumber+"<br>"+"检测时间："+startDateStr+"<br>";
   		var startDate = poi.startDate;
   		var startDateStr = startDate.substring(0,startDate.indexOf(" "));
   		if(poi.deviceState==1){
   			var titleStr = "历史订单<br>"+commonInfo;
   		}
   		else{
   			var titleStr = "预约订单<br>"+commonInfo;
   		}
	    var opts = {
		  width : 150,     // 信息窗口宽度
		  height: 150,     // 信息窗口高度
		  title : titleStr, // 信息窗口标题
		  enableMessage:true,//设置允许信息窗发送短息
		  message:""
		}
		var infoWindow = new BMap.InfoWindow("检测地址："+poi.address, opts);  // 创建信息窗口对象 
		marker.addEventListener("click", function(){          
			this.openInfoWindow(infoWindow,poi.point);
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
       	</script>      
</html>  
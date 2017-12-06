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
	//初始化信息
	var index = 0;
	var index2 = 0;
	var allAddressVO = [];
	var userArr = [];
	var myLocation = {};
	var airAddress = [];
	
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
    var myIcon = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
         new BMap.Size(23, 25), {  
             offset: new BMap.Size(10, 25),  
             imageOffset: new BMap.Size(0, -250)      
         });
	
	//页面加载完成启动
	$("document").ready(function(){
		weChatMap();
	});
	
	function weChatMap(){
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
				//getMap(myLocation);
				getMap();
		      }
	    else{
	      		AlertUtil.error("无法获取您的位置！");
	    	}
	    }
	}

	
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
				temp["airBoolean"] = 0;
				allAddressVO.push(temp);
			}
			//map_init(myLocation);
			//map_init();	
			//getAirCleanAddress();
			getAir();					
		},"json");
	}
	
	//获取空气净化器地址
	function getAir(){
	   $.ajax({    
		   url:'http://measure.qingair.net/management/deviceAddress/all',
		   type:'get',    
		   dataType:"json",       
		   timeout:3000,
		   success:function(res,textStatus){    
			    if(res.responseCode=="RESPONSE_OK"){
					for(var i=0;i<res.data.length;i++){
						temp = {};
						temp["deviceNumber"] = res.data[i].device_id;
						temp["address"] = res.data[i].province+res.data[i].city+res.data[i].address;
						temp["nickname"] = res.data[i].owner;
						temp["phone"] = res.data[i].phone;
						temp["airBoolean"] = 1;
						allAddressVO.push(temp);
					}			
				}
				else{
					alert("获取净化器地址失败！")
				}
				map_init();
		   }       
	   });   
	}
	
	//初始化地图
    function map_init() {  
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
   			commonInfo = "用户："+addPoint.nickname+"<br>"+"仪器编号："+addPoint.deviceNumber+"<br>"+"电话："+addPoint.phone+"<br>";
   			titleStr = "空气净化器<br>"+commonInfo;
   		}
   		else{
	   		var startDateStr = addPoint.startDate.substring(0,addPoint.startDate.indexOf(" "));
	   		var commonInfo = "用户："+addPoint.nickname+"<br>"+"仪器编号："+addPoint.deviceNumber+"<br>"+"检测时间："+startDateStr+"<br>";
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
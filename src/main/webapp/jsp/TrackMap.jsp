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
    <div style="height:100%; width: 100%;position:absolute;" id="map"></div>
	<div class="weui-flex weui-footer weui-footer_fixed-bottom">
		  <div class="weui-flex__item placeholder">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/jsp/Orders.jsp'">返回</button>
		  </div>
	</div>
</body>
<script type="text/javascript">
        
	//初始化信息
	var index = 0;
	var allDevices = [];
	var intervalDevices = [];
	var device_index = 0;
	var size = 0;
	var points = [];
	var myLocation = {"x":118.786078,"y":32.061531};
	var level = 18;
	var icon = new BMap.Icon("/Drift_wechat/images/map/red.png", new BMap.Size(32,32));
	
	//页面加载完成启动
	$("document").ready(function(){
		map_init();
		getLists('000000005fa5ec9f015fae298e3b0007');
	});
	
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
    }
    
   	//将地址解析结果显示在地图上,并调整地图视野
   	function bdGEO(){
		var add = allDevices[index];
		index++;
		geocodeSearch(add);
	}
		
	function geocodeSearch(add){
		// 创建地址解析器实例
		var myGeo = new BMap.Geocoder();
		if(index < allDevices.length){
			setTimeout(window.bdGEO(),200);
		}
		myGeo.getPoint(add.address, function(point){
			if (point) {	
				var maker = new BMap.Marker(point,{icon:icon});
              	map.addOverlay(maker);
				points.push(point);	
				addLine(points);
				setZoom(points);
			}else{
				alert("您选择地址没有解析到结果!"+add.map.address);
			}
		}, ""); 
	}
	
		//添加线
		function addLine(points){
			var linePoints = [],pointsLen = points.length,i,polyline;
			if(pointsLen == 0){
				return;
			}
			// 创建标注对象并添加到地图   
			for(i = 0;i < pointsLen ; i++){
				linePoints.push(new BMap.Point(points[i].lng,points[i].lat));
			}

			polyline = new BMap.Polyline(linePoints, {strokeColor:"red", strokeWeight:2, strokeOpacity:0.5});   //创建折线
			map.addOverlay(polyline);   //增加折线
		}
		
		//根据点信息实时更新地图显示范围，让轨迹完整显示。设置新的中心点和显示级别
		function setZoom(bPoints){
			var view = map.getViewport(eval(bPoints));
			var mapZoom = view.zoom; 
			var centerPoint = view.center; 
			map.centerAndZoom(centerPoint,mapZoom);
		}
		
		function getLists(id){
			/* $.getJSON('/Drift_wechat/api/order/deviceTrack?deviceid='+id,function(json){ */
			$.getJSON('/Drift_wechat/api/order/deviceTrack',function(json){
				intervalDevices = json.track;
				size = intervalDevices.length;
				interval();
			});
		}
		
		function interval(){
			var myDate = new Date();
			console.log(myDate.getMinutes() + ':' + myDate.getSeconds());
			allDevices.push(intervalDevices[device_index]);
			bdGEO();
			device_index ++;
			if(device_index < size){
				setTimeout(interval, 800);
			}else{
				allDevices.splice(0,size);
				device_index = 0;
			}
		}
</script>      
</html>
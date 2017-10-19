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
	    首页
	  </div>
	  <div class="weui-navbar__item weui_bar__item_on">
	  我的预约
	  </div>
	  <div class="weui-navbar__item">
	   仪器传递
	  </div>
	  <div class="weui-navbar__item">
	    个人中心
	  </div>
	</div>
		
	<div class="weui-flex weui-footer weui-footer_fixed-bottom">
		  <div class="weui-flex__item placeholder">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="">我要预约</button>
		  </div>
		  <div class="weui-flex__item placeholder">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="">我要插队</button>
		  </div>
	</div>
</body>
	<script type="text/javascript">
	function getUserLocation(ip){
		$.get(url,function(data){
					result = data.content.address;
					result = data.content.point;
					console.log(result);
					alert(result);
					map_init(myLocation,markerArr1,markerArr2);
		},"JSONP");
	}
		//发送GET请求
		$("document").ready(function(){
			$.get("/Drift_wechat/api/map/getMapData",function(data){
				//var map;
				var myLocation = data.myLocation;
				var markerArr1 = data.markerArr1;
				var markerArr2 = data.markerArr2;
				var myIp = "218.94.159.98";
				//$.session.get('id');
				var url = "https://api.map.baidu.com/location/ip?ip="+myIp+"&ak=FGnoI8RVLDdSe5qWVvKv5XjGphYGNRZ2&coor=bd09ll&";				
			},"json");
		});
		  
              function map_init(myLocation,markerArr1,markerArr2) {  
                      map = new BMap.Map("map");  
                      //第1步：设置地图中心点，当前城市  
                      var point = new BMap.Point(myLocation.px,myLocation.py);  
                      //第2步：初始化地图,设置中心点坐标和地图级别。  
                      map.centerAndZoom(point, 18);  
                      //第3步：启用滚轮放大缩小  
                      map.enableScrollWheelZoom(true);  
                      //第4步：向地图中添加缩放控件  
                      /*var ctrlNav = new window.BMap.NavigationControl({  
                          anchor: BMAP_ANCHOR_TOP_LEFT,  
                          type: BMAP_NAVIGATION_CONTROL_LARGE  
                      });  
                      map.addControl(ctrlNav);*/  
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
                        
                        
                      //第7步：绘制点    
                      for (var i = 0; i < markerArr1.length; i++) {
                          var myPoint = new BMap.Point(markerArr1[i].px,
                              markerArr1[i].py);

                          var myIcon = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
                          new BMap.Size(23, 25), {  
                              offset: new BMap.Size(10, 25),  
                              imageOffset: new BMap.Size(0, -275)  
                                
                          }); 
                          var maker =  new BMap.Marker(myPoint,{icon:myIcon});
                          map.addOverlay(maker); 
                          addInfoWindow(maker, markerArr1[i]);   
                      }

                      for (var i = 0; i < markerArr2.length; i++) {
                          var myPoint = new BMap.Point(markerArr2[i].px,
                              markerArr2[i].py);

                          var myIcon = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
                          new BMap.Size(23, 25), {  
                              offset: new BMap.Size(10, 25),  
                              imageOffset: new BMap.Size(0, -300)  
                                
                          }); 
                          var maker =  new BMap.Marker(myPoint,{icon:myIcon});
                          map.addOverlay(maker);
                          addInfoWindow(maker, markerArr2[i]);  
                      }


                      var myPoint = new BMap.Point(myLocation.px,myLocation.py);
                      var myIcon = new BMap.Icon("/Drift_wechat/images/baiduMarkers.png",  
                      new BMap.Size(23, 25), {  
                          offset: new BMap.Size(10, 25),  
                          imageOffset: new BMap.Size(0, -250)      
                      });

                      var maker =  new BMap.Marker(myPoint,{icon:myIcon});
                      map.addOverlay(maker);
                      addInfoWindow(maker, myLocation);
              }  
				
            	// 添加信息窗口  
              function addInfoWindow(marker, poi) {  
                      //marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画  
                      /*var label = new window.BMap.Label(poi.title, { offset: new window.BMap.Size(20, -10) }); 
                      marker.setLabel(label);  
                      var clo="";  
                      if("hello"==poi.title){  
                          clo="#FFFFFF";  
                      }else{  
                          clo="#FF5782";  
                      }  
                      var info = new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;color:"+clo+";’>" +poi.title+ "</p>"); // 创建信息窗口对象  
                      marker.addEventListener("mouseover", openInfoWinFun);  
                      var openInfoWinFun = function () {  
                          this.openInfoWindow(info);  
                      };*/
                      
			    var opts = {
				  width : 200,     // 信息窗口宽度
				  height: 100,     // 信息窗口高度
				  title : poi.title , // 信息窗口标题
				  enableMessage:true,//设置允许信息窗发送短息
				  message:"亲耐滴，晚上一起吃个饭吧？戳下面的链接看下地址喔~"
				}
				var infoWindow = new BMap.InfoWindow("地址：*******", opts);  // 创建信息窗口对象 
				marker.addEventListener("click", function(){          
					this.openInfoWindow(infoWindow,poi);
				}); 
              }  
              
              // 添加标注  
              function addMarker(point, index) {  
                  var myIcon = new BMap.Icon("/Drift_wechat/images/baiduMarker.png",  
                      new BMap.Size(23, 25), {  
                          offset: new BMap.Size(10, 25),  
                          imageOffset: new BMap.Size(0, -250 -  index * 25)  
                            
                      });  
                  var marker = new BMap.Marker(point, { icon: myIcon });  
                  map.addOverlay(marker);  
                  return marker;  
              }  
       	</script>      
</html>  
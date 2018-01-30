/**
 * 
 */
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
    	map_init(myLocation);
    })
    $("#originalMap").click(function(){
    	level = 18;
    	map_init(myLocation);
    })
    
    $("#yiqi").click(function() {
        $('#myModal').modal();
    });
    
	//初始化地图
    function map_init(location) {
    	    myLocation = location;
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
    //返回首页
    function backIndex(){
    	window.location.href="/Drift_wechat/api/wechat/center";
    }
    
    //评论区初始化展示
	function getComment(openid){
		var ptUrls = [];
		var comment;
		var itemsArray = [];
		var tempUrl = "/Drift_wechat/images/default.jpg";
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
		},"json");
	}
	
	function getAir(){
		$.get("http://measure.qingair.net/management/deviceAddress/all",function(res){
				if(res.responseCode=="RESPONSE_OK"){
					for(var i=0;i<res.data.length;i++){
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
				addData();
		},"json");
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
   			var name = addPoint.nickname;
   			name = name.substring(0,1)+"XX";
   			if(addPoint.hasOwnProperty("pm25")){
   				commonInfo = "用户："+name+"<br>"+"pm2.5:"+addPoint.pm25+"<br>"+"检测时间:"+addPoint.update_time+"<br>";
   			}
   			else{
   				commonInfo = "用户："+name+"<br>";
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
	    
		var infoWindow = new BMap.InfoWindow("检测地址："+cutAddress(addPoint.address), opts);  // 创建信息窗口对象 
		marker.addEventListener("click", function(){          
			this.openInfoWindow(infoWindow,addPoint.point);
			if(addPoint.airBoolean == 0){
				getComment(addPoint.openId);
			}
			else{
				getAirPt(addPoint.device_id);
			}
		});
	}
   
   //处理位置显示信息
   function cutAddress(address){
	   if(address.indexOf("区")!=-1){
		   return address.substring(0,address.indexOf("区")+1);
	   }
	   else if(address.indexOf("市")!=-1){
		   return address.substring(0,address.indexOf("市")+1);
	   }
	   else{
		   return address.substring(0,address.indexOf("省")+1);
	   }   
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
			var tempUrl = "/Drift_wechat/images/default.jpg";
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
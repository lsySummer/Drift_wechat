<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
	<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
	<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.css">
	<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
	<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
	<!-- <script type="text/javascript" src="/Drift_wechat/js/myJS/Forbid.js"></script> -->
	<script src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script src="/Drift_wechat/js/jquery-weui.js"></script>
	<script src="/Drift_wechat/js/bootstrap.min.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FGnoI8RVLDdSe5qWVvKv5XjGphYGNRZ2"></script>
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	a{color:#696969} 
	a:hover{color:#00BFFF}
	body{
	  width: 100%;
	  height:auto;
	  background:url("/Drift_wechat/images/sky.jpg") no-repeat;
	  background-size: cover;
	}
</style>  

<body ontouchstart style="">
	<!--主体块  -->
	<!-- <div style="height:auto;width:100%;background:#D3D3D3;position:absolute;background-image:url(/Drift_wechat/images/sky.jpg);background-size:cover"> -->
	<div style="height:auto;width:100%;position:absolute;">
		<!--BGM  -->
		<div style="height:50%;margin:10px;position:relative; align="center">
			<br/>
			<br/>
			<br/>
			<marquee><span style="font-weight: bolder;font-size: 40px;color:#FFFFFF;">甲醛仪累计漂流${allnum}次</br>今日漂流${todaynum}次</span></marquee>
			<br/>
			<%-- <marquee><span style="font-weight: bolder;font-size: 40px;color:#FFFFFF;">今日漂流${todaynum}次</span></marquee> --%>
		</div>
		<!--推荐的热门话题 -->
		<c:forEach items="${qList}" var="Q" varStatus="index">
			<div style="margin:10px;background:#FFFFFF;position:relative;">
				<a href="/Drift_wechat/api/QA/DateSort?qid=${Q.id}  " class="weui-media-box weui-media-box_appmsg">
				      <div class="weui-media-box__bd">
					      <p class="weui-media-box__title question">${Q.title}</p>
				          <p class="weui-media-box__desc">${qnumList[index.count-1]}回答</p>
				      </div>
					<c:choose>  
					   <c:when test="${not empty Q.picSig}">
					   		<div class="weui-media-box__hd">
					          <img class="weui-media-box__thumb" src="${Q.picSig}">
					        </div>
					   </c:when>  				     
					   <c:otherwise> 
					   		<div class="weui-media-box__hd">
					          <img class="weui-media-box__thumb" src="/Drift_wechat/images/icon.jpg">
					        </div>
					   </c:otherwise>  
					</c:choose>  
			    </a>
		    </div>
		</c:forEach>
		<div style="height:120px;position:relative;"></div>
	</div>
	
	<!--辅助功能  -->
	<div style="top:2%;left:10%;position:absolute;">
		<a href="javascript:toMap()" ><img alt="" src="/Drift_wechat/images/tomap.png" id="countryMap" height="32px" width="32px"></a>
	</div>
	<div style="top:2%;left:80%;position:absolute;">
		<a href="javascript:ShowModel()"><img alt="" src="/Drift_wechat/images/yiqi2.png"  height="32px" width="32px"></a>
	</div>
	
	<div id="loadmore" class="weui-loadmore" style="top:25%;position: absolute;width:100%;display:none">
	  <i class="weui-loading"></i>
	  <span class="weui-loadmore__tips" style="color:#FFFFFF">正在加载</span>
	</div>
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
	<!--底部块-->
	<div class="weui-footer_fixed-bottom" style="bottom:0">
		
		<div class="weui-flex">
		  <div class="weui-flex__item placeholder" style="padding-left:20px;padding-right:10px">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/api/order/set'">我要预约</button>
		  </div>
		  <div class="weui-flex__item placeholder" style="padding-left:10px;padding-right:20px">
		   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:window.location.href='/Drift_wechat/jsp/Contact.jsp';">联系客服</button>
		  </div>
		</div>
		  <div style="height:70px"></div>
		  
		  <div class="weui-tabbar" style="bottom:0">
			  <a href="/Drift_wechat/api/wechat/index" class="weui-tabbar__item weui-bar__item--on">
			    <div class="weui-tabbar__icon">
			      <img src="/Drift_wechat/images/navi/index.png" alt="">
			    </div>
			    <p class="weui-tabbar__label">首页</p>
			  </a>
			  <a href="/Drift_wechat/api/user/getState" class="weui-tabbar__item">
			    <div class="weui-tabbar__icon">
			      <img src="/Drift_wechat/images/navi/order.png" alt="">
			    </div>
			    <p class="weui-tabbar__label">订单</p>
			  </a>
			  <a href="/Drift_wechat/api/QA/Index" class="weui-tabbar__item">
			    <div class="weui-tabbar__icon">
			      <img src="/Drift_wechat/images/navi/community.png" alt="">
			    </div>
			    <p class="weui-tabbar__label">社区</p>
			  </a>
			  <a href="/Drift_wechat/jsp/MyIndex.jsp" class="weui-tabbar__item">
			    <div class="weui-tabbar__icon">
			      <img src="/Drift_wechat/images/navi/my.png" alt="">
			    </div>
			    <p class="weui-tabbar__label">我的</p>
			  </a>
		</div>
	</div>
</body>
<script type="text/javascript">
	wx.config({
	       appId: 'wx80e3eed8e26e852f', // 必填，企业号的唯一标识，此处填写企业号corpid
	       timestamp: parseInt("<%=session.getAttribute("timestamp")%>",10), // 必填，生成签名的时间戳
	       nonceStr: "<%=session.getAttribute("noncestr")%>", // 必填，生成签名的随机串
	       signature: "<%=session.getAttribute("signature")%>",// 必填，签名，见附录1
	       jsApiList: ['getLocation','hideAllNonBaseMenuItem'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	});
	wx.ready(function(){
		wx.hideAllNonBaseMenuItem({
		   success: function () {
		   }
		});
	})
	
	wx.error(function(res){
		
		wx.hideAllNonBaseMenuItem({
		   success: function () {
		   }
		});
	});
 	var x=-1,y=-1;
	function ShowModel(){
		$('#myModal').modal();
	}
	
	function toMap(){
		$("#loadmore").show();
		if(<%=session.getAttribute("locationX")%>!=null){
			window.location.href="/Drift_wechat/api/map/map?x=1&y=1";
		}
		else{
			weChatMap();
		}
	}
		//页面加载完成启动
 	/* $("document").ready(function(){
	    
	}); */ 
	
	function weChatMap(){
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
			        	alert("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
			            AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
			        }
			  });
	    });
	 
	    wx.error(function(res){
	    });
	    
		//变换坐标函数
		translateCallback = function (data){
		    if(data.status === 0) {
			      //myLocation = {"x":data.points[0].lng,"y":data.points[0].lat};}
			      x = data.points[0].lng;
			      y = data.points[0].lat;
			      window.location.href="/Drift_wechat/api/map/map?x="+data.points[0].lng+"&y="+data.points[0].lat;
			    }
		    else{
		      		AlertUtil.error("无法获取您的位置！");
		    	}
	    }
	}
</script>
</html>
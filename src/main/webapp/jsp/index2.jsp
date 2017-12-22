<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	a{color:#696969} 
	a:hover{color:#00BFFF}
</style>  

<body>
	<!--文案 -->
	<div style="height:70%; width:100%;position:absolute;margin:0px;padding:0px">
		<img width=100% height=100% src="/Drift_wechat/images/info.jpg" title="甲醛漂流仪"/>
	</div>
	<div style="top:70%;height:30%;width:100%;background:#D3D3D3;position:absolute;"></div>
	<div style="top:10%;left:90%;position:relative;">
		<a href="/Drift_wechat/api/map/map" ><img alt="" src="/Drift_wechat/images/tomap.png" id="countryMap" height="32px" width="32px"><h5>详情</h5></a>
	</div>
	<div style="bottom:-50%;margin:10px;background:#FFFFFF;position:relative;">
		<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
	      <div class="weui-media-box__hd">
	        <img class="weui-media-box__thumb" src="/Drift_wechat/images/icon.jpg">
	      </div>
	      <div class="weui-media-box__bd">
	        <h4 class="weui-media-box__title">你家的甲醛含量超标么？</h4>
	        <p class="weui-media-box__desc">由各种物质组成的巨型球状天体。</p>
	      </div>
	    </a>
	</div>
	
	<div style="bottom:-50%;margin:10px;background:#FFFFFF;position:relative;">
		<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
	      <div class="weui-media-box__hd">
	        <img class="weui-media-box__thumb" src="/Drift_wechat/images/icon.jpg">
	      </div>
	      <div class="weui-media-box__bd">
	        <h4 class="weui-media-box__title">如何清除甲醛呢？</h4>
	        <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。
	              由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。
	        </p>
	      </div>
	    </a>
	</div>
	<!--导航栏  -->
	<div class="weui-tabbar">
	  <a href="/Drift_wechat/jsp/BaiduMap.jsp" class="weui-tabbar__item weui-bar__item--on">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/index.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">首页</p>
	  </a>
	  <a href="/Drift_wechat/jsp/Orders.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/order.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">订单</p>
	  </a>
	  <a href="/Drift_wechat/jsp/community/CommunityIndex.jsp" class="weui-tabbar__item">
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
	

	 
</body>
</html>
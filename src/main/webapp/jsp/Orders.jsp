<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">  
    <title>我的预约</title>
  </head>
  
  <body>
  	<div class="weui-navbar">
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/BaiduMap.jsp">
	    	首页
	    </a>
	  </div>
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item weui-bar__item--on" href="/Drift_wechat/jsp/Orders.jsp">
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
  	<header class='demos-header'>
      <h3 class="demos-title">我的预约</h3>
    </header>
    <div class="container" id="container">
    	<div class="weui-form-preview" id="item1">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">订单编号</label>
		    <em class="weui-form-preview__value" id="orderId">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">下单日期</label>
		      <span class="weui-form-preview__value" id="startDate">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">预约结束</label>
		      <span class="weui-form-preview__value" id="endDate">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">送货地址</label>
		      <span class="weui-form-preview__value" id="address">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">收件人</label>
		      <span class="weui-form-preview__value" id="userName">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">联系电话</label>
		      <span class="weui-form-preview__value" id="userPhone">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">设备状态</label>
		      <span class="weui-form-preview__value" id="state">暂无</span>
		    </div>
		  </div>
		</div>
	</div>
  </body>
  <script type="text/javascript" src="/Drift_wechat/js/myJS/Orders.js"></script>
</html>

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
    <title>仪器传递</title>
  </head>
  
  <body>
  	<div class="weui-tab">
  	<div class="weui-navbar">
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/BaiduMap.jsp">
	    	首页
	    </a>
	  </div>
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Orders.jsp">
	    	我的订单
	    </a>
	  </div>
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item weui-bar__item--on" href="/Drift_wechat/jsp/Delivery.jsp">
	    	仪器传递
	    </a>
	  </div>
	  <div class="weui-navbar__item">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/MyIndex.jsp">
	    	个人中心
	    </a>
	  </div>
	</div>
	<div class="weui-tab__bd">
    <div class="weui-tab__bd-item weui-tab__bd-item--active" id="container">
    
      <h2 class="demos-title">仪器传递</h2>
      
    	<div class="weui-form-preview" id="item1">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">上家名称</label>
		    <em class="weui-form-preview__value" id="previous">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum1">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">设备id</label>
		      <span class="weui-form-preview__value" id="deviceId1">暂无</span>
		    </div>
		  </div>
		  <div class="weui-form-preview__ft">
		  	<a class="weui-form-preview__btn weui-form-preview__btn_default" href="/Drift_wechat/jsp/DeliveryWrite.jsp">快递查询</a>
    		<button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" onclick="javascript:confirm()">确认收货</button>
  		  </div>
		</div>
		</br>
		<div class="weui-form-preview">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">下家名称</label>
		    <em class="weui-form-preview__value" id="next">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum2">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">设备id</label>
		      <span class="weui-form-preview__value" id="deviceId2">暂无</span>
		    </div>
		  </div>
		  <div class="weui-form-preview__ft">
		  	<a class="weui-form-preview__btn weui-form-preview__btn_default" href="/Drift_wechat/jsp/DeliveryWrite.jsp">快递查询</a>
    		<button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/jsp/DeliveryWrite.jsp'">填写快递信息</button>
  		  </div>
		</div>
		</br>
	</div>
	</div>
	</div>
  </body>
  <script type="text/javascript" src="/Drift_wechat/js/myJS/Delivery.js"></script>
</html>

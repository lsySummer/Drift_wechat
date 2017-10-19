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
  	<header class='demos-header'>
      <h2 class="demos-title">仪器传递</h2>
    </header>
    <div class="container" id="container">
    	<div class="weui-form-preview" id="item1">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">上家名称</label>
		    <em class="weui-form-preview__value" id="previous">test</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum1">test</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">发货日期</label>
		      <span class="weui-form-preview__value" id="deliveryDate1">test</span>
		    </div>
		  </div>
		</div>
		<div class="weui-form-preview" id="item2">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">下家名称</label>
		    <em class="weui-form-preview__value" id="next">test</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum2">test</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">发货日期</label>
		      <span class="weui-form-preview__value" id="deliveryDate2">test</span>
		    </div>
		  </div>
		</div>
		<a href="javascript:;" class="weui-btn weui-btn_plain-default">回到首页</a>
	</div>
  </body>
  <script type="text/javascript" src="/Drift_wechat/js/myJS/Delivery.js"></script>
</html>
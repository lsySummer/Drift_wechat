<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">  
    <link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
	<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
	<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
    <title>购买耗材</title>
  </head>
  
  <body>
    <div class="container" id="container">
		
  	</div>

  </body>
</html>

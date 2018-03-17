<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
  <link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
  <link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
  <script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/myJS/stateJump.js"></script>
  <!-- set `maximum-scale` for some compatibility issues -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
  <title>使用须知</title>
</head>
<body id="body">
	<!--步骤条 -->
	<%@include file="TopBar.html"%>
  	<!--文案 -->
	<div style="margin-top:5%;" id="imagebox">
		<img src="/Drift_wechat/images/step3.jpg" title="甲醛漂流仪" id="image" style="width:100%"/>
	</div>
	<div id="other"></div>
	<!--按钮 -->
	<div class="weui-flex__item placeholder" style="padding-bottom:20%;">
	   <button id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/jsp/Orders/FeedbackWrite.jsp';">填写使用信息</button>
	</div>
  	<!--BOTTOM -->
  	<%@include file="BottomBar.html"%>
	<script>
		set(3);
		stateCheck(3);
	</script>
</body>
</html>

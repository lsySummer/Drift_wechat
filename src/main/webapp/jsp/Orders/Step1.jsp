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
  <script type="text/javascript" src="/Drift_wechat/js/myJS/Forbid.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/myJS/stateJump.js"></script>
  <!-- set `maximum-scale` for some compatibility issues -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
  <title>开始预约</title>
</head>
<body>
	<!--TOP -->
	<%@include file="TopBar.html"%>
  	<!--文案 -->
	<div style="height:100%;width:100%;">
		<img width=100% src="/Drift_wechat/images/detail.jpg" title="甲醛漂流仪"/>
	</div>
	<!--按钮组 -->
	<div id="botton" class="weui-flex" style="padding-bottom:20%;width:100%;">
	  <div class="weui-flex__item placeholder" style="padding-left:20px;padding-right:10px">
	   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/api/order/set';">我要预约</button>
	  </div>
	  <div class="weui-flex__item placeholder" style="padding-left:10px;padding-right:20px">
	   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:window.location.href='/Drift_wechat/jsp/Contact.jsp';">联系客服</button>
	  </div>
	</div>
	<!--BOTTOM -->
  	<%@include file="BottomBar.html"%>
	<script>
		set(1);
		stateCheck(1);
	</script>
</body>
</html>

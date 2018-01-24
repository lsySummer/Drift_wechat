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
<link rel="stylesheet" href="/Drift_wechat/css/jquery-ui.min.css">
<script type="text/javascript" src="/Drift_wechat/js/myJS/Forbid.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>

<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	hr.style-four {
		height: 12px;
		border: 0;
		box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
	}
</style>
<body>
	
  	<div class="weui-cell" align="center" style="background:#F5F5F5;margin:10px ">
  		<!-- <div class="weui-cell__ft"><a href="/Drift_wechat/jsp/community/CommunityIndex.jsp"><img alt="" style="" src="/Drift_wechat/images/community/close.png"></a></div> -->
  		<div class="weui-cell__ft"><a href="javascript:confirmBack()"><img alt="" style="" src="/Drift_wechat/images/community/close.png"></a></div>
	    <div class="weui-cell__bd" style="color:black">
	      <h2>撰写回答</h2>
	    </div>
	    <div class="weui-cell__ft"><a href="/Drift_wechat/jsp/community/QuestionAnswer.jsp"><img id="release" alt="" style="" src="/Drift_wechat/images/community/before.png"></a></div>
	</div>
	<hr class="style-four" />
	<div class="weui-cells weui-cells_form">
	  <div class="weui-cell">
	    <div class="weui-cell__bd">
	      <textarea class="weui-textarea" onkeyup="changeIncon()" placeholder="写回答..." rows="10"></textarea>
	      <div class="weui-textarea-counter"><span>0</span>/200</div>
	    </div>
	  </div>
	</div>
</body>

<script type="text/javascript">
	function changeIncon(){
		$("#release").attr("src","/Drift_wechat/images/community/release.png")
	}
	function confirmBack(){
		$.confirm("确定放弃您的编辑退出么？", function() {
		  	window.location.href="/Drift_wechat/jsp/community/QuestionAnswer.jsp";//点击确认后的回调函数
		  }, function() {
		  //点击取消后的回调函数
		});
	}
</script>
</html>
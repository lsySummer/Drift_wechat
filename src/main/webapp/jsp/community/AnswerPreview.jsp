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
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	hr.style-four {
		height: 10px;
		border: 0;
		box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
	}
	a{color:#6C6C6C; text-decoration:none;font-weight:bold;font-size:15px}/*链接设置*/
	a:hover{color:#00BFFF; text-decoration:underline;font-weight:bold;}/*鼠标放上的链接设置*/
</style> 	
<body>
	
  	<div class="weui-cell" align="center" style="background:#F5F5F5;margin:10px ">
  		<div class="weui-cell__ft"><a href="/Drift_wechat/api/QA/dateSort?qid=${question.id}"><img alt="" style="" src="/Drift_wechat/images/community/back.png"></a></div>
	    <div class="weui-cell__bd" style="color:black">
	      <h3>详细回答</h3>
	    </div>
	    <div class="weui-cell__ft"><a href=><img id="release" alt="" style="" src="/Drift_wechat/images/community/omit.png"></a></div>
	</div>
	<hr/>
	
	<div class="weui-cells weui-cells_form" style="margin-top:0px">
		  <div class="weui-cell" style="font-weight:bold">
		      <p>${question.title}</p>
		  </div>
		  <hr class="style-four" />
		  <div class="weui-cell" style="font-weight:bold">
		      <img src="/Drift_wechat/images/icon.jpg" style="height: 20px;width:20px;vertical-align:middle">&nbsp;&nbsp;&nbsp;${user.nickName}
		  </div>
		  <div style="margin:15px">
			   ${answer.content}
		  </div>
		  <div  class="weui-cell weui-cells_form">
		  	<div class="weui-cell__bd" align="center" >
		  		<a href="/Drift_wechat/jsp/community/CommunityIndex.jsp"><img alt="" style="vertical-align:middle" src="/Drift_wechat/images/community/invite.png">点赞</a>
		  	</div>
		  </div>
	</div>
</body>
</html>
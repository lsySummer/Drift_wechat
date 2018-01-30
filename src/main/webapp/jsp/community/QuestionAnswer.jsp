  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
<script type="text/javascript" src="/Drift_wechat/js/myJS/Forbid.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script src="/Drift_wechat/js/bootstrap.min.js"></script>
<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	hr.style-four {
		height: 5px;
		border: 0;
		box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
	}
	hr.style-one {
		border: 0;	
		height: 1px;	
		background: #333;	
		background-image: linear-gradient(to right, #ccc, #333, #ccc);	
	}
	p{font-size:15px} 
</style> 	
<body>

	<div class="weui-cell" align="center" style="background:#F5F5F5;margin:10px ">
  		<div class="weui-cell__ft"><a href="/Drift_wechat/api/QA/Index"><img alt="" style="" src="/Drift_wechat/images/community/back.png"></a></div>
	    <div class="weui-cell__bd" style="font-weight:bold;">
	      <h4>回答浏览</h4>
	    </div>
	    <div class="weui-cell__ft"><a href=><img id="release" alt="" style="" src="/Drift_wechat/images/community/omit.png"></a></div>
	</div>
	<hr class="style-four" />
	
	<div class="weui-cells weui-cells_form" style="margin-top:0px">
		  <div class="weui-cell" style="font-weight:bold;font-size:16px">
		      <p>${question.title}</p>
		  </div>
		  <div style="margin:10px;"align="center">
			   <p>${question.content}</p>
		  </div>
		  
		  <div  class="weui-cell weui-cells_form">
<!-- 		  	<div class="weui-cell__bd" align="center" >
		  		<a href="/Drift_wechat/jsp/community/CommunityIndex.jsp"><img alt="" style="vertical-align:middle" src="/Drift_wechat/images/community/invite.png">   邀请回答</a>
		  	</div> -->
		  	<div class="weui-cell__bd" align="center">
		  		<a href="/Drift_wechat/jsp/community/PublishA.html?qid=${question.id}"><img alt="" style="vertical-align:middle" src="/Drift_wechat/images/community/add.png">   添加回答</a>
		  	</div>
		  </div>
		  <hr class="style-one"/>
		  <div class="weui-cell" style="background:#F5F5F5;padding:5px;font-size:15px;color:#A9A9A9">
		      <div class="weui-cell__bd" align="center" >${anum}个回答</div>
		      <div class="weui-cell__bd" align="center">
					<div class="dropdown">
					    <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">排序
					        <span class="caret"></span>
					    </button>
					    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
					        <li role="presentation">
					            <a role="menuitem" tabindex="-1" href="/Drift_wechat/api/QA/LikeSort?qid=${question.id}">按质量排序</a>
					        </li>
					        <li role="presentation">
					            <a role="menuitem" tabindex="-1" href="/Drift_wechat/api/QA/DateSort?qid=${question.id}">按时间排序</a>
					        </li>
					    </ul>
					</div>
			  </div>
		  </div>
		  
	 <!--简略回答  -->
	<div class="weui-panel__bd">
		<c:forEach items="${aList}" var="A" varStatus="index">
			<a href="/Drift_wechat/api/QA/AnswerPreview?aid=${A.id}&qid=${question.id}" class="weui-media-box weui-media-box_appmsg">
		      <div class="weui-media-box__bd">
		      	<div class="weui-media-box__title" style="font-size:15px;color:#A9A9A9;padding-bottom:10px"> 
		      		<img src="${userList[index.count-1].headimgUrl}" style="height: 30px;width:30px;vertical-align:middle">${userList[index.count-1].nickName}
		      	</div>
		        <div class="weui-media-box__desc" style="color:#000000">${A.content}</div>
		        <div class="weui-media-box__title" style="font-size:15px;color:#A9A9A9;padding-top:10px">${likeList[index.count-1]}赞同 &nbsp;&nbsp;${dateStrs[index.count-1]}</div>
		      </div>
		    </a>
		</c:forEach>
	</div>
		
	</div>
	
	<script type="text/javascript">
		
	</script>
</body>
</html>
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
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	.question{
		color:black;
		font-size: 18px;
		font-weight:bold;
	}
	
	hr.style-one {
		border: 0;	
		height: 1px;	
		background: #333;	
		background-image: linear-gradient(to right, #ccc, #333, #ccc);	
	}
	hr.style-four {
		height: 12px;
		border: 0;
		box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
	}
</style>  

<body>
	<!--导航栏  -->
	<div class="weui-tabbar weui-footer_fixed-bottom" id="navi" style="bottom:0">
	  <a href="/Drift_wechat/api/wechat/index" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/index.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">首页</p>
	  </a>
	  <a href="/Drift_wechat/api/user/getState" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/order.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">订单</p>
	  </a>
	  <a href="javascript:;" class="weui-tabbar__item weui-bar__item--on">
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
	
	<!--问题列表-->
	<div id="questionList" class="weui-panel weui-panel_access">
		<div class="weui-cell" style="background:#F5F5F5;margin:10px " align="center" >
		    <div class="weui-cell__bd" style="color:black">
		      <h3>热门话题</h3>
		    </div>
		    <div class="weui-cell__ft"><a href="/Drift_wechat/jsp/community/PublishQ.html"><img alt="" style="" src="/Drift_wechat/images/community/ask.png">&nbsp;&nbsp;&nbsp;提问</a></div>
		</div>
		<hr class="style-four" />
			  
	  <div class="weui-panel__bd">
 		<c:forEach items="${qList}" var="Q" varStatus="index">
			<a href="/Drift_wechat/api/QA/dateSort?qid=${Q.id}  " class="weui-media-box weui-media-box_appmsg">
				<div class="weui-cell__hd" style="width:30px"><h3>${index.count}</h3></div>
			      <div class="weui-media-box__bd">
				      <p class="weui-media-box__title question">${Q.title}</p>
			        <p class="weui-media-box__desc">${qnumList[index.count-1]}回答</p>
			      </div>
			      <div class="weui-media-box__hd">
			        <img class="weui-media-box__thumb" src="/Drift_wechat/images/info.jpg">
			      </div>
		    </a>
		</c:forEach>
	  </div>
	  	  
	</div>
</body>
<script type="text/javascript">
/* $(document).ready(function(){  
    var p=0,t=0;  
    $(window).scroll(function(e){  
            p = $(this).scrollTop();  
              
            if(t<=p){//下滚  
                $("#navi").hide();
                console.log($(window).scrollTop());
                console.log($(window).height());  
            }  
              
            else{//上滚  
            	
                $("#navi").show(); 
            }  
            setTimeout(function(){t = p;},0);         
    });   
});   */
</script>
</html>
  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="java.util.*"%>
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
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script src="/Drift_wechat/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<script type='text/javascript' src='/Drift_wechat/js/swiper.js' charset='utf-8'></script>

<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>填写甲醛数值</title>
</head>
<style type="text/css">
	hr.style-one {
		border: 0;	
		height: 1px;	
		background: #333;	
		background-image: linear-gradient(to right, #ccc, #333, #ccc);	
	}
</style>
<body>
	<div id="mainInfo">
		</div>
		<!--用户评论展示  -->
		<div class="weui-cells__title"><h4>评论</h4></div>
	    <div style="margin:10px;background:#FFFFFF;position:relative;">
			<a href="" class="weui-media-box weui-media-box_appmsg">
				<div class="weui-media-box__bd" align="left">
				    <p class="weui-media-box__title" id=comment></p>
				</div>
		   		<div class="weui-media-box__hd">
		          <img class="weui-media-box__thumb" id="img" src=""><a class="pb" ><p>查看大图</p></a>
		        </div>
		    </a>
		</div>
		<hr class="style-one" />
	    <!--填写信息展示-->
	    <div class="weui-cells__title"><h4>甲醛信息</h4></div>
		<div class="weui-form-preview" id="jiaquan">
		</div>
	</div>
</body>

<script type="text/javascript">
	var pbset;
	var pts = [];
	$(".pb").click(function() {
		pbset.open();
		//$("#mainInfo").hide();
	});
	
	//页面加载完成启动
	$("document").ready(function(){
		feedbackPreview();
	});
	
	//获取数据
	function feedbackPreview(){
		$.get("/Drift_wechat/api/FB/getFB",function(json){
			console.log(json);
			var crList = json.crList;
			var userComment = json.userComment;
			console.log(userComment.comment);
			if(typeof(userComment.comment)   !=   "undefined"){
				$("#comment").html(userComment.comment);
			}
			else{
				$("#comment").html("您尚未填写任何评论哦亲");
			}
			
			if(typeof(userComment.picURLS)   !=   "undefined"){
				ptUrls = userComment.picURLS.split(";");
				ptUrls.pop();
				ptUrls.forEach(function(ptUrl){
					var temp = {image:ptUrl};
					pts.push(temp);
				})
				pbset = $.photoBrowser({
			        items: pts,
			        initIndex: 0
			    });
				console.log(ptUrls[0]);
				$("#img").attr('src',ptUrls[0]); 
			}
			else{
				$("#img").attr('src',"/Drift_wechat/images/icon.jpg"); 
			}
			
			if(typeof(crList)   !=   "undefined"){
				var html = '';
				crList.forEach(function(CR,index){
/* 					html += '<div class="weui-form-preview__hd"><label class="weui-form-preview__label">甲醛</label>';
					html += '<em class="weui-form-preview__value">'+CR.num+'</em></div>';
					html += '<div class="weui-form-preview__bd"><div class="weui-form-preview__item"><label class="weui-form-preview__label">位置</label>';
					html += '<span class="weui-form-preview__value">'+CR.num+'</span></div>';
					html += '<div class="weui-form-preview__item"><label class="weui-form-preview__label">面积</label>';
					html += '<span class="weui-form-preview__value">'+CR.location+'m2</span></div></div>'; */
					html+="<div class='weui-form-preview__bd'><div class='weui-form-preview__item'><label class='weui-form-preview__label'>甲醛数值</label>";
					html+="<span class='weui-form-preview__value'>"+CR.num+"</span></div></div>";
					html+="<div class='weui-form-preview__bd'><div class='weui-form-preview__item'><label class='weui-form-preview__label'>面积</label>";
					html+="<span class='weui-form-preview__value'>"+CR.area+"</span></div></div>";
					html+="<div class='weui-form-preview__bd'><div class='weui-form-preview__item'><label class='weui-form-preview__label'>位置</label>";
					html+="<span class='weui-form-preview__value'>"+CR.location+"</span></div></div>";
					html+="<div class='weui-form-preview__ft'></div>";
				});	
				$("#jiaquan").html(html);
			}
		},"json");    
	}
</script>
</html>
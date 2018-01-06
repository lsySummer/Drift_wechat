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
  <!-- set `maximum-scale` for some compatibility issues -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
  <script src="https://as.alipayobjects.com/g/component/fastclick/1.0.6/fastclick.js"></script>
  <script>
    if ('addEventListener' in document) {
      document.addEventListener('DOMContentLoaded', function() {
        FastClick.attach(document.body);
      }, false);
    }
  </script>
  <title>订单完成</title>
</head>
<body>
	<%@include file="TopBar.html"%>
  	<!--结果 -->
	<div class="weui-msg__icon-area" style="margin-top:18%;"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">订单完成</h2>
        </div>
    <!--推荐 -->
    	<div style="margin_top:20%;background:#FFFFFF;">
			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
		      <div class="weui-media-box__hd">
		        <img class="weui-media-box__thumb" src="/Drift_wechat/images/icon.jpg">
		      </div>
		      <div class="weui-media-box__bd">
		        <h4 class="weui-media-box__title">你家的甲醛含量超标么？</h4>
		        <p class="weui-media-box__desc">由各种物质组成的巨型球状天体。</p>
		      </div>
		    </a>
		</div>
		
		<div style="margin_top:10%;background:#FFFFFF;">
			<a href="javascript:void(0);" class="weui-media-box weui-media-box_appmsg">
		      <div class="weui-media-box__hd">
		        <img class="weui-media-box__thumb" src="/Drift_wechat/images/icon.jpg">
		      </div>
		      <div class="weui-media-box__bd">
		        <h4 class="weui-media-box__title">如何清除甲醛呢？</h4>
		        <p class="weui-media-box__desc">由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。
		              由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。由各种物质组成的巨型球状天体，叫做星球。星球有一定的形状，有自己的运行轨道。
		        </p>
		      </div>
		    </a>
		</div>
		
	<!--按钮 -->
	<a href="/Drift_wechat/jsp/TrackMap.jsp" class="weui-btn weui-btn_primary" style="bottom:12%;position:fixed;width:100%;">查看设备历史</a>
  	<!--BOTTOM -->
  	<%@include file="BottomBar.html"%>
	<script>
		set(5);
	</script>
</body>
</html>

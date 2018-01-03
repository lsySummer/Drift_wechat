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
<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script src="/Drift_wechat/js/bootstrap.min.js"></script>
<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	a{color:#696969} 
	a:hover{color:#00BFFF}
</style>  

<body>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:10%;bottom:10%;position:relative;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						甲醛检测仪
					</h4>
				</div>
				<div class="modal-body" align="center">
					 <img  width=80% height=70%  src="/Drift_wechat/images/info.jpg">
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	

<!-- 	<div style="height:70%; width:100%;position:absolute;margin:0px;padding:0px">
		<img width=100% height=100% src="/Drift_wechat/images/info.jpg" title="甲醛漂流仪"/>
	</div> -->
	
	<div style="height:80%;width:100%;background:#D3D3D3;position:absolute;">
	
		<div style="height:50%;margin:10px;background:#00BFFF;position:relative;background-image:url(/Drift_wechat/images/sky.jpg);background-size:cover" align="center">
			<br/>
			<br/>
			<br/>
			<marquee><span style="font-weight: bolder;font-size: 40px;color:#FFFFFF;">甲醛仪累计漂流次数${allnum}次</span></marquee>
			<br/>
			<marquee><span style="font-weight: bolder;font-size: 40px;color:#FFFFFF;">今日漂流次数${todaynum}次</span></marquee>
		</div>
		
		<div style="margin:10px;background:#FFFFFF;position:relative;">
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
		
		<div style="margin:10px;background:#FFFFFF;position:relative;">
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
		
	</div>
	
	<div style="top:2%;left:85%;position:relative;">
		<a href="/Drift_wechat/api/map/map" ><img alt="" src="/Drift_wechat/images/tomap.png" id="countryMap" height="32px" width="32px"></a>
		<br/><a href="javascript:ShowModel()"><img alt="" src="/Drift_wechat/images/yiqi2.png"  height="32px" width="32px"></a>
	</div>
	
	
	<!--主要功能  -->
	<div class="weui-flex" style="top:82%;position: absolute;width:100%;">
	  <div class="weui-flex__item placeholder" style="padding-left:20px;padding-right:10px">
	   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:window.location.href='/Drift_wechat/api/order/set'">我要预约</button>
	  </div>
	  <div class="weui-flex__item placeholder" style="padding-left:10px;padding-right:20px">
	   <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:window.location.href='/Drift_wechat/jsp/Contact';">联系客服</button>
	  </div>
	</div>
	
	<!--导航栏  -->
	<div class="weui-tabbar weui-footer_fixed-bottom" style="bottom:0">
	  <a href="/Drift_wechat/jsp/index2.jsp" class="weui-tabbar__item weui-bar__item--on">
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
	  <a href="/Drift_wechat/jsp/community/CommunityIndex.jsp" class="weui-tabbar__item">
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
</body>
<script type="text/javascript">
 $("#yiqi").click(function() {
     $('#myModal').modal();
 });
  
 function ShowModel(){
 	$('#myModal').modal();
 }
</script>
</html>
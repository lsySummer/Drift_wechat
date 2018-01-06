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
	<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.min.css">
	<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
	<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/city-picker.min.js" charset="utf-8"></script>
    <title>个人信息</title>
  </head>
  
  <body>
    <div class="weui-tab__bd" id="container" style="height:100%;width:100%;margin-top:15%;>
    <div id="tab" class="weui-tab__bd-item weui-tab__bd-item--active">
    	<div style="text-align: center">
			<img alt="" id="image" class="img-circle" src="/Drift_wechat/images/icon.jpg" style="margin: 0 auto;width: 100px; heigth: 100px;" />
		</div>

		<div class="weui-cells">
		  <div class="weui-cell">
		    <div class="weui-cell__hd">
		      <label class="weui-label" style="color:black;font-size:17px;font-weight:bold;">昵称</label>
		    </div>
		    <div id="nickName" class="weui-cell__bd">暂无</div>
		  </div>
		</div>
		<form method="get" id="personDetail" name="personDetail" action="/Drift_wechat/api/user/save">
		<input id="redict" name="redict" value="true" style="display:none;"></input>		
    		<div class="page__bd">
		        <div class="weui-cells weui-cells_form">		            
		            <div class="weui-cell">
		            	<div class="weui-cell__hd">
					      <label class="weui-label" style="color:black;">真实姓名</label>
					    </div>
					    <div class="weui-cell__bd">
					      <input class="weui-input" name="deliveryPerson" id="deliveryPerson" placeholder="请输入收货人姓名">
					    </div>
		            </div>
		            <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label" style="color:black;">手机号</label></div>
					    <div class="weui-cell__bd">
					      <input id="phone" name="phone" class="weui-input" type="tel" pattern="[0-9]*" placeholder="请输入手机号">
					    </div>
					</div>
		        </div>
		    </div>
		    <div class="weui-cells__title" style="color:black;font-size:17px;font-weight:bold;">地址选择</div>   
	        <div class="weui-cells">
	            <div class="weui-cell">
	                <div class="weui-cell__bd">
	                    <input id='city-picker' name="address" class="weui-input" placeholder="选择省市区" required=""/>
	                    <script>
	  						$("#city-picker").cityPicker({
	    					title: "请选择收货地址"
	  						});
						</script>
	                </div>
	            </div>
	            <div class="weui-cell">
	                <div class="weui-cell__bd">
	                    <input id="address_detail" class="weui-input" type="text" name="address_detail" placeholder="请填写可以邮寄给您的详细地址哦" required=""/>
	                </div>
	            </div>
	        </div>
	        <div style="text-align: center" style="bottom:10%;position:fixed;width:100%;">		    	
	          <button type="submit"class="weui-btn weui-btn_primary" id="submit" style="text-align:center">保存个人信息</button>
	        </div>
    	</form>
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
	  <a href="/Drift_wechat/jsp/community/CommunityIndex.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/community.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">社区</p>
	  </a>
	  <a href="/Drift_wechat/jsp/MyIndex.jsp" class="weui-tabbar__item weui-bar__item--on">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/my.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">我的</p>
	  </a>
	</div>
  	</div>
  	</div>
  </body>
  	<script type="text/javascript" src="/Drift_wechat/js/myJS/MyIndex.js"></script>
</html>

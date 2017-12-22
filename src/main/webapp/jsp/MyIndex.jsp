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
   <div class="weui-navbar">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/BaiduMap.jsp">
	    	首页
	    </a>
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Orders.jsp">
	    	我的订单
	    </a> 
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Delivery.jsp">
	    	仪器传递
	    </a>
	  	<a class="weui-navbar__item weui-bar__item--on" href="/Drift_wechat/jsp/MyIndex.jsp">
	    	个人中心
	    </a>
	</div>
    <div class="weui-tab__bd" id="container">
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
	        </br>
	        <div style="text-align: center">		    	
	          <button type="submit"class="weui-btn weui-btn_primary" id="submit" style="text-align:center">保存个人信息</button>
	        </div>
    	</form>
  	</div>
  	</div>
  </body>
  	<script type="text/javascript" src="/Drift_wechat/js/myJS/MyIndex.js"></script>
</html>

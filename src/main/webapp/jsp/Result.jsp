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
	<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
	<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
    <title>申请结果</title>
  </head>
  
  <body>
    <div class="container" id="container">
    <div class="page msg_success js_show">
    <div class="weui-msg">
    
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">操作成功</h2>
            <p class="weui-msg__desc">请加客服微信，方便客服与您联系</p>
        </div>
    		<img src="/Drift_wechat/images/QR_code.jpg" height="200px" width="200px"/>
    		</br>
    	<div class="weui-form-preview" id="item1">
		  <div class="weui-form-preview__bd">
		    <label class="weui-form-preview__label">订单编号</label>
		    <em class="weui-form-preview__value" id="orderId">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">预约开始日期</label>
		      <span class="weui-form-preview__value" id="startDate">暂无</span>
		    </div>
		  </div>
		</div>
    	<a href="/Drift_wechat/api/user/getState" class="weui-btn weui-btn_default" style="bottom:6%;position:fixed;width:100%;">返回</a>
    </div>
    </div>
    <div id="image" class="weui-popup__container popup-bottom">
  		<div class="weui-popup__overlay"></div>
  			<div class="weui-popup__modal">
    			<img src="/Drift_wechat/images/QR_code.jpg" align="middle" height="150px" width="150px"/>
 			</div>
		</div>
	</div>
        <div class="weui-footer weui-footer_fixed-bottom">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
        </div>
	<script>
		$.getJSON('/Drift_wechat/api/order/get',function(json){
			var data = json.data;
			document.getElementById('orderId').innerHTML=data[data.length-1].id.slice(-12);
			document.getElementById('startDate').innerHTML=data[data.length-1].startDate;
		});
	</script>
  </body>
</html>

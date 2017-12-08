<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">  
    <title>填写快递</title>
  </head>
  
  <body>
  	<header class='demos-header'>
      <h2 class="demos-title">快递填写</h2>
    </header>
    <div class="container" id="container">
    	<form method="get" id="deliveryWrite" name="deliveryWrite" action="/Drift_wechat/api/delivery/set">
    		<div class="page__bd">
		        <div class="weui-cells__title">快递详情</div>
		        <div class="weui-cells weui-cells_form">
		            <!-- <div class="weui-cell">
		                <div class="weui-cell__hd"><label for="" class="weui-label">发货日期</label></div>
		                <div class="weui-cell__bd">
		                    <input id="startDate" name="deliveryDate" id="deliveryDate" class="weui-input" type="date" value="" required=""/>
		                </div>
		            </div> -->
		            <div class="weui-cell">
		            	<div class="weui-cell__hd">
					      <label class="weui-label">快递单号</label>
					    </div>
					    <div class="weui-cell__bd">
					      <input class="weui-input" name="deliveryNum" id="deliveryNum" type="number" placeholder="请输入快递单号">
					    </div>
		            </div>
		            <!-- <div class="weui-cell">
		            	<div class="weui-cell__hd">
					      <label class="weui-label">收货人</label>
					    </div>
					    <div class="weui-cell__bd">
					      <input class="weui-input" name="deliveryPerson" id="deliveryPerson" placeholder="请输入收货人姓名">
					    </div>
		            </div> -->
		        </div>
		    </div>
		    <div class="weui-btn-area">
	            <button class="weui-btn weui-btn_primary" type="submit" id="submit">确定并发布感受</button>
	        </div>
    	</form>
	</div>
    <div class="weui-msg__extra-area">
        <div class="weui-footer">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
        </div>
  	</div>
  </body>
  <script type="text/javascript">
		$("#submit").click(function(){
			if($("#deliveryNum").val().trim().length){
					$('#deliveryWrite').submit();
			}else{
				$.toptip('操作失败，请确保所有内容均已填写', 'error');
				return false;
			}
		})
  </script>
</html>

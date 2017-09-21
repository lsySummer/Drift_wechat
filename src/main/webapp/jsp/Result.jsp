<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">  
    <title>申请结果</title>
  </head>
  
  <body>
    <div class="container" id="container">
    <div class="page msg_success js_show">
    <div class="weui-msg">
    
        <div class="weui-msg__icon-area"><i class="weui-icon-success weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">操作成功</h2>
            <p class="weui-msg__desc">请加微信XXXXX，会有客服与您联系<a href="javascript:void(0);">文字链接</a></p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a href="javascript:history.back();" class="weui-btn weui-btn_primary">添加微信</a>
                <a href="javascript:history.back();" class="weui-btn weui-btn_default">返回首页</a>
            </p>
        </div>
    </div>
    </div>
</div>
    <div class="page_bd page_bd_spacing">
  	<div class="weui-footer">
  		<p class="weui-footer_text">Copyright © 2017-2020 GuoMai</p>
  	</div>
    </div>

  </body>
</html>

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
	<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
    <title>联系客服</title>
  </head>
  
  <body>
    <div class="container" id="container">
    <div class="page msg_warn js_show">
    <div class="weui-msg">
        <div class="weui-msg__icon-area"><i class="weui-icon-info weui-icon_msg"></i></div>
        <div class="weui-msg__text-area">
            <h2 class="weui-msg__title">甲醛仪流转客服</h2>
            <img src="/Drift_wechat/images/QR_code.jpg" height="200px" width="200px"/>
            <p class="weui-msg__desc">长按识别图中二维码，联系客服。</p>
        </div>
        <div class="weui-msg__opr-area">
            <p class="weui-btn-area">
                <a id="test" href="/Drift_wechat/api/wechat/center" class="weui-btn weui-btn_primary">回到首页</a>
            </p>
        </div>
        <div class="weui-msg__extra-area" >
            <div class="weui-footer weui-footer_fixed-bottom">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
            </div>
        </div>
    </div>
</div>
</div>

  </body>
</html>


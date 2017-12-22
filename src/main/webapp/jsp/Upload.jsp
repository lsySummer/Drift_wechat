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
    <title>图片上传</title>
  </head>
  
 <body>
 <div class="weui-cells__title">甲醛数值</div>
 				<div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <input id="methanal" name="methanal" class="weui-input" type="number" placeholder="毫克/立方米">
                        </div>
                    </div>
                </div>
 <div class="weui-cells__title">使用心得</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <textarea id="txt_des" name="txt" class="weui-textarea" placeholder="说点什么吧" rows="3"></textarea>
                            <div class="weui-textarea-counter"><span id="des_count">0</span>/140</div>
                        </div>
                    </div>
                </div>
  <p class="weui-cells__title">图片上传</p>
            <div class="weui-cells weui-cells_form" id="uploaderCustom">
                <div class="weui-cell">
                    <div class="weui-cell__bd">
                        <div class="weui-uploader">
                            <div class="weui-uploader__hd">
                                <p class="weui-uploader__title">图片上传</p>
                                <div class="weui-uploader__info"><span id="uploadCount">0</span>/5</div>
                            </div>
                            <div class="weui-uploader__bd">
                                <ul class="weui-uploader__files" id="uploaderCustomFiles"></ul>
                                <div class="weui-uploader__input-box">
                                    <input id="uploaderCustomInput" name="photo" class="weui-uploader__input" type="file" accept="image/*" multiple="">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="weui-btn-area">
                <a id="uploaderCustomBtn" href="javascript:" class="weui-btn weui-btn_primary">上传</a>
            </div>
  <script type="text/javascript" src="/Drift_wechat/js/myJS/Upload.js"></script>
</body>
</html>

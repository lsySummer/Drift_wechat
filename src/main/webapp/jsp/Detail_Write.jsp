<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="../css/weui.min.css">
<script type="text/javascript" src="../js/weui.min.js"></script>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">
    <title>Write your details here</title>
  </head>
  
  <body>
    <div class="container" id="container">
    <div class="page__hd">
        <h1 class="page__title">请输入基本信息</h1>
    </div>
    <div class="page__bd">
     
        <div class="weui-cells__title">复选列表项</div>
        <div class="weui-cells weui-cells_checkbox">
            <label class="weui-cell weui-check__label" for="s11">
                <div class="weui-cell__hd">
                    <input type="checkbox" class="weui-check" name="checkbox1" id="s11" checked="checked">
                    <i class="weui-icon-checked"></i>
                </div>
                <div class="weui-cell__bd">
                    <p>Selection 1.</p>
                </div>
            </label>
            <label class="weui-cell weui-check__label" for="s12">
                <div class="weui-cell__hd">
                    <input type="checkbox" name="checkbox1" class="weui-check" id="s12">
                    <i class="weui-icon-checked"></i>
                </div>
                <div class="weui-cell__bd">
                    <p>Selection 2.</p>
                </div>
            </label>
        </div>

        <div class="weui-cells__title">具体信息</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_warn">
                <div class="weui-cell__hd"><label for="" class="weui-label">微信号</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" pattern="[0-9]*" value="weui input error" placeholder="请输入微信号">
                </div>
                <div class="weui-cell__ft">
                    <i class="weui-icon-warn"></i>
                </div>
            </div>
            <div class="weui-cell">
                <div class="weui-cell__hd"><label for="" class="weui-label">日期</label></div>
                <div class="weui-cell__bd">
                    <input class="weui-input" type="date" value="">
                </div>
            </div>
        </div>

        <div class="weui-cells__title">开关</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">是否接受产品推荐</div>
                <div class="weui-cell__ft">
                    <input class="weui-switch" type="checkbox">
                </div>
            </div>
        </div>

        <div class="weui-cells__title">文本框</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" type="text" placeholder="请输入文本">
                </div>
            </div>
        </div>

        <div class="weui-cells__title">输入手机号</div>
        <div class="weui-cells">
            <div class="weui-cell weui-cell_select weui-cell_select-before">
                <div class="weui-cell__bd">
                    <input class="weui-input" type="number" pattern="[0-9]*" placeholder="请输入号码">
                </div>
            </div>
        </div>
        
        <label for="weuiAgree" class="weui-agree">
            <input id="weuiAgree" type="checkbox" class="weui-agree__checkbox">
            <span class="weui-agree__text">
                阅读并同意<a href="javascript:void(0);">《相关条款》</a>
            </span>
        </label>

        <div class="weui-btn-area">
            <a class="weui-btn weui-btn_primary" href="jsp/Result.jsp" id="showTooltips">确定并绑定芝麻信用</a>
        </div>
    </div>
    <div class="page__ft">
        <a href="javascript:home()"><img src="./images/icon_footer_link.png"></a>
    </div>
</div>
  <div class="page_bd page_bd_spacing">
  	<div class="weui-footer">
  		<p class="weui-footer_text">Copyright © 2017-2020 GuoMai</p>
  	</div>
  </div>
  </body>
</html>

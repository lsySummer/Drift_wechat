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
    <title>预定信息</title>
  </head>
  
  <body>
    <div class="container" id="container">
    <div class="page__hd">
        <h1 class="page__title">请输入预定信息</h1>
    </div>
    <div class="page__bd">
     <form method="get" id="detail" name="detail" action="/Drift_wechat/api/zmxy/reserve">
        <div class="weui-cells__title">性别</div>
        <div class="weui-cells weui-cells_checkbox">
            <label class="weui-cell weui-check__label" for="s11">
                <div class="weui-cell__hd">
                    <input id="sex" name="sex" type="checkbox" class="weui-check" name="checkbox1" id="s11" checked="checked">
                    <i class="weui-icon-checked"></i>
                </div>
                <div class="weui-cell__bd">
                    <p>男.</p>
                </div>
            </label>
            <label class="weui-cell weui-check__label" for="s12">
                <div class="weui-cell__hd">
                    <input type="checkbox" name="checkbox1" class="weui-check" id="s12">
                    <i class="weui-icon-checked"></i>
                </div>
                <div class="weui-cell__bd">
                    <p>女.</p>
                </div>
            </label>
        </div>

        <div class="weui-cells__title">预定信息</div>
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell">
                <div class="weui-cell__hd"><label for="" class="weui-label">起始日期</label></div>
                <div class="weui-cell__bd">
                    <input id="startDate" name="startDate" class="weui-input" type="date" value="">
                </div>
            </div>
        
            <div class="weui-cell">
                <div class="weui-cell__hd"><label for="" class="weui-label">结束日期</label></div>
                <div class="weui-cell__bd">
                    <input id="endDate" name="endDate" class="weui-input" type="date" value="">
                </div>
        </div> 
        </div>

        <div class="weui-cells__title">详细地址</div>   
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input id="address" name="address" class="weui-input" type="text" placeholder="请输入具体地址">
                </div>
            </div>
        </div>
        
        <div class="weui-cells weui-cells_form">
            <div class="weui-cell weui-cell_switch">
                <div class="weui-cell__bd">是否接受产品推荐</div>
                <div class="weui-cell__ft">
                    <input id="recommand" name="recommand" class="weui-switch" type="checkbox">
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
            <a class="weui-btn weui-btn_primary" href="jsp/Result.jsp" id="showTooltips">确定并提交</a>
        </div>
      </form>
    </div>
    
    <div class="page__ft">
        <a href="javascript:home()"><img src="./images/icon_footer_link.png"></a>
    </div>
</div>
  <div class="page_bd page__bd_spacing">
  	<div class="weui-footer">
  		<p class="weui-footer_text">Copyright © 2017-2020 GuoMai</p>
  	</div>
  </div>
  </body>
</html>

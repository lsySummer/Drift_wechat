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
    <title>Drift</title>
  </head> 
 <body>
  <script type="text/javascript">
    weui.alert('欢迎进入甲醛检测仪漂流系统');
  </script>
  <div class="container" id="container">
   <div class="page_hd">
    <h1 class="page_title">公益甲醛检测仪漂流预报名系统</h1>
   </div>
   <div class="page_bd">
   <article class="weui-article">
    <section>
    	<h2>1.1 活动目的</h2>
        <section>
            <p>Test</p>
        </section>
        <h2>1.2 活动须知</h2>
        <section>
            <p>Test</p>
        </section>
    </section>
   </article>
   </div>
   <form method="get" id="basic" name="basic" action="/Drift_wechat/api/zmxy/register">
     <div class="weui-cells__title">手机号</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" id="phone" name="phone" type="tel" required pattern="[0-9]{11}" placeholder="输入你现在的手机号" emptyTips="请输入手机号" notMatchTips="请输入正确的手机号">
                </div>
            </div>
        </div>
       <div class="weui-cells__title">姓名</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" id="name" name="name" type="text" placeholder="请输入姓名">
                </div>
            </div>
        </div>
     <div class="page__bd page__bd_spacing">
      <button type="submit" class="weui-btn weui-btn_primary">授权芝麻信用</button>
   	 </div>
   </form>
  </div>
  <div class="page_bd page__bd_spacing">
  	<div class="weui-footer">
  		<p class="weui-footer_text">Copyright © 2017-2020 GuoMai</p>
  	</div>
  </div>
 </body>
</html>

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
    <title>Drift_wechat</title>
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
   <div class="page__bd page__bd_spacing">
     <a href="Detail_Write.jsp" class="weui-btn weui-btn_primary">我要报名</a>
   </div>
  </div>
  <div class="page_bd page_bd_spacing">
  	<div class="weui-footer">
  		<p class="weui-footer_text">Copyright © 2017-2020 GuoMai</p>
  	</div>
  </div>
 </body>
</html>

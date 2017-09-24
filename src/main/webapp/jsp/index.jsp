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
    <h1 class="page_title" align="center">公益共享甲醛检测仪</h1>
    <h1 class="page_title" align="center">预报名系统</h1>
   </div>
   <div class="page_bd">
   <article class="weui-article">
    <section>
        <section>
            <img src="/Drift_wechat/images/product.png" height="150px" width="350px"/>
        </section>
        <h2>&bull;&nbsp;产品特点</h2>
        <section>
            <p>以ppm和mg/m3为单位显示甲醛浓度</p>
            <p>单键操作，能够快速采样。快速恢复时间</p>
            <p>可存储数据并与电脑连接，输出数据</p>
            <p>不受高湿度和温度影响，具备补偿功能</p>
        </section>
        <h2>&bull;&nbsp;活动原因</h2>
        <section>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;淘宝售价1万多，当然您搜索甲醛检测仪时会出现更多销量过千售价几百这样的检测仪器
				看到这里您应该能想明白为何专业的价格会这么高了，所以我们只选最专业的仪器，来保证大家的检测结果精准
			</p>
        </section>
        <h2>&bull;&nbsp;活动方式</h2>
        <section>
            <p>仪器在您与下家之间传递</p>
            <p>需使用顺丰快递并保价</p>
            <p>13-25元快递费+50元保价费</p>
            <p>免押金和使用费，但需绑定芝麻信用</p>
        </section>
    </section>
   </article>
   </div>
   <form method="get" id="basic" name="basic" action="/Drift_wechat/api/zmxy/register">
     <div class="weui-cells__title">手机号</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" id="phone" name="phone" type="tel" required pattern="[0-9]{11}" placeholder="输入您与芝麻信息绑定的手机号" emptyTips="请输入手机号" notMatchTips="请输入正确的手机号">
                </div>
            </div>
        </div>
       <div class="weui-cells__title">姓名</div>
        <div class="weui-cells">
            <div class="weui-cell">
                <div class="weui-cell__bd">
                    <input class="weui-input" id="name" name="name" type="text" placeholder="请输入姓名" required/>
                </div>
            </div>
        </div>
     <div class="page__bd page__bd_spacing">
      <button type="submit" class="weui-btn weui-btn_primary">授权芝麻信用</button>
   	 </div>
   </form>
  </div>
        <div class="weui-footer">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
        </div>
 </body>
</html>

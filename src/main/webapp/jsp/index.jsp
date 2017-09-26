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
    <title>Drift</title>
  </head> 
 <body ontouchstart>
  <script type="text/javascript">
    weui.alert('欢迎进入甲醛检测仪漂流系统');
  </script>
  	<header class='demos-header'>
      <h2 class="demos-title">共享甲醛检测仪</h2>
      <p class='demos-sub-title'>预报名系统</p>
      </br>
      <img src="/Drift_wechat/images/product.png" width="340px" height="160px"/>
    </header>
   <div class="weui-grids">
      <a href="javascript:part1()" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="/Drift_wechat/images/icon_nav_article.png" alt="">
        </div>
        <p class="weui-grid__label">
          产品特点
        </p>
      </a>
      <a href="javascript:part2()" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="/Drift_wechat/images/icon_nav_button.png" alt="">
        </div>
        <p class="weui-grid__label">
          活动原因
        </p>
      </a>
      <a href="javascript:part3()" class="weui-grid js_grid">
        <div class="weui-grid__icon">
          <img src="/Drift_wechat/images/icon_nav_cell.png" alt="">
        </div>
        <p class="weui-grid__label">
          活动方式
        </p>
      </a>
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
                    <input class="weui-input" id="name" name="name" type="text" placeholder="请输入姓名"/>
                </div>
            </div>
        </div>
     <div class="weui-btn-area">
      <button type="submit" id="auth" name="auth" class="weui-btn weui-btn_primary" onclick=verity>授权芝麻信用</button>
   	 </div>
   </form>
        <div class="weui-footer">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
        </div>
   <script type="text/javascript">
		$('#auth').click(function(){
			if($("#name").val().trim().length && $("#phone").val().trim().length){
				$.toptip('操作成功', 'success');
				$('#basic').submit();
			}else{
				$.toptip('操作失败，请确保所有内容均已填写', 'error');
				return false;
			}
		})
		function part1(){
			$.notification({
				  title: "活动原因",
				  text: "单键操作，能够快速采样。快速恢复时间;可存储数据并与电脑连接，输出数据;不受高湿度和温度影响，具备补偿功能。",
			});
		}
		function part2(){
			$.notification({
				  title: "产品特点",
				  text: "淘宝售价1万多，当然您搜索甲醛检测仪时会出现更多销量过千售价几百这样的检测仪器。看到这里您应该能想明白为何专业的价格会这么高了。",
			});
		}
		function part3(){
			$.notification({
				  title: "活动方式",
				  text: "仪器在您与下家之间传递;13-25元快递费+50元保价费;免押金和使用费，但需绑定芝麻信用。",
			});
		}
	</script>
 </body>
</html>

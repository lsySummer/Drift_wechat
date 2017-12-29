<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
  <link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
  <link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
  <script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
  <script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
  <!-- set `maximum-scale` for some compatibility issues -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
  <script src="https://as.alipayobjects.com/g/component/fastclick/1.0.6/fastclick.js"></script>
  <script>
    if ('addEventListener' in document) {
      document.addEventListener('DOMContentLoaded', function() {
        FastClick.attach(document.body);
      }, false);
    }
  </script>
</head>
<body>
	<!--模态框 -->
	<div id="about" class="weui-popup__container">
	  <div class="weui-popup__overlay"></div>
	  <div class="weui-popup__modal">
	 <h1> 快递查询 </h1>
	  <table class="table table-hover" cellspacing="8">
			  <thead>
			    <tr>
			      <th width="30%">时间</th>
			      <th width="70%">消息</th>
			    </tr>
			  </thead>
			  <tbody id="lists">
			  </tbody>
			</table>
	    <a href="javascript:close();" class="weui-btn weui-btn_primary">关闭</a>
	  </div>
	</div>
	<!--步骤条 -->
	<%@include file="TopBar.html"%>
  	<!--信息 -->
	<div class="weui-form-preview" style="margin-top:30%;">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">下家名称</label>
		    <em class="weui-form-preview__value" id="next">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">联系电话</label>
		      <span class="weui-form-preview__value" id="phontNum">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">邮寄地址</label>
		      <span class="weui-form-preview__value" id="address">暂无</span>
		    </div>
			<div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum2">暂无</span>
		    </div>
		  </div>
		  <div class="weui-form-preview__ft">
		  	<a id="d2" class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:query(2);">快递查询</a>
    		<button id="detail" class="weui-form-preview__btn weui-form-preview__btn_primary" value="true" onclick="javascrtpt:detail();">填写快递信息</button>
  		  </div>
	</div>
  	<!--导航栏  -->
	<div class="weui-tabbar">
	  <a href="/Drift_wechat/jsp/BaiduMap.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/index.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">首页</p>
	  </a>
	  <a href="/Drift_wechat/jsp/Orders.jsp" class="weui-tabbar__item weui-bar__item--on">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/order.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">订单</p>
	  </a>
	  <a href="/Drift_wechat/jsp/community/CommunityIndex.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/community.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">社区</p>
	  </a>
	  <a href="/Drift_wechat/jsp/MyIndex.jsp" class="weui-tabbar__item">
	    <div class="weui-tabbar__icon">
	      <img src="/Drift_wechat/images/navi/my.png" alt="">
	    </div>
	    <p class="weui-tabbar__label">我的</p>
	  </a>
	</div>
	<script>set(4);</script>
</body>
</html>

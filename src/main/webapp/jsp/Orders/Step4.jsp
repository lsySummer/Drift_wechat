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
  <script type="text/javascript" src="/Drift_wechat/js/myJS/stateJump.js"></script>
  <!-- set `maximum-scale` for some compatibility issues -->
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no" />
  <title>寄送下家</title>
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
	    <a id="close" href="javascript:close();" class="weui-btn weui-btn_primary" style="position:fiexd;bottom:10%;z-index:10">关闭</a>
	  </div>
	</div>
	<!--步骤条 -->
	<%@include file="TopBar.html"%>
  	<!--信息 -->
  	<i class="weui-icon-waiting weui-icon_msg" style="margin-top:15%;"></i>
	<div class="weui-form-preview" style="margin-top:15%;">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">下家名称</label>
		    <em class="weui-form-preview__value" id="next">抽取中～</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">联系电话</label>
		      <span class="weui-form-preview__value" id="phontNum">疯狂打call</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">邮寄地址</label>
		      <span class="weui-form-preview__value" id="address">外太空</span>
		    </div>
			<div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum2">下家已经等不及了～</span>
		    </div>
		  </div>
		  <div class="weui-form-preview__ft" style="margin-bottom:70px;">
    		<button id="detail" class="weui-form-preview__btn weui-form-preview__btn_primary" value="true" onclick="javascrtpt:window.location.href='/Drift_wechat/jsp/DeliveryWrite.jsp';">填写快递信息</button>
  		  </div>
	</div>
  	<!--BOTTOM -->
  	<%@include file="BottomBar.html"%>
	<script type="text/javascript" src="/Drift_wechat/js/myJS/Step4.js"></script>
	<script>
		set(4);
		stateCheck(4);
	</script>
</body>
</html>
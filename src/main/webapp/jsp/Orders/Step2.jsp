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
  <title>等待收货</title>
</head>
<body>
	<!--步骤条 -->
	<%@include file="TopBar.html"%>
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
	<!--订单信息 -->
	    <div class="weui-form-preview" id="item1" style="margin-top:5%;">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">订单编号</label>
		    <em class="weui-form-preview__value" id="orderId">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">预约开始日期</label>
		      <span class="weui-form-preview__value" id="startDate">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">预约结束日期</label>
		      <span class="weui-form-preview__value" id="endDate">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">送货地址</label>
		      <span class="weui-form-preview__value" id="address">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">收件人</label>
		      <span class="weui-form-preview__value" id="userName">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">联系电话</label>
		      <span class="weui-form-preview__value" id="userPhone">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">设备状态</label>
		      <span class="weui-form-preview__value" id="state">暂无</span>
		    </div>
		  </div>
		</div>
  	<!--快递信息 -->
    	<div class="weui-form-preview" id="item1" style="margin-top:5%;">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">上家名称</label>
		    <em class="weui-form-preview__value" id="previous">上家正在抽取中……</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum1">更新了会告诉你的～</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">设备id</label>
		      <span class="weui-form-preview__value" id="deviceId1">可能有变动哦</span>
		    </div>
		  </div>
		  <div class="weui-form-preview__ft" style="bottom:10%;position:fixed;width:100%;">
		  	<a id="d1" class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:query(1);">快递查询</a>
    		<button id="confirm" class="weui-form-preview__btn weui-form-preview__btn_primary" value="true" onclick="javascript:confirm();">确认收货</button>
  		  </div>
		</div>
  	<!--BOTTOM -->
  	<%@include file="BottomBar.html"%>
	<script type="text/javascript" src="/Drift_wechat/js/myJS/Step2.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/myJS/Orders.js"></script>
</body>
</html>

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
<script type="text/javascript" src="/Drift_wechat/js/myJS/Delivery.js"></script>
<html>
	<script>
	function confirm(){
		if(document.getElementById('confirm').value == "true"){
			$.get('/Drift_wechat/api/delivery/confirm');
			$.toast("收货成功");
		}else{
			$.toast("暂时无法收货", "forbidden");
		}
	}
	function detail(){
		if(document.getElementById('detail').value == "true"){
			window.location.href='/Drift_wechat/jsp/DeliveryWrite.jsp';
		}else{
			$.toast("暂时无法填写", "forbidden");
		}
	}
	</script>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">  
    <title>仪器传递</title>
  </head>
  
  <body>
  	<!-- <div class="weui-tab"> -->
  	<div class="weui-navbar">
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/BaiduMap.jsp">
	    	首页
	    </a>
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/Orders.jsp">
	    	我的订单
	    </a> 
	  	<a class="weui-navbar__item weui-bar__item--on" href="/Drift_wechat/jsp/Delivery.jsp">
	    	仪器传递
	    </a>
	  	<a class="weui-navbar__item" href="/Drift_wechat/jsp/MyIndex.jsp">
	    	个人中心
	    </a>
	</div>
	
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
	</br>
	<div class="weui-tab__bd">
    <div class="weui-tab__bd-item weui-tab__bd-item--active" id="container">
      </br>
    	<div class="weui-form-preview" id="item1">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">上家名称</label>
		    <em class="weui-form-preview__value" id="previous">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum1">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">设备id</label>
		      <span class="weui-form-preview__value" id="deviceId1">暂无</span>
		    </div>
		  </div>
		  <div class="weui-form-preview__ft">
		  	<a id="d1" class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:query(1);">快递查询</a>
    		<button id="confirm" class="weui-form-preview__btn weui-form-preview__btn_primary" value="true" onclick="javascript:confirm();">确认收货</button>
  		  </div>
		</div>
		</br>
		<div class="weui-form-preview">
		  <div class="weui-form-preview__hd">
		    <label class="weui-form-preview__label">下家名称</label>
		    <em class="weui-form-preview__value" id="next">暂无</em>
		  </div>
		  <div class="weui-form-preview__bd">
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">快递单号</label>
		      <span class="weui-form-preview__value" id="deliveryNum2">暂无</span>
		    </div>
		    <div class="weui-form-preview__item">
		      <label class="weui-form-preview__label">设备id</label>
		      <span class="weui-form-preview__value" id="deviceId2">暂无</span>
		    </div>
		  </div>
		  <div class="weui-form-preview__ft">
		  	<a id="d2" class="weui-form-preview__btn weui-form-preview__btn_default" href="javascript:query(2);">快递查询</a>
    		<button id="detail" class="weui-form-preview__btn weui-form-preview__btn_primary" value="true" onclick="javascrtpt:detail();">填写快递信息</button>
  		  </div>
		</div>
	</div>
	</div>
	</div>
  </body>
  <script type="text/javascript">
  function query(x){
  	var delivery = '';
  	if(x == 1){
  		delivery = document.getElementById("deliveryNum1").innerHTML;
  	}else{
  		delivery = document.getElementById("deliveryNum2").innerHTML;
  	}
  	if(delivery == '暂无物流信息'){
  		$.toast("暂无信息，无法查询", "forbidden");
  	}else{
  		$.ajax({
		  url:"http://jisukdcx.market.alicloudapi.com/express/query?number="+ delivery +"&type=auto",
		  type:"get",
		  dataType:"json",
		  data:"hello world",
		  headers: {'Content-Type': 'application/json',
			  		'Authorization':'APPCODE f4726618e0cd48249485fdc44286e869'	
		  },
		  success: function (res) {
		  	if(res.status == '0'){
			  	var temp = res.result;
			    var list = temp.list;
			    var result = "";
			    list.forEach(function(item){
			    	result += '<tr>';
			    	var temp = item.time.split(" ");
			    	result += '<td>' + temp[0] + '<br>' + temp[1] +'</td>';
			    	result += '<td>' + item.status +'</td>';
			    	result += '</tr>';
			    });
			    document.getElementById('lists').innerHTML = result;
		  	}else{
		  		document.getElementById('lists').innerHTML = res.msg;
		  	}
		  }
		});
		$('#about').popup();
  	}
	}
	function close(){$.closePopup();}
  </script>
</html>

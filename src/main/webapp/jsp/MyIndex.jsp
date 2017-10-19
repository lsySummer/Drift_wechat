<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/city-picker.min.js" charset="utf-8"></script>
<html>
  <head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">  
    <title>个人信息</title>
  </head>
  
  <body>
    <div class="container" id="container">
    	<div style="text-align: center">
			<img alt="" class="img-circle" src="/Drift_wechat/images/icon.jpg" style="margin: 0 auto;width: 100px; heigth: 100px;" />
		</div>
		</br>
		<div style="text-align: center">
			<a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_default" style="text-align:center">绑定芝麻信用</a>
		</div>
		<div class="weui-cells">
		  <div class="weui-cell">
		    <div class="weui-cell__bd">
		      <p>昵称</p>
		    </div>
		    <div id="nickName" class="weui-cell__ft">暂无</div>
		  </div>
		</div>
		<form method="get" id="personDetail" name="personDetail" action="/Drift_wechat/api/delivery/set">		
    		<div class="page__bd">
		        <div class="weui-cells weui-cells_form">		            
		            <div class="weui-cell">
		            	<div class="weui-cell__hd">
					      <label class="weui-label">真实姓名</label>
					    </div>
					    <div class="weui-cell__bd">
					      <input class="weui-input" name="deliveryPerson" id="deliveryPerson" placeholder="请输入收货人姓名">
					    </div>
		            </div>
		            <div class="weui-cell">
					    <div class="weui-cell__hd"><label class="weui-label">手机号</label></div>
					    <div class="weui-cell__bd">
					      <input id="phone" name="phone" class="weui-input" type="tel" pattern="[0-9]*" placeholder="请输入手机号">
					    </div>
					</div>
		        </div>
		    </div>
		    <div class="weui-cells__title">地址选择</div>   
	        <div class="weui-cells">
	            <div class="weui-cell">
	                <div class="weui-cell__bd">
	                    <input id='city-picker' name="address" class="weui-input" placeholder="选择省市区" required=""/>
	                    <script>
	  						$("#city-picker").cityPicker({
	    					title: "请选择收货地址"
	  						});
						</script>
	                </div>
	            </div>
	            <div class="weui-cell">
	                <div class="weui-cell__bd">
	                    <input id="address_detail" class="weui-input" type="text" name="address_detail" placeholder="请填写可以邮寄给您的详细地址哦" required=""/>
	                </div>
	            </div>
	        </div>
	        </br>
	        <div style="text-align: center">		    	
	          <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary" style="text-align:center">修改个人信息</a>
	        </div>
    	</form>
  	</div>
	<script type="text/javascript">
		$("#submit").click(function(){
			if($("#deliveryPerson").val().trim().length && $("phone").val().trim().length && $("address_detail").val().trim().length && $("address").val().trim().length){
					$('#personDetail').submit();
			}else{
				$.toptip('操作失败，请确保所有内容均已填写', 'error');
				return false;
			}
		})
  	</script>
  </body>
</html>

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
    <base href="<%=basePath%>">  
    <title>预约日期</title>
  </head>
  
  <body>
  	<header class='demos-header'>
      <h2 class="demos-title">产品预约</h2>
    </header>
    <div class="container" id="container">
    	<form method="get" id="DateChoose" name="DateChoose" action="/Drift_wechat/api/order/date">
    		<div class="page__bd">
    			<div class="weui-cells__title">已分配设备</div>
    			<div class="weui-cells">
				  <div class="weui-cell">
				    <div class="weui-cell__bd">
				      <p>设备编号</p>
				    </div>
				    <div class="weui-cell__ft" id="deviceNum">暂无</div>
				  </div>
				</div>
		        <div class="weui-cells__title">预约使用</div>
		        <div class="weui-cells weui-cells_form">
		            <div class="weui-cell">
		                <div class="weui-cell__hd"><label for="" class="weui-label">选择时间</label></div>
		                <div class="weui-cell__bd">
		                    <input id="startDate" name="startDate" class="weui-input" value="" required=""/>
		                    <script type="text/javascript">
		                    	$.getJSON('/Drift_wechat/api/order/getDate',function(json){
		                    		var date = json.data;
		                    		document.getElementById("deviceNum").innerHTML = json.number;
		                    		document.getElementById("startDate").value = data[0];
		                    		$("#startDate").picker({
									  title: "请选择预约日期",
									  cols: [
									    {
									      textAlign: 'center',
									      values: data
									    }
									  ]
									});
		                    	})
							</script>
		                </div>
		            </div>
		        </div>
		    </div>
		    <div class="weui-btn-area">
	            <button class="weui-btn weui-btn_primary" type="submit" id="submit">确定并提交</button>
	        </div>
    	</form>
	</div>
    <div class="weui-msg__extra-area">
        <div class="weui-footer">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
        </div>
  	</div>
  </body>
  <script type="text/javascript">
		$("#submit").click(function(){
			if($("#startDate").val().trim().length){
					$('#DateChoose').submit();
			}else{
				$.toptip('操作失败，请确保所有内容均已填写', 'error');
				return false;
			}
		});
  </script>
</html>

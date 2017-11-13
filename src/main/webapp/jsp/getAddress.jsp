<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
	    wx.config({
	        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	        appId: 'wx80e3eed8e26e852f', // 必填，企业号的唯一标识，此处填写企业号corpid
	        timestamp: parseInt("<%=session.getAttribute("timestamp")%>",10), // 必填，生成签名的时间戳
	        nonceStr: "<%=session.getAttribute("noncestr")%>", // 必填，生成签名的随机串
	        signature: "<%=session.getAttribute("signature")%>",// 必填，签名，见附录1
	        jsApiList: ['getLocation'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
	    });
	    wx.ready(function(){
	    });
	 
	    wx.error(function(res){
	    });
	    
	    <button id="getBBS" style="width:1000px;height:600px;font-size:150px;" onclick="submitOrderInfoClick();">获取地理位置</button>
	 	<script type="text/javascript">
		function submitOrderInfoClick(){
		  wx.getLocation({
		        success: function (res) {
		            alert("小宝鸽获取地理位置成功，经纬度为：（" + res.latitude + "，" + res.longitude + "）" );
		        },
		        fail: function(error) {
		            AlertUtil.error("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
		        }
		    });
		}
	</script>
</head>
<body>

</body>
</html>
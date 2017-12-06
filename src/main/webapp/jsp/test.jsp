<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script>
	$.getJSON("http://measure.qingair.net/management/deviceAddress/all&jsoncallback=?",function(json){
		console.log(json)
	});
</script>
</html>
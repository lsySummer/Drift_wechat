<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>甲醛检测后台系统</title>
	<meta http-equiv="description" content="This is a login page">
	<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/bootstrap.min.js"></script>
  </head>
  
  <body style="background-image:url(/Drift_wechat/images/background.jpg);background-size:cover;">
  <div id="result" style="visibility:hidden;"><div class="alert alert-success" role="alert">登录成功</div></div>
    <form class="form-horizontal" action="" style="margin:15% 25% 20% 20%;padding:auto;width:50%" id="form">
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label" style="color:white">用户名</label>
	    <div class="col-sm-10">
	      <input type="text" class="form-control" id="username" placeholder="Username">
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputPassword3" class="col-sm-2 control-label" style="color:white">密码</label>
	    <div class="col-sm-10">
	      <input type="password" class="form-control" id="password" placeholder="Password">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button id="login" class="btn btn-default" type="submit">登录</button>
	    </div>
	  </div>
	</form>
	<script>
		$("#form").on("submit", function() {
			var username = document.getElementById("username").value;
			var password = document.getElementById("password").value;
			$.getJSON('/Drift_wechat/api/manage/login?username='+username+'&password='+password,function(json){
				document.getElementById('result').style.visibility = 'visible';
				if(json.status == '200'){
					document.getElementById('result').innerHTML = '<div class="alert alert-success" role="alert">登录成功</div>';
					setTimeout("window.location.href='/Drift_wechat/api/manage/orderList?page=1'", 1000);
				}else{
					document.getElementById('result').innerHTML = '<div class="alert alert-danger" role="alert" id="alert">用户名或密码错误</div>';
					setTimeout("document.getElementById('result').style.visibility = 'hidden'", 1000);
				}
			});
			return false;
		});
	</script>
  </body>
</html>

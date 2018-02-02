<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script src="/Drift_wechat/js/bootstrap.min.js"></script>
<head>
	<title>AddDevice</title>
</head>
<body>
	<c:import url="ManageNavi.jsp"/>
	<!--主要区域开始-->
	<div style="height:30px"></div>
	<div id="main">
		<div id="myAlert" class="alert alert-success" style="display:none;text-align:center">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				<strong>保存成功！</strong>
		</div>
		
		<div id="warnAlert" class="alert alert-warning" style="display:none;text-align:center">
		    <a href="#" class="close" data-dismiss="alert">
		        &times;
		    </a>
		    <strong>保存失败,输入数据不全!</strong>
		</div>
		
		<form action="" method="post" class="form-horizontal" role="form">
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">设备编号:</label>
			    <div class="col-sm-8">
			      <input  class="form-control" id="number" name="number" placeholder="请输入设备编号">
			    </div>
			  </div>
			  <div class="form-group"></div>
			  <div class="form-group">
			    <label for="name" class="col-sm-2 control-label">设备类型:</label>
			    <div class="col-sm-8">
				    <select class="form-control" id="type" name="type">
				      <option value=0>流动设备</option>
				      <option value=1>备用设备</option>
				    </select>
			    </div>
			  </div>
			  <div class="form-group"></div>
			  <div class="form-group">
			    <label for="name" class="col-sm-2 control-label">流动区域:</label>
			    <div class="col-sm-8">
					<select multiple class="form-control" name="areas" id="areas">
						<c:forEach items="${provinces}" var="province">
							<option
								value="${province}">${province}</option>
						</c:forEach>
					</select>
			    </div>
			  </div>
			  
			<div class="form-group">
				<div class="row">
					<div class="col-sm-5"></div>
					<div class=""><button class="btn btn-success" type="button" id="saveBtn">保存</button>
					<button type="button" class="btn btn-primary" id="cancelBtn">取消</button></div>
				</div>
			</div>
		</form>
	</div>
	<!--主要区域结束-->
</body>
	<script>
		$(function(){
		
			$('#naviUL li').click(function(){
				$(".active").attr("class",null);
				$(this).attr("class","active")
			});
			
			$(".close").click(function(){
				$("alert").alert();
			});

  			$("#saveBtn").click(function(){
	  			var number = $("#number").val();
	  			var areas = $("#areas").val();
	  			var type = $("#type").val();
	  			if(number==""||areas==""){
	  				$("#warnAlert").show();
	  			}
	  			
	  			else{
		  			$.ajax({
						type:"POST",
						url:"addDeviceAction",
						data:"number="+number+"&areas="+areas+"&type="+type,
						success:function(data){
							$("#myAlert").show();
			   				setTimeout(function() {
			   					window.location.href="/Drift_wechat/api/manage/deviceList?page=1";
			   				},1000)	;
						}
					});
	  			}
			});	
			
 			$("#cancelBtn").click(function(){
     			window.location.href="addDevice";
     		});
		});
	</script>
</html>
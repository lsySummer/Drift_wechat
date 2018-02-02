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
				<strong>修改成功！</strong>
		</div>
		
		<div id="warnAlert" class="alert alert-warning" style="display:none;text-align:center">
		    <a href="#" class="close" data-dismiss="alert">
		        &times;
		    </a>
		    <strong>修改失败,输入数据不全!</strong>
		</div>
		
		<form action="" method="post" class="form-horizontal" role="form">
			  <div class="form-group">
			    <label  class="col-sm-2 control-label">设备编号:</label>
			    <div class="col-sm-8">
			      <input  class="form-control" id="number" name="number" value="${device.number}" readonly="readonly" />
			    </div>
			  </div>
			  <div class="form-group"></div>
			  <div class="form-group">
			    <label for="name" class="col-sm-2 control-label">设备类型:</label>
			    <div class="col-sm-8">
		    		<c:if test="${device.type==0}">
					   <input  class="form-control" id="type" name="type" value="流动设备" readonly="readonly" />
					</c:if>
					<c:if test="${device.type==2}">
					   <input  class="form-control" id="type" name="type" value="预留设备" readonly="readonly" />
					</c:if>
			    </div>
			  </div>
			  <div class="form-group"></div>
			  <div class="form-group" style="height:400px">
			    <label for="name" class="col-sm-2 control-label">流动区域:</label>
			    <div class="col-sm-8">
					<select multiple class="form-control" name="areas" id="areas"
					onmouseover="{this.size=10}">
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
	  			var areas = $("#areas").val();
	  			var did = '${device.id}';
	  			var type = $("#type").val();
	  			if(areas==""){
	  				$("#warnAlert").show();
	  			}
	  			
	  			else{
		  			$.ajax({
						type:"POST",
						url:"modifyDeviceAction",
						data:"areas="+areas+"&did="+did+"&type="+type,
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
     			window.location.href="ModifyDevice";
     		});
		});
	</script>
</html>
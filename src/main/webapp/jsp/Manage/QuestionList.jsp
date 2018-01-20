<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script src="/Drift_wechat/js/bootstrap.min.js"></script>
	<style type="text/css">
	.table th{
		font-size:15px
	}
	.table td{
		font-size:12px
	}
	</style>    
  </head>
 <body>
    <c:import url="manageNavi.jsp"/>
	<!--主要区域开始-->
     <div class="row">
     	<div class="col-sm-2"></div>
        <div id="main"  class="col-sm-8">
			<table class="table table-hover">
			  <thead>
			    <tr>
			      <th>编号</th>
			      <th>设备编号</th>
			      <th>所在位置</th>
			      <th>排队人数</th>
			      <th>流转区域</th>
			      <th>类型</th>
			    </tr>
			  </thead>
			  <tbody>
		  		<c:forEach items="${deviceList}" var="device" varStatus="index">
					<tr>
						<td >${index.count} </td>
						<td>${device.number}</td>
						<td>${device.loc}</td>
						<td>${device.queueNum}</td>
						<td>${device.area}</td>
						<td>
							<c:if test="${device.type==1}">
							   <p>流动设备<p>
							</c:if>
							<c:if test="${device.type==0}">
							   <p>备用设备<p>
							</c:if>
						</td>
					</tr>
				</c:forEach> 
			  </tbody>
			</table>
		</div> 
	</div>
	<script>
		$(function(){
			$('#naviUL li').click(function(){
				$(".active").attr("class",null);
				$(this).attr("class","active")
			});
		});
	</script>                
    </body>
</html>
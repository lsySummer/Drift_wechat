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
	.header{
	    color: #000000;
	    text-align:center;
	    font-size: 20px;
	}
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
     <div id="myAlert" class="alert alert-success" style="display:none;text-align:center">
				<a href="#" class="close" data-dismiss="alert">&times;</a>
				<strong>收货成功！</strong>
	</div>
     <div class="row">
     	<div class="col-sm-2"></div> 
        <div id="main"  class="col-sm-8">
			<table class="table table-hover">
			  <thead>
			    <tr>
			      <th>编号</th>
			      <th>设备编号</th>
			      <th>开始时间</th>
			      <th>结束时间</th>
			      <th>用户姓名</th>
			      <th>电话</th>
			      <th>地址</th>
			      <th>订单状态</th>
			      <th>确认收货</th>
			    </tr>
			  </thead>
			  <tbody>
		  		<c:forEach items="${orderList}" var="order" varStatus="index">
					<tr>
						<td >${index.count} </td>
						<td >${order.deviceNumber} </td>
						<td > <fmt:formatDate value="${order.startDate}" pattern="yyyy-MM-dd" /></td>
						<td > <fmt:formatDate value="${order.endDate}" pattern="yyyy-MM-dd" /></td>
						<td >${order.name} </td>
						<td >${order.phone} </td>
						<td >${order.address} </td>
						<td >${order.state}</td>
						<td ><button type="button" id="${order.id}" onclick="javascript:confirm(this);">确认收货</button></td>
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
		function confirm(obj){
			orderId = obj.id;
			$.getJSON('/Drift_wechat/api/manage/reviceConfirm?orderId='+orderId, function(json){
				$("#myAlert").show();
			});
		}
		</script>  
    </body>
</html>

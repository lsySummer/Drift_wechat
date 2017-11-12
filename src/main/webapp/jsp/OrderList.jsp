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
			      <th>修改订单</th>
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
						<td ><button type="button" id="${order.id}" onclick="javascript:modify(this);">修改</button></td>
					</tr>
				</c:forEach> 
			  </tbody>
			</table>
		</div> 
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">订单设备修改</h4>
            </div>
            <div class="modal-body" id="modal-body">
             <div class="form-group">
			    <label for="name" class="col-sm-2 control-label">设备编号:</label>
			    <div class="col-sm-6">
					<select class="form-control" name="deviceNum" id="deviceNum" onchange="changetime(this)">
						<c:forEach items="${devices}" var="device">
							<option value="${device}">${device}</option>
						</c:forEach>
					</select>
			    </div>
			  </div>
			  </br>
			  <div class="form-group">
			    <label for="name" class="col-sm-2 control-label">可用时间:</label>
			    <div class="col-sm-6">
					<select class="form-control" name="times" id="times">
						
					</select>
			    </div>
			  </div>
			</div> 
			</br>
			<div align="center"><button class="btn btn-success" type="button" id="modify" onclick="javascript:confirm();">确认提交</button></div>
            </br>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
    </div>
		<script>
		var result;
		$(function(){
			$('#naviUL li').click(function(){
				$(".active").attr("class",null);
				$(this).attr("class","active")
			});
		});
		function modify(obj){
			$.getJSON('/Drift_wechat/api/manage/modify?order='+obj.id,function(json){
				result = json;
				var insert = "";
				for(var key in json){
					insert += '<option value="'+ key +'">'+ key +'</option>';
				}
				document.getElementById("deviceNum").innerHTML = insert;
				$('#myModal').modal('show');
			});
		}
		function confirm(){
			var device = document.getElementById("deviceNum").value;
			var date = document.getElementById("times").value;
			$.getJSON('/Drift_wechat/api/manage/confirm?device='+device+"&date="+date,function(json){
				$('#myModal').modal('hide');
				window.location.href='/Drift_wechat/api/manage/orderList';
			});
		}
		function changetime(obj){
			var insert = '';
			var start = new Date(result[obj.value]);
			console.log(start);
			for(var i = 0; i < 7; i ++){
				insert += '<option value="'+ getNextDay(start) +'">'+ getNextDay(start) +'</option>';
			}
			document.getElementById("times").innerHTML = insert;
		}
		function getNextDay(d){
	        d = new Date(d);
	        d = + d + 1000*60*60*24;
	        d = new Date(d);
	        //return d;
	        //格式化
	        return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
        }
		</script>               
    </body>
</html>

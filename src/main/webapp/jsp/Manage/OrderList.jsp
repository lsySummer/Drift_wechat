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
		font-size:12px;
		text-align:center;
		valign:center;
	}
	</style>   
  </head>
    <body>
    <c:import url="manageNavi.jsp"/>
     <!--主要区域开始-->
     <div class="row">
     	<div class="col-sm-1"></div> 
        <div id="main"  class="col-sm-9">
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
			      <th>甲醛检测（位置-面积-数值）</th>
			      <th>修改设备</th>
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
						<td >
							<table class="table table-hover">
							  <tbody>
							  	  <c:forEach items="${order.jqNum}" var="jq" varStatus="index">
									<tr>
									<td >${jq.location}</td>
									<td >${jq.area}</td>
									<td >${jq.num}</td>
									</tr>
								  </c:forEach> 
							  </tbody>
							</table>
						</td>
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
					</select>
			    </div>
			  </div>
			  </br>
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
		<script>
		var result;
		var orderId;
		$(function(){
			$('#naviUL li').click(function(){
				$(".active").attr("class",null);
				$(this).attr("class","active")
			});
		});
		function modify(obj){
			$.getJSON('/Drift_wechat/api/manage/modify?order='+obj.id,function(json){
				result = json;
				orderId = obj.id;
				var insert = "";
				for(var key in json){
					insert += '<option value="'+ json[key].id +'">'+ json[key].number +'</option>';
				}
				document.getElementById("deviceNum").innerHTML = insert;
				var insert2 = '';
				var temp = new Date(getDate(document.getElementById("deviceNum").value));
				var start = temp.getFullYear() + '-' + getFormatDate(temp.getMonth()+1)+'-'+getFormatDate(temp.getDate());
				for(var i = 0; i < 7; i ++){
					insert2 += '<option value="'+ start +'">'+ start +'</option>';
					start = getNextDay(start);
				}
				document.getElementById("times").innerHTML = insert2;
				$('#myModal').modal('show');
			});
		}
		function confirm(){
			var deviceId = document.getElementById("deviceNum").value;
			var deviceNumber = getName(document.getElementById("deviceNum").value);
			var date = document.getElementById("times").value;
			$.getJSON('/Drift_wechat/api/manage/confirm?deviceId='+deviceId+"&date="+date+"&deviceNumber="+deviceNumber+"&orderId="+orderId,function(json){
				$('#myModal').modal('hide');
				window.location.href='/Drift_wechat/api/manage/orderList';
			});
		}
		function changetime(obj){
			var insert = '';
			var temp = new Date(getDate(obj.value));
			var start = temp.getFullYear() + '-' + getFormatDate(temp.getMonth()+1)+'-'+getFormatDate(temp.getDate());
			for(var i = 0; i < 7; i ++){
				insert += '<option value="'+ start +'">'+ start +'</option>';
				start = getNextDay(start);
			}
			document.getElementById("times").innerHTML = insert;
		}
		function getNextDay(date){
	       	var date = new Date(date);
		    date.setDate(date.getDate() + 1);
		    var month = date.getMonth() + 1;
		    var day = date.getDate();
		    return date.getFullYear() + '-' + getFormatDate(month) + '-' + getFormatDate(day);
        }
        function getFormatDate(arg) {
		    if (arg == undefined || arg == '') {
		        return '';
		    }
		    var re = arg + '';
		    if (re.length < 2) {
		        re = '0' + re;
		    }
		    return re;
		}
		function getDate(id){
			for(var key in result){
				if(result[key].id == id){
					return result[key].date;
				}
			}
		}
		function getName(id){
			for(var key in result){
				if(result[key].id == id){
					return result[key].number;
				}
			}
		}
		</script>  
    </body>
</html>

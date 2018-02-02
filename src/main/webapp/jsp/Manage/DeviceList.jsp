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
    <c:import url="ManageNavi.jsp"/>
    <!--操作提示框-->
   	<div id="successAlert" class="alert alert-success" style="display:none;text-align:center">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		<strong id="successContent"></strong>
	</div>
	
	<div id="warnAlert" class="alert alert-warning" style="display:none;text-align:center">
	    <a href="#" class="close" data-dismiss="alert">
	        &times;
	    </a>
	    <strong id="warnContent"></strong>
	</div>
	
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
			      <th>修改类型</th>
			      <th>修改流转区域</th>
			      <th>删除</th>
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
							<c:if test="${device.type==0}">
							   <p>流动设备<p>
							</c:if>
							<c:if test="${device.type==2}">
							   <p>备用设备<p>
							</c:if>
						</td>
						<td>
							<button type="button" class="btn btn-primary  btn-sm" onclick="changeType('${device.id}','${device.type}');">修改</button>
						</td>
						<td>
							<button type="button" class="btn btn-primary  btn-sm" onclick="toChangeArea('${device.id}');">修改</button>
						</td>
						<td>
							<button type="button" class="btn btn-danger  btn-sm" onclick="confrimDelete('${device.id}','${device.loc}');">删除</button>
						</td>
					</tr>
				</c:forEach> 
			  </tbody>
			</table>
		</div> 
	</div>
	<!--分页-->
	<div class="row">
     	<div class="col-sm-2"></div>
        <div id="pages"  class="col-sm-8" align="center">
        	<c:if test="${page.counts!=null && page.totalPage>1}">
				<a href="/Drift_wechat/api/manage/deviceList?page=1">首页</a>
				<a href="/Drift_wechat/api/manage/deviceList?page=${page.previous}">上一页</a>
				<span class="current_page">${page.currentPage}</span>/<span id="totalPage" class="current_page">${page.totalPage}</span>
				<a href="/Drift_wechat/api/manage/deviceList?page=${page.next}">下一页</a>
				<a href="/Drift_wechat/api/manage/deviceList?page=${page.totalPage}">末页</a>
				<input type="text" id="skip" name="page" class="width50">
				<input type="button" id="goBtn" class="btn btn-primary  btn-sm" value="GO">
			</c:if>
        </div>
    </div>
	<!-- 信息删除确认 -->  
	<div class="modal fade" id="delcfmModel">  
	  <div class="modal-dialog">  
	    <div class="modal-content message_align">  
	      <div class="modal-header">  
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>  
	        <h4 class="modal-title">提示信息</h4>  
	      </div>  
	      <div class="modal-body">  
	        <p>您确认要删除吗？设备对应的订单也将被删除</p>  
	      </div>  
	      <div class="modal-footer">  
	         <input type="hidden" id="url"/>  
	         <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>  
	         <a  onclick="deleteDevice()" class="btn btn-success" data-dismiss="modal">确定</a>  
	      </div>  
	    </div><!-- /.modal-content -->  
	  </div><!-- /.modal-dialog -->  
	</div><!-- /.modal --> 
	<script>
		$(function(){
			$('#naviUL li').click(function(){
				$(".active").attr("class",null);
				$(this).attr("class","active")
			});
		});
		function toChangeArea(did){
			window.location.href='/Drift_wechat/api/manage/toChangeArea?did='+did;
		}
   		/*更改设备类型*/
   		function changeType(did,type){
   			var toType;
   			if(type =="2"){
   				toType = "0";
   			}
   			if(type =="0"){
   				toType = "2";
   			}
   			if(did!='undefined'&&type!='undefined'){
	  			$.ajax({
					type:"GET",
					url:"/Drift_wechat/api/manage/changeType",
					data:"did="+did+"&type="+toType,
					success:function(data){
						if(data=="1"){
							$("#successContent").html("更改成功");
							$("#successAlert").show();
							refeshCurrentPage();
						}
						else{
							$("#warnContent").html("更改失败");
							$("#warnAlert").show();
						}
					}
				});
   			}
   			else{
   				$("#warnContent").html("更改失败，传递空参数");
				$("#warnAlert").show();
   			}
   		}
   		/*刷新本页面  */
   		function refeshCurrentPage(){
 				setTimeout(function() {
 					window.location.href="/Drift_wechat/api/manage/deviceList?page=1";
 				},1000)	;
   		}
   		
    	/*确认删除  */
		function confrimDelete(did,loc) {
    		if(loc!="company"){
    			$("#warnContent").html("该设备目前正在漂流中，不可删除！");
				$("#warnAlert").show();
				return;
    		}
	        $('#url').val(did);//给会话中的隐藏属性URL赋值  
	        $('#delcfmModel').modal();  
		}  
   		/*删除设备*/
   		function deleteDevice(){
   			var did =$.trim($("#url").val());//获取会话中的隐藏属性URL 
   			if(did!='undefined'){
	  			$.ajax({
					type:"GET",
					url:"/Drift_wechat/api/manage/deleteDevice",
					data:"did="+did,
					success:function(data){
						if(data=="1"){
							$("#successContent").html("删除成功");
							$("#successAlert").show();
							refeshCurrentPage();
						}
						else{
							$("#warnContent").html("删除失败");
							$("#warnAlert").show();
						}
					}
				});
   			}
   			else{
   				$("#warnContent").html("删除失败，传递空qid");
				$("#warnAlert").show();
   			}
   		}
   		
		$("#goBtn").click(function(){
    		if(!(/^[\d]*$/.test($("#skip").val())))
			{
    			alert("页码填写格式错误");
				return;
			}
			var goPage = parseInt($("#skip").val());
			var totalPage = parseInt($("#totalPage").html());
			if(goPage>totalPage||goPage<1){
				alert("页码有误");
				return;
			}
			else{
				window.location.href="/Drift_wechat/api/manage/deviceList?page="+goPage;
			}
		});
	</script>                
    </body>
</html>

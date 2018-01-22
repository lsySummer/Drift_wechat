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
	/*表格样式  */
	.table th{
		font-size:15px
	}
	.table td{
		font-size:12px
	}
	/*分页样式  */
	#pages a
	{
	    color: #000;
	}
	#pages a:hover
	{
	    background: #cddde4;
	    border: #97b9c9 solid 1px;
	    color: #067db5;
	}
	#pages a.current_page
	{
	    background: #FFF;
	    border: #89bdd8 solid 1px;
	    color: #067db5;
	}
	</style>    
  </head>
 <body>
    <c:import url="manageNavi.jsp"/>
    
    <!-- 模态框显示问题的具体内容 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:10%;bottom:10%;position:relative;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						问题详情
					</h4>
				</div>
				<div class="modal-body" align="center" id="modelContent"></div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	
	<!--主要区域开始-->
     <div class="row">
     	<div class="col-sm-2"></div>
        <div id="main"  class="col-sm-8">
			<table class="table table-hover">
			  <thead>
			    <tr>
			      <th>编号</th>
			      <th>提问者</th>
			      <th>标题</th>
			      <th>内容</th>
			      <th>创建时间</th>
			      <th>热帖</th>
			      <th>设置推荐</th>
			      <th>取消推荐</th>
			    </tr>
			  </thead>
			  <tbody>
		  		<c:forEach items="${qList}" var="question" varStatus="index">
					<tr> 
						<td >${index.count} </td>
						<td>${userList[index.count-1].nickName}</td>
						<td>${question.title}</td>
						<td><a href="javascript:ShowModel('${question.content}')">查看内容</a></td>
						<td>${question.createTime}</td>
						<c:if test="${flagList[index.count-1]=='1'}">
							<td><p>是</p></td>
							<td><button type="button" class="btn btn-default btn-lg" disabled="disabled">设置</button></td>
							<td><button type="button" class="btn btn-danger" onclick="javascript:window.location.href='';"/>取消</td>
					   </c:if> 				     
					   <c:if test="${flagList[index.count-1]=='0'}">
					   		<td><p>否</p></td>
							<td><button type="button" class="btn btn-primary btn-lg">设置</button></td>
							<td><button type="button" class="btn btn-default btn-lg" disabled="disabled" onclick="javascript:window.location.href='';"/>取消</td>
					   </c:if>
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
				<a href="/Drift_wechat/api/manage/QA/questionList?page=1">首页</a>
				<a href="/Drift_wechat/api/manage/QA/questionList?page=${page.previous}">上一页</a>
				<span class="current_page">${page.currentPage}</span>/<span id="totalPage" class="current_page">${page.totalPage}</span>
				<a href="/Drift_wechat/api/manage/QA/questionList?page=${page.next}">下一页</a>
				<a href="/Drift_wechat/api/manage/QA/questionList?page=${page.totalPage}">末页</a>
				<input type="text" id="skip" name="page" class="width50">
				<input type="button" id="goBtn" class="width50" value="GO">
			</c:if>
        </div>
    </div>
    
    <script type="text/javascript">
   		function ShowModel(content){
   			alert("显示模态框");
   			console.log(content);
   			$('#myModal').modal();
   			$('#modelContent').html(content);
   		}
    </script>                   
</body>
</html>
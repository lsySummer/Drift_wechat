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
	<link rel="stylesheet" href="/Drift_wechat/css/manage/global.css">
	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script src="/Drift_wechat/js/bootstrap.min.js"></script>
  </head>
 <body>
    <c:import url="manageNavi.jsp"/>
    
    <!--操作提示框-->
   	<div id="successAlert" class="alert alert-success" style="display:none;text-align:center">
		<a href="#" class="close" data-dismiss="alert">&times;</a>
		<strong id="successContent"></strong>
	</div>
	
	<div id="warnAlert" class="alert alert-warning" style="display:none;text-align:center">
	    <a href="#" class="close" data-dismiss="alert">
	        &times;
	    </a>
	    <strong id="warnContent">保存失败,输入数据不全!</strong>
	</div>
		
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
			      <th>回答</th>
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
						<td><a href=javascript:showModel('${question.id}')>查看内容</a></td>
						<td><a href="javascript:toAnswerList('${question.id}')">查看回答</a></td>
						<td>${question.createTime}</td>
						<c:if test="${flagList[index.count-1]=='1'}">
							<td><p>是</p></td>
							<td><button type="button" class="btn btn-default  btn-sm" disabled="disabled">设置</button></td>
							<td><button type="button" class="btn btn-danger  btn-sm" onclick="javascript:removeRec('${question.id}');"/>取消</td>
					   </c:if> 				     
					   <c:if test="${flagList[index.count-1]=='0'}">
					   		<td><p>否</p></td>
							<td><button type="button" class="btn btn-primary  btn-sm" onclick="javascript:setRec('${question.id}');">设置</button></td>
							<td><button type="button" class="btn btn-default  btn-sm" disabled="disabled" 
							onclick="javascript:removeRec('${question.id}');"/>取消</td>
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
				<input type="button" id="goBtn" class="btn btn-primary  btn-sm" value="GO">
			</c:if>
        </div>
    </div>
    
    <script type="text/javascript">
    	/*显示模态框  */
   		function showModel(qid){
   			$.ajax({
				type:"GET",
				url:"/Drift_wechat/api/manage/QA/getQContent",
				data:"qid="+qid,
				success:function(content){
					$('#modelContent').html(content);
					$('#myModal').modal();
				}
			});
   		}
   		/*设置推荐*/
   		function setRec(qid){
   			if(qid!='undefined'){
	  			$.ajax({
					type:"GET",
					url:"/Drift_wechat/api/manage/QA/setRec",
					data:"qid="+qid,
					success:function(data){
						if(data=="1"){
							$("#successContent").html("设置成功");
							$("#successAlert").show();
							refeshCurrentPage();
						}
						else{
							$("#warnContent").html("设置失败");
							$("#warnAlert").show();
						}
					}
				});
   			}
   			else{
   				$("#warnContent").html("设置失败，传递空qid");
				$("#warnAlert").show();
   			}
   		}
   		/*取消推荐 */
   		function removeRec(qid){
   			if(qid!='undefined'){
	  			$.ajax({
					type:"GET",
					url:"/Drift_wechat/api/manage/QA/removeRec",
					data:"qid="+qid,
					success:function(data){
						if(data=="1"){
							$("#successContent").html("取消成功");
							$("#successAlert").show();
							refeshCurrentPage();
						}
						else{
							$("#warnContent").html("取消失败");
							$("#warnAlert").show();
						}
					}
				});
   			}
   			else{
   				$("#warnContent").html("设置失败，传递空qid");
				$("#warnAlert").show();
   			}
   		}
   		/*操作成功刷新当前页面*/
   		function refeshCurrentPage(){
			setTimeout(function() {
				window.location.href="/Drift_wechat/api/manage/QA/questionList?page="+${page.currentPage};
			},1000)
   		}
   		
   		function toAnswerList(qid){
   			if(qid!='undefined'){
   				window.location.href = "/Drift_wechat/api/manage/QA/answerList?page=1&qid="+qid;
   			}
   			else{
   				$("#warnContent").html("设置失败，传递空qid");
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
				window.location.href="/Drift_wechat/api/manage/QA/questionList?page="+goPage;
			}
		});
    </script>                   
</body>
</html>
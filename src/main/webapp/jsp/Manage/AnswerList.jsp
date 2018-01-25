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
	    <strong id="warnContent"></strong>
	</div>
	<c:if test="${flag=='0'}">
	<h2>此提问暂无回答</h2>
	</c:if>
	<!--主要区域开始-->
     <div class="row">
     	<div class="col-sm-2"></div>
        <div id="main"  class="col-sm-8">
			<table class="table table-hover">
			  <thead>
			    <tr>
			      <th>编号</th>
			      <th>回答者</th>
			      <th>内容</th>
			      <th>创建时间</th>
			      <th>赞数</th>
			      <th>删除</th>
			    </tr>
			  </thead>
			  <tbody>
		  		<c:forEach items="${aList}" var="answer" varStatus="index">
					<tr> 
						<td>${index.count} </td>
						<td>${userList[index.count-1].nickName}</td>
						<td><a href="javascript:showModel('${answer.id}')">查看内容</a></td>
						<td>${answer.createTime}</td>
						<td>${likeList[index.count-1]}</td>
						<td><button type="button" class="btn btn-danger  btn-sm" onclick="confrimDelete('${answer.id}');"/>删除</td>
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
				<a href="/Drift_wechat/api/manage/QA/answerList?page=1">首页</a>
				<a href="/Drift_wechat/api/manage/QA/answerList?page=${page.previous}">上一页</a>
				<span class="current_page">${page.currentPage}</span>/<span id="totalPage" class="current_page">${page.totalPage}</span>
				<a href="/Drift_wechat/api/manage/QA/answerList?page=${page.next}">下一页</a>
				<a href="/Drift_wechat/api/manage/QA/answerList?page=${page.totalPage}">末页</a>
				<input type="text" id="skip" name="page" class="width50">  
				<input type="button" id="goBtn" class="btn btn-primary  btn-sm" value="GO">
			</c:if>
        </div>
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
						回答详情
					</h4>
				</div>
				<div class="modal-body" align="center" id="modelContent"></div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
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
	        <p>您确认要删除吗？</p>  
	      </div>  
	      <div class="modal-footer">  
	         <input type="hidden" id="url"/>  
	         <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>  
	         <a  onclick="deleteAnswer()" class="btn btn-success" data-dismiss="modal">确定</a>  
	      </div>  
	    </div><!-- /.modal-content -->  
	  </div><!-- /.modal-dialog -->  
	</div><!-- /.modal -->  
    <script type="text/javascript">
    	/*显示模态框  */
   		function showModel(aid){
   			$.ajax({
   				type:"GET",
   				url:"/Drift_wechat/api/manage/QA/getAContent",
   				data:"aid="+aid,
   				success:function(content){
   					$('#modelContent').html(content);
   					$('#myModal').modal();
   				}
   			});
   		}
    	/*跳到某一页  */
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
				window.location.href="/Drift_wechat/api/manage/QA/answerList?page="+goPage;
			}
		});
    	
		/*确认删除  */
		function confrimDelete(aid) {  
	        $('#url').val(aid);//给会话中的隐藏属性URL赋值  
	        $('#delcfmModel').modal();  
		}
		
   		/*删除问题*/
   		function deleteAnswer(){
   			var aid =$.trim($("#url").val());//获取会话中的隐藏属性URL 
   			if(aid!='undefined'){
	  			$.ajax({
					type:"GET",
					url:"/Drift_wechat/api/manage/QA/deleteAnswer",
					data:"aid="+aid,
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
   				$("#warnContent").html("删除失败，传递空aid");
				$("#warnAlert").show();
   			}
   		}
   		/*刷新本页面  */
   		function refeshCurrentPage(){
   			var qid = '${qid}';
   			var page = '${page.currentPage}';
			setTimeout(function() {
				window.location.href="/Drift_wechat/api/manage/QA/answerList?qid="+qid+"&page="+page;
			},1000);
   		}
    </script>                   
</body>
</html>
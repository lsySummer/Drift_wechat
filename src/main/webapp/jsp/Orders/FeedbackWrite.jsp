  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="java.util.*"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-ui.min.css">
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-ui.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>

<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>填写甲醛数值</title>
</head>
<style type="text/css">
	hr.style-four {
		height: 12px;
		border: 0;
		box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
	}
</style>
<body>
	
  	<div class="weui-cell" align="center" style="background:#F5F5F5;margin:10px ">
  		<div class="weui-cell__ft"><a href="/Drift_wechat/jsp/community/CommunityIndex.jsp"><img alt="" style="" src="/Drift_wechat/images/community/close.png"></a></div>
	    <div class="weui-cell__bd" style="color:black">
	      <h2>填写甲醛数值</h2>
	    </div>
	    <div class="weui-cell__ft"><a href="javascript:publishQ()"><img id="release" alt="" style="" src="/Drift_wechat/images/community/before.png"></a></div>
	</div>
	<hr class="style-four" />
	
	<div class="weui-cell" style="height:5%">
	    <div class="weui-cell__bd">添加甲醛检测反馈</div>
	    <div class="weui-cell__bd"><a href="javascript:addFeedback()"><img id="release" alt="" style="" src="/Drift_wechat/images/order/add.png"></a></div>
	</div>
	
	<!--填写模块 -->
	<div class="weui-cells weui-cells_form" id="feedback" style="display:none">
	
	  <div class="weui-cell">
	    <div class="weui-cell__hd"><label class="weui-label">检测位置</label></div>
	    <div class="weui-cell__bd">
	      <input class="weui-input"  id="place" placeholder="请选择检测位置">
	    </div>
	  </div>
	  
	  <div class="weui-cell">
	    <div class="weui-cell__hd">
	      <label class="weui-label">检测面积</label>
	    </div>
	    <div class="weui-cell__bd">
	      <input class="weui-input"  id="area" placeholder="请选择检测面积">
	    </div>
	    <div class="weui-cell__bd">
	       <label class="weui-label">m2</label>
	    </div>
	  </div>
	  
	  <div class="weui-cell ">
	    <div class="weui-cell__hd">
	      <label class="weui-label">甲醛数值</label>
	    </div>
	    <div class="weui-cell__bd">
	      <input class="weui-input"  id="measure" type="number" pattern="[1-9]/d*/./d*|0/./d*[1-9]/d*" placeholder="请输入甲醛数值">
	    </div>
	    <div class="weui-cell__bd">
	      <label class="weui-label">mg/m3</label>
	    </div>
	  </div>
	  <div class="weui-cell">
	  	<a href="javascript:saveOneCase();" class="weui-btn weui-btn_primary">保存</a>
	  </div>  
	</div>
	
<%-- 	<div class="weui-form-preview__hd">
	    <label class="weui-form-preview__label">甲醛数值</label>
	    <em class="weui-form-preview__value"><%=request.getParameter("test")%></em>
	</div> --%>
	<!--预显示模块  -->
	<div id="preview">
	</div>
	
<!-- 	<div class="weui-form-preview">
	  <div class="weui-form-preview__bd">
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">甲醛数值</label>
	      <span class="weui-form-preview__value">200</span>
	    </div>
	  </div>
	  <div class="weui-form-preview__bd">
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">位置</label>
	      <span class="weui-form-preview__value">厨房</span>
	    </div>
	  </div>
	  <div class="weui-form-preview__bd">
	    <div class="weui-form-preview__item">
	      <label class="weui-form-preview__label">面积</label>
	      <span class="weui-form-preview__value">10</span>
	    </div>
	  </div>
	  <div class="weui-form-preview__ft">
	    <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">修改</button>
	    <button type="submit" class="weui-form-preview__btn weui-form-preview__btn_primary" href="javascript:">删除</button>
	  </div>
	</div>
	 -->
</body>

<script type="text/javascript">
	//页面加载完成启动
	$("document").ready(function(){
		$("#place").select({
		  title: "选择位置",
		  items: ["卧室", "客厅", "厨房", "洗漱间", "其他"]
		});
		
		$("#area").select({
		  title: "选择面积",
		  items: ["<10 ", "10-30", "30-50", "50-80", "80-100", "100-150",">150"]
		});
	});
	var jqList = new Array();
	var amount = 0; 
	function addFeedback(){
		$("#feedback").show();
	}
	
	function saveOneCase(){
		$("#feedback").hide();
		var place = $("#place").val();
		var area = $("#area").val();
		var measure = $("#measure").val();
		var temp = {};
		temp["place"] = place;
		temp["area"] = area;
		temp["measure"] = measure;
		amount++;
		jqList.push(temp);
		var html='';
		jqList.forEach(function(){
			html+="<div class='weui-form-preview'>";
			html+="<input id='index' type='hidden'value="+amount+">";
			html+="<div class='weui-form-preview__bd'><div class='weui-form-preview__item'><label class='weui-form-preview__label'>甲醛数值</label>";
			html+="<span class='weui-form-preview__value'>"+measure+"</span></div></div>";
			html+="<div class='weui-form-preview__bd'><div class='weui-form-preview__item'><label class='weui-form-preview__label'>位置</label>";
			html+="<span class='weui-form-preview__value'>"+place+"</span></div></div>";
			html+="<div class='weui-form-preview__bd'><div class='weui-form-preview__item'><label class='weui-form-preview__label'>面积</label>";
			html+="<span class='weui-form-preview__value'>"+area+"</span></div></div>";
			html+="<div class='weui-form-preview__ft'><button type='submit' class='weui-form-preview__btn weui-form-preview__btn_primary' id='edit'>修改</button>";
			html+="<button type='submit' class='weui-form-preview__btn weui-form-preview__btn_primary' id='delete' onclick='deleteOneCase()'>删除</button></div></div>";
		})
		$("#preview").html(html);
	}
	function deleteOneCase(){
		alert($(this).parent());
		alert($(this).parent().parent());
		alert($(this).parent().parent().find('input'))
		alert($(this).parent().parent().find('input').val())
	}
	
	$("#delete").click(function(){
		
	})
	$("#edit").click(function(){
		
	})
	
	function changeIncon(){
		$("#release").attr("src","/Drift_wechat/images/community/release.png")
	}
	
	function publishQ(){
			$.toptip('发布成功', 'success');
			setTimeout(function() {
				window.location.href="/Drift_wechat/jsp/Upload.jsp";
			},1000)
		}
		//window.location.href="/Drift_wechat/jsp/community/QuestionPreview.jsp";
</script>
</html>
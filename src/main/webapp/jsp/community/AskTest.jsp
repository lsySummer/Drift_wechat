  <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<!-- 配置文件 -->
<script type="text/javascript" src="/Drift_wechat/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/Drift_wechat/ueditor/ueditor.all.js"></script>

<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>
<style type="text/css">
	hr.style-four {
		height: 12px;
		border: 0;
		box-shadow: inset 0 12px 12px -12px rgba(0, 0, 0, 0.5);
	}
</style>
<body>
<h1>外部调用UEditor的多图上传和附件上传示例</h1>

<button type="button" id="j_upload_img_btn">多图上传</button>
<ul id="upload_img_wrap"></ul>

<button type="button" id="j_upload_file_btn">附件上传</button>
<ul id="upload_file_wrap"></ul>

<!-- 加载编辑器的容器 -->
<textarea id="uploadEditor" style="display: none;"></textarea>

<!-- 使用ue -->
<script type="text/javascript">

    // 实例化编辑器，这里注意配置项隐藏编辑器并禁用默认的基础功能。
 var uploadEditor = UE.getEditor("uploadEditor", {
        isShow: true,
        focus: false,
        enableAutoSave: false,
        autoSyncData: false,
        autoFloatEnabled:false,
        wordCount: false,
        sourceEditor: null,
        scaleEnabled:true,
        toolbars: [["insertimage", "attachment"]]
    });

    // todo::some code
    document.getElementById('j_upload_img_btn').onclick = function () {
	    var dialog = uploadEditor.getDialog("insertimage");
	    dialog.title = '多图上传';
	    dialog.render();
	    dialog.open();
	};
	
	function _beforeInsertImage(t, result) {
		alert("上传之前");
		console.log(result);
	    var imageHtml = '';
	    for(var i in result){
	        imageHtml += '<li><img src="'+result[i].src+'" alt="'+result[i].alt+'" height="150"></li>';
	    }
	    document.getElementById('upload_img_wrap').innerHTML = imageHtml;
	}

</script>
</body>

<script type="text/javascript">
	function changeIncon(){
		$("#release").attr("src","/Drift_wechat/images/community/release.png")
	}
	
	function publishQ(){
		var title = $("#title").val();
		var content = $("#content").val();
		if(title!=""){
			if(title.charAt(title.length-1)!="?"){
				title += "?";	
			}
			$.get("/Drift_wechat/api/QA/publishQ?title="+title+"&content="+content,function(data){
				console.log(data);
				if(data="1"){
					$.toptip('发布成功', 'success');
					setTimeout(function() {
						window.location.href="/Drift_wechat/jsp/community/QuestionPreview.jsp";
					},1000)
				}
				else{
					$.toptip('操作失败', 'error');
				}
			})
		}
		//window.location.href="/Drift_wechat/jsp/community/QuestionPreview.jsp";
	}
</script>
</html>
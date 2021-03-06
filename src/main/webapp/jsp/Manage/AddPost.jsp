<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>发帖</title>
    <link rel="stylesheet" href="/Drift_wechat/css/bootstrap.min.css">
  	<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
  	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
  	<script type="text/javascript" src="/Drift_wechat/js/bootstrap.min.js"></script> 
  </head>
  
  <body>
  	<c:import url="ManageNavi.jsp"/>
    <div class="weui-cells__title">标题</div>
	<div class="weui-cells">
	  <div class="weui-cell">
	    <div class="weui-cell__bd" style="width:50%">
	      <input class="weui-input" type="text" placeholder="请输入题目" id="title" name="title"/>
	    </div>
	  </div>
	  <div class="weui-cells__title">内容</div>
	  <textarea name="summernote" class="summernote" id="summernote" title="Contents" style="margin-left:5%;margin-right:5%;max-width:1000px;"></textarea>
	</div>
	<div class="weui-loadmore" id="waiting"></div>
    <!--按钮组 -->
	<div id="botton" class="weui-flex" style="margin:0 auto;width:24%">
	  <div class="weui-flex__item placeholder" style="padding-right:5%;">
	   <button id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:cancel();">取消</button>
	  </div>
	  <div class="weui-flex__item placeholder" style="padding-left:5%;">
	   <button id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:confirm();">发布</button>
	  </div>
	</div>
  </body>
<script type="text/javascript">
  var image_name = 0;
  $(document).ready(function() { 
   $('#summernote').summernote({ 
      height: 400, /*指定高度*/  
      lang: 'zh-CN',  
      focus: true,
      toolbar: [     
        ['style', ['bold', 'italic', 'underline', 'clear']],
	    ['font', ['strikethrough', 'superscript', 'subscript']],
	    ['fontsize', ['fontsize']],
	    ['color', ['color']],
	    ['height', ['height']], 
        ['insert', ['picture', 'hr']]  
      ],  
      callbacks: {
        onImageUpload: function(files,editor,$editable) {
        	/* $('#summernote').summernote('insertNode', imgNode); */
            document.getElementById("waiting").innerHTML = '<i class="weui-loading"></i><span class="weui-loadmore__tips">正在加载图片</span>';
            sendFile(files[0],editor,$editable);
        },
        onEnter: function() {
	      $('#summernote').summernote('bold');  
	    }
      } 
   });
  });  
    
  function sendFile(file,editor,$editable) {
    var fileData = URL.createObjectURL(file);
    var img = new Image();
  	img.src = fileData;
  	img.onload = function(){
  		var file_modify = compress(img, img.naturalWidth, img.naturalHeight);
  		var data = new FormData();
	    data.append("file", file_modify, image_name+'.png');
	    var fileTest = URL.createObjectURL(file_modify);
	    $.ajax({
	        data: data,
	        type: "POST",
	        url: "/Drift_wechat/api/manage/QA/Question",
	        cache: false,
	        contentType: false,
	        processData: false,
	        success: function(url) {
	            /* $('#'+image_name).attr('src', url); */
	            $('#summernote').summernote('insertImage', fileTest, function ($image) {
			      $image.css('width', document.body.clientWidth*0.95);
				  $image.attr('id', image_name);
				});
				document.getElementById("waiting").innerHTML = '';
	            image_name ++;
	        }
	    });
  	}
  }

  function confirm(){
	if ($('#summernote').summernote('isEmpty') || document.getElementById("title").value.trim(' ') == '') {
	  alert('总要说点啥吧');
	}else{
	  window.location.href='/Drift_wechat/api/manage/QA/ConfirmQuestion?summernote='+$('#summernote').summernote('code')+"&title="+document.getElementById("title").value;
	}
  }
  
  function cancel(){
  	window.location.href='/Drift_wechat/api/manage/QA/CancelQuestion';
  }
  
  function test(){
  	$('.modal-title').innerHTML = '插入图片';
  }
  
  function compress(img, width, height){
  		var quality = 0.6;
  		var mime_type = "image/png";
	    var cvs = document.createElement('canvas');
	    cvs.width = width; 
	    cvs.height = height;
	    cvs.getContext("2d").drawImage(img, 0, 0, width, height);
		var newImageData = cvs.toDataURL(mime_type, quality);
	    return dataURItoBlob(newImageData);
  }
  
  function dataURItoBlob(dataurl) {
  		var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
	        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
	    while(n--){
	        u8arr[n] = bstr.charCodeAt(n);
	    }
	    return new Blob([u8arr], {type:mime});
   }
</script>
<link href="/Drift_wechat/css/summernote.css" rel="stylesheet">
<script src="/Drift_wechat/js/summernote.js"></script>
</html>

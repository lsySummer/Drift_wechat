<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发帖</title>
    <link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
  	<link href="/Drift_wechat/css/summernote.css" rel="stylesheet">
  	<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
  	<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
  	<script type="text/javascript" src="/Drift_wechat/js/myJS/Forbid.js"></script>
  	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
  	<script src="/Drift_wechat/js/bootstrap.min.js"></script> 
  	<script src="/Drift_wechat/js/summernote.min.js"></script>
  	<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
  </head>
  
  <body>
    <div class="weui-cells__title" style="margin-top:4%;">内容</div>
    <textarea name="summernote" class="summernote" id="summernote" title="Contents" style="width:100%"></textarea>
    <div class="weui-loadmore" id="waiting"></div>
    <!--按钮组 -->
	<div id="botton" class="weui-flex" style="width:100%;">
	  <div class="weui-flex__item placeholder" style="padding-left:20px;padding-right:10px">
	   <button id="auth" name="auth" class="weui-btn weui-btn_warn" onclick="javascrtpt:cancel();">取消</button>
	  </div>
	  <div class="weui-flex__item placeholder" style="padding-left:10px;padding-right:20px">
	   <button id="auth" name="auth" class="weui-btn weui-btn_primary" onclick="javascrtpt:confirm();">发布</button>
	  </div>
	</div>
    <input type="text" id="qid" name="qid" style="visibility:collapse"/>
  </body>
<script type="text/javascript">
  var image_name = 0;
  var qid = getParmFormUrl("qid");
  document.getElementById("qid").value = qid; 
  $(document).ready(function() {
   $('#summernote').summernote({  
      placeholder: '快说点啥...', 
      height: 450, /*指定高度*/  
      lang: 'zh-CN',  
      focus: true,
      toolbar: [     
        ['fontsize', ['fontsize']],  
        ['insert', ['picture', 'hr']]  
      ],  
      callbacks: {
        onImageUpload: function(files) {
        	/* $('#summernote').summernote('insertNode', imgNode); */
        	document.getElementById("waiting").innerHTML = '<i class="weui-loading"></i><span class="weui-loadmore__tips">正在加载图片</span>';
            sendFile(files[0]);
        }
      } 
   });  
  });  
    
  function sendFile(file) {
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
	        url: "/Drift_wechat/api/QA/Answer",
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
  	if ($('#summernote').summernote('isEmpty')){
  	  alert('总要说点啥吧');
  	}else{
  	  window.location.href="/Drift_wechat/api/QA/ConfirmAnswer?summernote="+$('#summernote').summernote('code')+'&qid=' + qid;
  	}
  }
  
  function getParmFormUrl(name){
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null){return decodeURI(r[2]);}
    return null;
  }
  
  function cancel(){
  	window.location.href='/Drift_wechat/api/QA/CancelAnswer?qid=' + qid;
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
</html>

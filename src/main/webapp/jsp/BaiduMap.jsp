<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>  
<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
<link rel="stylesheet" href="/Drift_wechat/css/bootstrap.css">
<script type="text/javascript" src="/Drift_wechat/js/myJS/Forbid.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
<script src="/Drift_wechat/js/bootstrap.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=FGnoI8RVLDdSe5qWVvKv5XjGphYGNRZ2"></script>
<script type='text/javascript' src='/Drift_wechat/js/swiper.js' charset='utf-8'></script>
<script type='text/javascript' src='/Drift_wechat/js/map/baiduMap.js' charset='utf-8'></script>
<style type="text/css">  
	.placeholder {
	  padding: 0 10px;
	}
</style> 

<head>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">  
    <title>果麦公益检测</title>
</head>

<body ontouchstart>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:10%;bottom:10%;position:relative;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" align="center">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="myModalLabel">
						甲醛检测仪
					</h4>
				</div>
				<div class="modal-body" align="center">
					 <img  width=80% height=70%  src="/Drift_wechat/images/info.jpg">
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>

    <div style="height:100%; width: 100%;position:absolute;" id="map"></div>
	
	<div style="top:70%;left:80%;position:relative;">
		<img alt="" src="/Drift_wechat/images/map/tomap.png" id="countryMap" height="32px" width="32px">全国
	</div>
	
	<div style="top:75%;left:80%;position:relative;">
		<img alt="" src="/Drift_wechat/images/map/tolocal.png" id="originalMap" height="32px" width="32px">本地
	</div>
	<!--返回键  -->	
	<div class="weui-footer_fixed-bottom" style="margin-left:20%;margin-right:20%;">
		<a href="javascript:backIndex();" class="weui-btn weui-btn_primary">返回</a>
	</div>
	
	<div id="comment" class='weui-popup__container popup-bottom'>
      <div class="weui-popup__overlay"></div>
      <div class="weui-popup__modal">
      
        <div class="toolbar">
          <div class="toolbar-inner">
            <a href="javascript:;" class="picker-button close-popup">关闭</a>
            <p class="title">评论区</p>
          </div>
        </div>
        
		<div class="modal-content">
          <div class="weui-grids" id="ptContent">          
            <a class="weui-grid js_grid pb" >
                <img  id="img1" width=100px height=100px src="/Drift_wechat/images/default.jpg">
            </a>
            
            <a class="weui-grid js_grid pb" >
                <img  id="img2" width=100px height=100px src="/Drift_wechat/images/default.jpg">
            </a>
            
            <a class="weui-grid js_grid pb" >
                <img  id="img3" width=100px height=100px src="/Drift_wechat/images/default.jpg">
            </a>
            <div class="weui-article" id="commentDiv"></div>
	     	</div> 
         </div>
        </div>    
      </div>
</body>
<script type="text/javascript">
	//页面加载完成启动
	$("document").ready(function(){
	    var x = <%=session.getAttribute("locationX")%>;
	    var y = <%=session.getAttribute("locationY")%>;
	    myLocation = {"x":parseFloat(x),"y":parseFloat(y)};
		//weChatMap();
		map_init();
	});
</script> 
</html>
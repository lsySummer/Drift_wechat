<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="stylesheet" href="/Drift_wechat/css/weui.min.css">
	<link rel="stylesheet" href="/Drift_wechat/css/demos.css">
	<link rel="stylesheet" href="/Drift_wechat/css/jquery-weui.min.css">
	<link rel="stylesheet" href="/Drift_wechat/css/jquery-ui.min.css">
	<script type="text/javascript" src="/Drift_wechat/js/weui.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-3.2.0.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-weui.min.js"></script>
	<script type="text/javascript" src="/Drift_wechat/js/jquery-ui.js"></script>
  	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <base href="<%=basePath%>">  
    <title>预约日期</title>
  </head>
  
  <body>
  	<header class='demos-header'>
      <h2 class="demos-title">产品预约</h2>
    </header>
    <div class="container" id="container">
    		<div class="page__bd">
		        <div class="weui-cells__title">选择时间</div>
		        
		        <div class="weui-cells weui-cells_form">
		        	<div class="weui-cell" id="selectDate" style="margin-left:20px;margin-top:20px;"></div>
		            <div class="weui-cell">
		                <div class="weui-cell__bd">您选择的时间:&nbsp;&nbsp;</div>
		                <div class="weui-cell__bd">
		                    <input id="alternate" class="weui-input" value="" required="" readonly="readonly"/>
		                </div>
		            </div>
		        </div>
		    </div>
		    <div class="weui-btn-area">
	            <button class="weui-btn weui-btn_primary" id="submit">确定并提交</button>
	        </div>
	</div>
    <div class="weui-msg__extra-area">
        <div class="weui-footer">
                <p class="weui-footer__text">Copyright © 2017-2020 GuoMai</p>
        </div>
  	</div>
  </body>
  
  <script type="text/javascript">
 	var disabledDays =[];//格式要与datepicker中的日期格式一致（yyyy/mm/dd）。  
	$(document).ready(function() {
		var td = new Date();
		var d = new Date(td);
		d.setDate(td.getDate() + 1);
		var startDateStr = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
		//document.getElementById("selectDate").value = startDateStr;
		var date2 = new Date(d);
		date2.setDate(d.getDate() + 30);			
		var endDateStr = date2.getFullYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDate();
		$.getJSON('/Drift_wechat/api/order/getDate',function(json){
           	var disabledDays = json.dates;
           	//disabledDays = ["2018-1-30"];
            $('#selectDate').datepicker({
		      	showButtonPanel:true,//是否显示按钮面板
		      	beforeShowDay: disableSpecificDays,
		      	dateFormat: 'yy-mm-dd',//日期格式
		      	clearText:"清除",//清除日期的按钮名称  
	            closeText:"关闭",//关闭选择框的按钮名称  
	            yearSuffix: '年', //年的后缀  
	            showMonthAfterYear:true,//是否把月放在年的后面  
	            defaultDate:"",//默认日期  
	            minDate:startDateStr,//最小日期  
	            maxDate:endDateStr,//最大日期  
	            monthNames: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],  
	            dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	            dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	            dayNamesMin: ['日','一','二','三','四','五','六'],
	            altField: "#alternate",
      			altFormat: "yy-mm-dd"  
		     });
		     
			function disableSpecificDays(date) {  
			    var dateStr = date.pattern("yyyy-MM-dd");
			    if (typeof(disabledDays) != "undefined") {  
			        for (var i = 0; i < disabledDays.length; i++) {  
			            if($.inArray(dateStr,disabledDays) != -1) {
			                    return [false];  
			            }  
			        }  
			    }  
			    return [true];  
			} 
			$("#alternate").val("");  
        })   
	});
        
	$("#submit").click(function(){
		if($("#alternate").val().trim().length){
			var startDate = document.getElementById("alternate").value;
			$.ajax({url:"/Drift_wechat/api/order/date?startDate="+startDate, success: function(data){
				if(data == "200"){
					window.location.href='/Drift_wechat/jsp/Result.jsp';
				}else{
					$.toptip('您的设备已经被预约走了～', 'error');
					setTimeout("window.location.href='/Drift_wechat/jsp/DateChoose.jsp'", 1000);
				}
     			}});
		}else{
			$.toptip('操作失败，请确保所有内容均已填写', 'error');
			return false;
		}
	});
	
	Date.prototype.pattern=function(fmt) {           
	    var o = {           
	    "M+" : this.getMonth()+1, //月份           
	    "d+" : this.getDate(), //日           
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时           
	    "H+" : this.getHours(), //小时           
	    "m+" : this.getMinutes(), //分           
	    "s+" : this.getSeconds(), //秒           
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度           
	    "S" : this.getMilliseconds() //毫秒           
	    };           
	    var week = {           
	    "0" : "/u65e5",           
	    "1" : "/u4e00",           
	    "2" : "/u4e8c",           
	    "3" : "/u4e09",           
	    "4" : "/u56db",           
	    "5" : "/u4e94",           
	    "6" : "/u516d"          
	    };           
	    if(/(y+)/.test(fmt)){           
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));           
	    }           
	    if(/(E+)/.test(fmt)){           
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);           
	    }           
	    for(var k in o){           
	        if(new RegExp("("+ k +")").test(fmt)){           
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));           
	        }           
	    }           
	    return fmt;           
	}         
   
   
  </script>
</html>

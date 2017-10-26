/**
 * 
 */
$.getJSON('/Drift_wechat/api/delivery/get',function(json){
	if(json.before == 'null'){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="<h1>暂无订单</h1></br><p>暂无仪器信息，先去预约吧！</p>";
	}else{
		if(json.enable != "上家已发货"){
			document.getElementById('confirm').style.color = "grey";
			document.getElementById('confirm').disabled="true";
		}
		document.getElementById('previous').innerHTML=json.before;
		document.getElementById('deliveryNum1').innerHTML=json.receive;
		document.getElementById('deviceId1').innerHTML=json.deviceId;
		if(json.after != null){
			if(json.enable != "已确认收货"){
				document.getElementById('detail').style.color = "grey";
				document.getElementById('detail').disabled="true";
			}
			document.getElementById('next').innerHTML=json.after;
			document.getElementById('deliveryNum2').innerHTML=json.send;
			document.getElementById('deviceId2').innerHTML=json.deviceId;
		}else{document.getElementById("item2").style.display="none";}
	}
});
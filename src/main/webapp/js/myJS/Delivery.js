/**
 * 
 */
$.getJSON('/Drift_wechat/api/delivery/get',function(json){
	if(json.before == null){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}else{
		if(json.enable != "上家已发货"){
			document.getElementById('confirm').disable=true;
		}
		document.getElementById('previous').innerHTML=json.before;
		document.getElementById('deliveryNum1').innerHTML=json.receive;
		document.getElementById('deviceId1').innerHTML=json.deviceId;
		if(json.after != null){
			if(json.enable != "已收货"){
				document.getElementById('detail').disable=true;
			}
			document.getElementById('next').innerHTML=json.after;
			document.getElementById('deliveryNum2').innerHTML=json.send;
			document.getElementById('deviceId2').innerHTML=json.deviceId;
		}else{document.getElementById("item2").style.display="none";}
	}
});
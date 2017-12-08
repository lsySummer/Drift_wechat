/**
 * 
 */
$.getJSON('/Drift_wechat/api/delivery/get',function(json){
	if(json.before == 'null'){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="</br><p>暂无仪器信息，先去预约吧！</p>";
	}else{
		if(json.enable != "上家已发货"){
			document.getElementById('confirm').style.color="grey";
			document.getElementById('confirm').value="false";
//			document.getElementById('confirm').disabled="true";
		}
		if(json.receive != "暂无物流信息"){
			document.getElementById('d1').style.color="green";
		}
		document.getElementById('previous').innerHTML=json.before;
		document.getElementById('deliveryNum1').innerHTML=json.receive;
		document.getElementById('deviceId1').innerHTML=json.deviceId;
		if(json.after != null){
			if(json.enable != "已确认收货"){
				document.getElementById('detail').style.color="grey";
				document.getElementById('detail').value="false";
//				document.getElementById('detail').disabled="true";
			}
			if(json.send != "暂无物流信息"){
				document.getElementById('d2').style.color="green";
				document.getElementById('detail').style.color="grey";
				document.getElementById('detail').value="false";
			}
			document.getElementById('next').innerHTML=json.after;
			document.getElementById('deliveryNum2').innerHTML=json.send;
			document.getElementById('deviceId2').innerHTML=json.deviceId;
		}else{document.getElementById("item2").style.display="none";}
	}
});
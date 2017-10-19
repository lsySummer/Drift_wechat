/**
 * 
 */
$.getJSON('/Drift_wechat/api/delivery/get',function(json){
	var before = json.before;
	var after = json.after;
	if(before == null){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}else{
		document.getElementById('previous').innerHTML=before.name;
		document.getElementById('deliveryNum1').innerHTML=json.receive;
		document.getElementById('deviceId1').innerHTML=json.deviceId;
		if(after != null){
			document.getElementById('next').innerHTML=after.name;
			document.getElementById('deliveryNum2').innerHTML=json.deliveryNum2;
			document.getElementById('deviceId2').innerHTML=json.deviceId;
		}else{document.getElementById("item2").style.display="none";}
	}
});

function confirm(){
	$.toptip('确认收货成功', 'success');
	$.get('/Drift_wechat/api/delivery/confirm');
}
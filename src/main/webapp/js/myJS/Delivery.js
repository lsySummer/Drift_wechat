/**
 * 
 */
$.getJSON('/Drift_wechat/api/user/getDetail',function(json){
	var before = json.before;
	var after = json.after;
	if(before == null){
		document.getElementById("item1").style.display="none";
		document.getElementById("container").innerHTML="暂无订单";
	}else{
		document.getElementById('previous').innerHTML=before.name;
		document.getElementById('deliveryNum1').innerHTML=before.deliveryNum1;
		document.getElementById('deviceId1').innerHTML=before.deviceId1;
		if(after != null){
			document.getElementById('next').innerHTML=after.next;
			document.getElementById('deliveryNum2').innerHTML=after.deliveryNum2;
			document.getElementById('deviceId2').innerHTML=after.deviceId2;
		}else{document.getElementById("item2").style.display="none";}
	}
});

function confirm(){
	$.toptip('确认收货成功', 'success');
	$.get('/Drift_wechat/api/delivery/confirm');
}